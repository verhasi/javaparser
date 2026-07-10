# JavaParser Core Principles

> This module is ALWAYS loaded. It contains non-negotiable rules for any JavaParser contribution.

## 1. Architecture: Metamodel-First

JavaParser uses JavaParser to build JavaParser. The metamodel is the single source of truth for AST structure.

**Rules:**
- ALWAYS use metamodel APIs for type checking and metadata access
- NEVER bypass metamodel with raw Java reflection
- Respect the abstraction layers - they exist for maintainability

**Correct:**
```java
node.isInstanceOfMetaModel(JavaParserMetaModel.commentMetaModel)
meta.isAbstract()
```

**Incorrect:**
```java
Comment.class.isAssignableFrom(node.getType())
Modifier.isAbstract(meta.getType().getModifiers())
```

## 2. Build Pipeline: 6-Stage Self-Referential

The build is ordered and each stage depends on the previous:

```
1. JavaCC         → Enables Java parsing
2. Core compile   → Builds AST node classes  
3. Metamodel gen  → Introspects nodes, builds JavaParserMetaModel
4. Core generators → Generates getters/setters/visitors/clone/etc.
5. bnd generator  → Produces module export declarations
6. Final build    → Produces releasable artifacts
```

**Critical Rule:** If you change AST nodes (add/remove fields or classes), you MUST run:
```bash
./run_core_metamodel_generator.sh   # Stages 1-3
./run_core_generators.sh            # Stages 4-6 (includes spotless:apply)
```

If you only change non-node code, `./run_core_generators.sh` alone suffices.

## 3. Node Class Ordering

`MetaModelGenerator.ALL_NODE_CLASSES` maintains a manually curated list. **Parent classes MUST appear before child classes.**

Example: `Expression` → `LiteralExpr` → `LiteralStringExpr`

Breaking this ordering causes silent metamodel corruption.

## 4. Annotations That Drive Code Generation

| Annotation | Meaning | Effect |
|-----------|---------|--------|
| `@AllFieldsConstructor` | Constructor with all fields | Generators use this to produce builders/constructors |
| `@OptionalProperty` | Field may be null | Generators produce Optional-aware accessors |
| `@DerivedProperty` | Field computed from others | Excluded from equality/hash/clone |

## 5. Code Style (Non-Negotiable)

- **Indent:** 4 spaces (never tabs)
- **Line width:** 120 characters maximum
- **Annotations:** On separate line above method/type
- **Wrapped args:** Aligned to opening parenthesis
- **Trailing whitespace:** Strip on save (modified lines)
- **File ending:** Newline at end of file

## 6. Copyright Header (Required on All New Files)

```java
/*
 * Copyright (C) 2011, 2013-2026 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package com.github.javaparser...;
```

**Note:** Empty line between copyright comment and package declaration is MANDATORY.

## 7. Contribution Process Essentials

### Scope Discipline
- **One PR = One concern.** No exceptions.
- Unrelated improvements go in separate PRs, even if trivial.
- PR title must describe the single change clearly.

### Generator Compliance
Before every PR submission:
```bash
./run_core_metamodel_generator.sh && ./run_core_generators.sh
git diff  # Must be empty
```

### Evidence Requirement
- Every claimed fix must be backed by test results or concrete evidence.
- Don't claim "it works" without proof.

### Test Requirement
- All functional changes require tests (BDD or JUnit).
- Test behavioral contracts, not implementation details.
- Test names must accurately describe what they test.

## 8. Module Boundaries

| Module | Responsibility |
|--------|---------------|
| `javaparser-core` | AST nodes, parsing, visitors, metamodel |
| `javaparser-core-generators` | Code generation for AST node boilerplate |
| `javaparser-core-metamodel-generator` | Metamodel introspection and generation |
| `javaparser-core-testing` | Unit tests for core |
| `javaparser-core-testing-bdd` | BDD-style tests for core |
| `javaparser-core-serialization` | JSON serialization of AST |
| `javaparser-symbol-solver-core` | Type resolution and symbol solving |
| `javaparser-symbol-solver-testing` | Tests for symbol solver |

## 9. CI Validation Pipeline

CI runs on every PR (ignoring .md-only changes):
1. **Checkstyle:** `./mvnw -B checkstyle:check`
2. **Spotless + Generators:** Run both generators → `git diff --exit-code`
3. **Tests:** Full test suite across ubuntu/macos/windows × JDK 8-18

**If CI fails on formatting:** Extract the diff from CI output, save as patch, apply with `git apply`.

## 10. Review Process Expectations

- Address ALL maintainer feedback systematically
- Categorize feedback: functional / architectural / quality / scope
- Provide evidence for each fix
- Separate unrelated improvements immediately
- Expect PRs to stay open for review period
- Minimize review cycles through high-quality first submissions
