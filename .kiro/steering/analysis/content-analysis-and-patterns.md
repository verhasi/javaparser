# Content Analysis & Pattern Extraction - Phase 0 Working Document

## 1. PROCESS STANDARDS

### Pattern: PR Creation Workflow
- **Trigger:** Starting any contribution
- **Actions:** Fork → Feature branch → Implement → Run generators → Format → PR
- **Validation:** CI green, git diff empty after generators, tests pass
- **Anti-patterns:** Working directly on master, skipping generator run, mixing concerns in one PR

### Pattern: Pre-Submission Formatting
- **Trigger:** Before creating PR or pushing commits
- **Actions:** Run `./run_core_metamodel_generator.sh && ./run_core_generators.sh` (includes spotless:apply)
- **Validation:** `git diff` shows no changes after running
- **Anti-patterns:** Only running spotless:apply (misses generator changes), committing formatting-only changes mixed with feature changes

### Pattern: CI Failure Recovery
- **Trigger:** Spotless/formatting check fails in CI
- **Actions:** Read CI output → Extract diff → Save as patch → `git apply` → Commit
- **Validation:** Re-push triggers green CI
- **Anti-patterns:** Manually reformatting without understanding what CI expects

### Pattern: Scope Discipline
- **Trigger:** Any PR creation
- **Actions:** Ensure one concern per PR, separate unrelated improvements
- **Validation:** PR title describes single change, no unrelated file modifications
- **Anti-patterns:** Bundling config fixes with bug fixes, including spotted improvements in unrelated PRs

## 2. TECHNICAL STANDARDS

### Pattern: Code Style Compliance
- **Trigger:** Writing or modifying any Java file
- **Actions:** 4-space indent, 120-char wrap, annotations on separate lines, aligned wrapped args
- **Validation:** `./mvnw checkstyle:check` passes, spotless:apply makes no changes
- **Anti-patterns:** Using tabs, exceeding 120 chars, inline annotations

### Pattern: Copyright Header
- **Trigger:** Creating any new Java file
- **Actions:** Add standard copyright header with current year range, empty line before package
- **Validation:** Header matches template exactly, empty line between header and package declaration
- **Anti-patterns:** Missing header, wrong year, no empty line separator

### Pattern: Metamodel-First API Usage
- **Trigger:** Working with AST node type checking or metadata
- **Actions:** Use `node.isInstanceOfMetaModel(JavaParserMetaModel.xxxMetaModel)` and `meta.isAbstract()`
- **Validation:** No raw reflection calls to check node types or modifiers
- **Anti-patterns:** `Comment.class.isAssignableFrom()`, `Modifier.isAbstract(meta.getType().getModifiers())`

### Pattern: Generator Development
- **Trigger:** Modifying code generators
- **Actions:** Use metamodel APIs → Run generators → Verify output → Commit separately
- **Validation:** Generated code makes sense, no unintended changes in generated files
- **Anti-patterns:** Bypassing metamodel, not running generators after changes, mixing generator and generated commits

### Pattern: Annotation-Driven Design
- **Trigger:** Adding/modifying AST node fields
- **Actions:** Use `@AllFieldsConstructor`, `@OptionalProperty`, `@DerivedProperty` appropriately
- **Validation:** Generators produce correct code for annotated fields
- **Anti-patterns:** Manual getter/setter in generated areas, missing annotations

## 3. ARCHITECTURE PRINCIPLES

### Pattern: Self-Referential Build
- **Trigger:** Understanding build failures or modifying build process
- **Actions:** Recognize 6-stage pipeline: JavaCC → metamodel → codegen → generators → bnd → final
- **Validation:** Each stage completes before next begins
- **Anti-patterns:** Running generators before core compiles, skipping metamodel step when nodes change

### Pattern: Node Class Ordering
- **Trigger:** Adding new AST node to MetaModelGenerator.ALL_NODE_CLASSES
- **Actions:** Insert new class AFTER all parent classes in the list
- **Validation:** Build succeeds, metamodel correctly reflects inheritance
- **Anti-patterns:** Adding child before parent, adding at arbitrary position

### Pattern: Adding New AST Nodes (15-step process)
- **Trigger:** Implementing support for new Java syntax
- **Actions:** Skeleton → MetaModel → Generate → Fix errors → Generate again → Tests → Grammar → Symbol solver → Pretty printer → CSM → Validators
- **Validation:** All tests pass, ./mvnw clean test succeeds
- **Anti-patterns:** Skipping steps, wrong ordering, not running generators between steps

### Pattern: Module Boundaries
- **Trigger:** Deciding where to place code
- **Actions:** core (AST + parsing), generators (codegen), metamodel-generator (metamodel), testing (tests), symbol-solver (resolution)
- **Validation:** Code is in appropriate module per its responsibility
- **Anti-patterns:** Putting test code in core, putting generation logic in core

## 4. QUALITY REQUIREMENTS

### Pattern: Test-Driven Contributions
- **Trigger:** Any functional change
- **Actions:** Write tests (BDD or JUnit) demonstrating the fix/feature
- **Validation:** Tests fail without change, pass with change
- **Anti-patterns:** No tests, tests that always pass, testing implementation details

### Pattern: Test Naming Accuracy
- **Trigger:** Writing test methods
- **Actions:** Method name and @DisplayName must accurately describe what's tested
- **Validation:** Reading the test name tells you exactly what behavior is verified
- **Anti-patterns:** Copy-paste test names from other tests, misleading names

### Pattern: Behavioral Contract Testing
- **Trigger:** Writing assertions
- **Actions:** Assert behavioral contracts (e.g., two things are equal) not implementation details (e.g., result is 0)
- **Validation:** Assertions test the contract, not a specific implementation value
- **Anti-patterns:** `assertEquals(0, result)` when testing "results should be equal to each other"

### Pattern: Cross-Platform Validation
- **Trigger:** Before final PR submission
- **Actions:** Consider line-ending differences, path separators, JDK version compatibility
- **Validation:** CI matrix covers ubuntu/macos/windows × JDK 8-18
- **Anti-patterns:** Assuming Unix line endings, using platform-specific paths in tests

## 5. CULTURAL NORMS

### Pattern: Evidence-Based Claims
- **Trigger:** Claiming a fix works or a change is correct
- **Actions:** Provide test results, before/after comparisons, specific commit references
- **Validation:** Each claim backed by concrete, verifiable evidence
- **Anti-patterns:** "I fixed it" without proof, assumptions about behavior without testing

### Pattern: Systematic Feedback Response
- **Trigger:** Receiving maintainer review feedback
- **Actions:** Categorize feedback → Address each point → Provide evidence → Separate unrelated items
- **Validation:** Every feedback point explicitly addressed with evidence
- **Anti-patterns:** Ignoring feedback, partial responses, defensive reactions

### Pattern: Respect for Maintainer Time
- **Trigger:** All interactions with project
- **Actions:** High-quality first submissions, focused PRs, clear descriptions
- **Validation:** Minimal back-and-forth review cycles needed
- **Anti-patterns:** Submitting half-done work, requiring multiple review rounds for basic issues

### Pattern: Architectural Consistency
- **Trigger:** Choosing implementation approach
- **Actions:** Follow existing patterns, use project abstractions, match surrounding code style
- **Validation:** New code looks like it belongs with existing code
- **Anti-patterns:** Introducing new patterns when existing ones work, bypassing abstractions
