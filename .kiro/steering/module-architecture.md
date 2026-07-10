# Module Architecture Specification

> Defines the modular steering system structure, memory management, and development roadmap.

## Memory Budget

**Context Window:** ~1M tokens (~750K words)  
**Core System (always loaded):** ~8.5K words (~1.1% of context)  
**Maximum additional loading:** ~40K words (~5.3% of context)  
**Total maximum steering load:** ~50K words (~6.7% of context)  
**Available for actual work:** ~93% of context  

## Module Specifications

### Module 1: generator-development.md
**Phase:** 1 (High Priority)  
**Path:** `.kiro/steering/modules/generator-development.md`  
**Size:** 8-12K words  
**Loading Trigger:** Working on code generators, visitor modifications, generated code issues  

**Topics to Cover:**
- Generator types and their purposes (Property, Accept, Clone, Replace, HashCode, Equals, etc.)
- Visitor pattern variants (Generic, Void, Modifying)
- NoComment visitor generators (filtering comment nodes)
- How generators use metamodel to produce code
- Testing generator output
- Common generator modification patterns
- Generator execution and verification workflow
- Handling compilation errors during generation

**Source Material:**
- Recent generator-related PRs (PR #5057, similar)
- Generator source code in `javaparser-core-generators/`
- Generated visitor files as output examples
- Maintainer feedback on generator quality

**Dependencies:** core-principles.md (metamodel-first principle)  
**Success Criteria:** Can guide any generator modification from start to finish without external research

---

### Module 2: pr-workflow.md
**Phase:** 1 (High Priority)  
**Path:** `.kiro/steering/modules/pr-workflow.md`  
**Size:** 5-8K words  
**Loading Trigger:** Creating PRs, handling reviews, responding to maintainer feedback  

**Topics to Cover:**
- Optimal PR structure and description format
- Commit message conventions and strategies
- Review feedback categorization and response patterns
- Scope management techniques
- Communication tone and style with maintainers
- Building trust through contribution patterns
- Handling rejected PRs gracefully
- Multi-PR coordination for large features

**Source Material:**
- Merged PRs with substantial review discussions
- Rejected PRs with explanatory feedback
- CONTRIBUTING.md workflow
- PR template analysis

**Dependencies:** core-principles.md (scope discipline, evidence requirement)  
**Success Criteria:** First-submission approval rate >80% when following guide

---

### Module 3: metamodel-patterns.md
**Phase:** 2 (Medium Priority)  
**Path:** `.kiro/steering/modules/metamodel-patterns.md`  
**Size:** 8-12K words  
**Loading Trigger:** Adding/modifying AST nodes, metamodel introspection, node inheritance  

**Topics to Cover:**
- Metamodel internals and how introspection works
- BaseNodeMetaModel API deep dive
- Node class design patterns and inheritance
- Annotation effects on code generation (@AllFieldsConstructor, @OptionalProperty, @DerivedProperty)
- ALL_NODE_CLASSES ordering requirements and rationale
- PropertyMetaModel and field metadata
- Adding new node types (detailed architectural guidance)
- Migration patterns when node structure changes

**Source Material:**
- Historical metamodel-changing PRs
- MetaModelGenerator source code
- BaseNodeMetaModel and related classes
- Wiki: Adding New Nodes guide

**Dependencies:** core-principles.md (node ordering, annotations)  
**Success Criteria:** Can design and implement new AST node from scratch

---

### Module 4: testing-standards.md
**Phase:** 2 (Medium Priority)  
**Path:** `.kiro/steering/modules/testing-standards.md`  
**Size:** 6-10K words  
**Loading Trigger:** Writing tests, test failures, test organization questions  

**Topics to Cover:**
- Test organization across modules (core-testing, testing-bdd, symbol-solver-testing)
- JUnit vs BDD test patterns in JavaParser
- Test naming conventions and @DisplayName usage
- Assertion best practices (behavioral contracts)
- Test fixture patterns for AST parsing
- Cross-platform testing considerations
- Slow tests vs fast tests (AlsoSlowTests profile)
- Integration testing with symbol solver
- Regression test patterns for parser bugs

**Source Material:**
- Existing test suites in all testing modules
- PRs with test quality feedback
- CI workflow test configuration
- BDD test examples

**Dependencies:** core-principles.md (test requirements)  
**Success Criteria:** Tests written following guide pass review without test-quality feedback

---

### Module 5: architecture-decisions.md
**Phase:** 2 (Medium Priority)  
**Path:** `.kiro/steering/modules/architecture-decisions.md`  
**Size:** 10-15K words  
**Loading Trigger:** Design decisions, understanding existing patterns, API evolution  

**Topics to Cover:**
- Historical architecture evolution of JavaParser
- Visitor pattern design and variants (why multiple visitor types)
- Lexical Preserving Printer design rationale
- Symbol solver architecture and resolution strategies
- API stability and backward compatibility rules
- Deprecation patterns and migration strategies
- Module boundary decisions and rationale
- Performance vs correctness tradeoffs
- JavaCC grammar design patterns
- ConcreteSyntaxModel design and purpose

**Source Material:**
- Major architectural PRs and discussions
- Wiki pages (About Symbol Solver, LPP Specification)
- Blog posts on javaparser.org
- Version migration guides

**Dependencies:** core-principles.md (build pipeline, module boundaries)  
**Success Criteria:** Can make architectural decisions consistent with project history

## Module Loading Strategy

### Context-Aware Loading Rules

```
contribution_context → modules_to_load:

"generator|visitor|generated code"     → generator-development.md
"PR|review|feedback|submission"        → pr-workflow.md
"metamodel|node|AST|field|annotation"  → metamodel-patterns.md
"test|assertion|JUnit|BDD"            → testing-standards.md
"design|architecture|API|pattern"      → architecture-decisions.md
```

### Multi-Module Loading
When scenarios span multiple concerns, load at most 2 modules simultaneously:
- Generator + Testing: When writing tests for generators
- Metamodel + Architecture: When designing new nodes
- PR Workflow + any technical module: When preparing submission

### Fallback Behavior
If a module is not yet developed (Phase hasn't been executed):
- Core modules provide basic guidance
- Pattern-index marks gap areas clearly
- Indicate that deeper guidance will be available after Phase X

## Scalability Plan

### Future Module Candidates (Phase 3+)
- `modules/symbol-solver-patterns.md` - Resolution strategies
- `modules/grammar-development.md` - JavaCC patterns
- `modules/performance-patterns.md` - Optimization approaches
- `modules/lexical-preservation.md` - LPP rules and patterns

### Module Growth Rules
- Maximum 15K words per module (hard limit)
- If module exceeds limit, split into focused sub-modules
- Each sub-module must be independently loadable
- Cross-references handle navigation between sub-modules

## Version Management

### Versioning Convention
```
Module Version: <phase>.<revision>
Example: core-principles.md v0.1 (Phase 0, first revision)
```

### Update Triggers
- New maintainer feedback patterns discovered
- Project standards evolve
- New features/tools introduced
- Phase completion adds new modules

### Change Tracking
Each module header includes:
```markdown
> Version: 0.1 | Last Updated: July 2026 | Phase: 0
> Changes: Initial creation from documentation analysis
```
