# JavaParser Pattern Index

> This module is ALWAYS loaded. Maps scenarios to guidance and future modules.

## Module System Overview

### Always-Loaded Core (This Session)
| Module | Size | Status |
|--------|------|--------|
| `core-principles.md` | ~2.5K words | ✅ Complete |
| `quick-reference.md` | ~4K words | ✅ Complete |
| `pattern-index.md` | ~2K words | ✅ Complete |

### On-Demand Modules (Load When Needed)
| Module | Size Target | Status | Phase |
|--------|-------------|--------|-------|
| `modules/generator-development.md` | 8-12K words | 🔲 Planned | Phase 1 |
| `modules/pr-workflow.md` | 5-8K words | 🔲 Planned | Phase 1 |
| `modules/metamodel-patterns.md` | 8-12K words | 🔲 Planned | Phase 2 |
| `modules/testing-standards.md` | 6-10K words | 🔲 Planned | Phase 2 |
| `modules/architecture-decisions.md` | 10-15K words | 🔲 Planned | Phase 2 |

## Scenario-to-Module Routing

### When Working on Generators
**Load:** `modules/generator-development.md`  
**Keywords:** generator, visitor, code generation, metamodel API, NoComment, generated code  
**Core coverage:** Basic metamodel-first principle and generator run commands  
**Module adds:** Detailed patterns for all generator types, testing generators, common pitfalls [PHASE 1]

### When Creating/Reviewing Pull Requests
**Load:** `modules/pr-workflow.md`  
**Keywords:** PR, pull request, review, feedback, scope, maintainer, submission  
**Core coverage:** Basic scope discipline and pre-PR checklist  
**Module adds:** Detailed communication patterns, feedback response strategies, PR structuring [PHASE 1]

### When Modifying Metamodel or AST Nodes
**Load:** `modules/metamodel-patterns.md`  
**Keywords:** metamodel, AST node, new node, field, annotation, ALL_NODE_CLASSES, BaseNodeMetaModel  
**Core coverage:** Node ordering rule and 15-step process summary  
**Module adds:** Detailed metamodel internals, annotation effects, inheritance patterns [PHASE 2]

### When Writing Tests
**Load:** `modules/testing-standards.md`  
**Keywords:** test, JUnit, BDD, assertion, mock, test naming, coverage  
**Core coverage:** Basic test requirements and behavioral contract testing  
**Module adds:** Test organization, fixture patterns, parametric testing, integration tests [PHASE 2]

### When Making Architectural Decisions
**Load:** `modules/architecture-decisions.md`  
**Keywords:** design, architecture, visitor pattern, module boundary, abstraction, API evolution  
**Core coverage:** Module boundaries table and self-referential build  
**Module adds:** Historical decisions, visitor pattern variants, API compatibility rules [PHASE 2]

## Pattern Categories

### Process Patterns
- PR creation workflow → core-principles §7 + pr-workflow.md [PHASE 1]
- Code formatting protocol → quick-reference §Build Commands
- CI failure recovery → quick-reference §Decision Trees
- Maintainer feedback response → core-principles §10 + pr-workflow.md [PHASE 1]

### Technical Patterns
- Metamodel API usage → core-principles §1 + metamodel-patterns.md [PHASE 2]
- Generator development → quick-reference §Generator Modification + generator-development.md [PHASE 1]
- Grammar modification → quick-reference §Adding New AST Node (Step 10)
- Visitor implementation → generator-development.md [PHASE 1]

### Architecture Patterns
- Build pipeline → core-principles §2
- Module boundaries → core-principles §8
- Node class design → metamodel-patterns.md [PHASE 2]
- API evolution → architecture-decisions.md [PHASE 2]

### Quality Patterns
- Test writing → core-principles §7 + testing-standards.md [PHASE 2]
- Assertion design → quick-reference §Common Mistakes
- Code review preparation → quick-reference §Pre-PR Checklist
- Evidence documentation → core-principles §7

## Gap Areas for Phase 1-3

### High Priority Gaps (Phase 1)
- [ ] **Generator internals:** How each generator type works (Property, Accept, Clone, Replace, etc.)
- [ ] **Visitor pattern variants:** Generic, Void, Modifying visitor differences
- [ ] **PR communication:** Optimal PR descriptions, responding to feedback
- [ ] **Maintainer relationship:** Building trust through contribution patterns

### Medium Priority Gaps (Phase 2)
- [ ] **Metamodel deep dive:** How metamodel introspection works internally
- [ ] **Testing best practices:** Integration testing, BDD patterns, fixture management
- [ ] **Architecture history:** Why decisions were made, evolution over time
- [ ] **Symbol solver patterns:** Resolution strategies, scoping rules
- [ ] **Lexical preserving printer:** CSM patterns, transformation rules

### Lower Priority Gaps (Phase 3)
- [ ] **Performance patterns:** Optimization approaches in parsing/resolution
- [ ] **API compatibility:** Versioning strategy, deprecation patterns
- [ ] **Community engagement:** Issue triage, feature discussion, roadmap participation
- [ ] **Release process:** Version management, changelog generation

## Quick Keyword Lookup

| Keyword | Go To |
|---------|-------|
| metamodel | core-principles §1, §3 |
| generator | quick-reference §Generator Modification |
| test | quick-reference §Pre-Commit Checklist, core-principles §7 |
| PR / pull request | quick-reference §Pre-PR Checklist, core-principles §7, §10 |
| new node | quick-reference §Adding New AST Node |
| formatting | quick-reference §Build Commands, §Decision Trees |
| CI failure | quick-reference §Decision Trees |
| copyright | core-principles §6 |
| scope | core-principles §7 |
| annotation | core-principles §4 |
| build | core-principles §2, quick-reference §Build Commands |
| visitor | quick-reference §Generator Modification |
| style | core-principles §5 |
