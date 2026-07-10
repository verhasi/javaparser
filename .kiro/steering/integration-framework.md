# Integration Framework

> Procedures for loading, cross-referencing, updating, and maintaining the steering system.

## Cross-Reference Format

### Internal References (Within Core)
```markdown
→ See core-principles §2 (Build Pipeline)
→ See quick-reference §Adding New AST Node
```

### Module References (On-Demand)
```markdown
→ Load modules/generator-development.md for detailed guidance [PHASE 1]
→ See modules/testing-standards.md §Assertion Best Practices [PHASE 2]
```

### Unresolved References (Module Not Yet Created)
```markdown
[PHASE 1] Detailed generator patterns will be available after Phase 1 development
[PHASE 2] Deep metamodel internals planned for Phase 2
```

## Module Loading Protocol

### Session Start
1. Load core-principles.md (always)
2. Load quick-reference.md (always)
3. Load pattern-index.md (always)
4. Assess contribution context from user's first message
5. Load relevant on-demand module(s) if available

### Mid-Session Loading
When context shifts during a session:
1. Consult pattern-index.md keyword lookup
2. Identify relevant module
3. Load module if not already in context
4. Maximum 2 on-demand modules simultaneously

### Loading Decision Matrix
| User Context | Load Module | Reason |
|-------------|-------------|--------|
| "fix the generator" | generator-development.md | Technical generator work |
| "create a PR" | pr-workflow.md | Submission process |
| "add a new node" | metamodel-patterns.md | Metamodel work |
| "write tests for" | testing-standards.md | Test development |
| "why is it designed this way" | architecture-decisions.md | Design understanding |
| "fix a bug" | (none - core sufficient) | Standard workflow |
| "format failed in CI" | (none - core sufficient) | Covered in quick-ref |

## Update Procedures

### When to Update Modules
- **Immediate:** Maintainer contradicts current guidance
- **After PR Merge:** New patterns learned from successful contribution
- **After PR Rejection:** Anti-patterns discovered from failed attempt
- **Quarterly:** Review for staleness against recent project activity

### How to Update

#### Minor Update (Fix/Clarification)
```bash
# Edit the specific module
# Update version number in header
# Verify no cross-reference breaks
```

#### Major Update (New Section/Pattern)
```bash
# Check module size constraint (15K words max)
# Add new section with proper heading
# Update pattern-index.md keyword lookup
# Update module-architecture.md if scope changes
# Verify cross-references still valid
```

#### New Module Creation
```bash
# Create file in .kiro/steering/modules/
# Add entry to pattern-index.md Module System Overview
# Add routing rules to pattern-index.md Scenario-to-Module Routing
# Add keywords to pattern-index.md Quick Keyword Lookup
# Update module-architecture.md specifications
# Update integration-framework.md Loading Decision Matrix
```

## Consistency Validation Rules

### Cross-Module Consistency
- No two modules should give contradictory guidance
- Technical terms used consistently across all modules
- Code examples follow same style in all modules
- Anti-pattern descriptions match across references

### Version Consistency
- All modules reference the same project version/state
- Deprecated guidance is removed or clearly marked
- New project standards are reflected across relevant modules

### Completeness Checks
- Every scenario in pattern-index maps to existing guidance
- Every keyword in lookup table leads to relevant content
- Every [PHASE X] tag has corresponding entry in module-architecture.md

## Community Contribution Guidelines

### For Module Updates
1. Identify which module needs updating
2. Check if update fits within module scope
3. Verify update doesn't contradict other modules
4. Ensure size constraint is maintained
5. Update cross-references as needed

### For New Pattern Discovery
1. Identify the pattern from PR feedback or experience
2. Determine which module it belongs to
3. Formulate as: trigger → actions → validation → anti-patterns
4. Add to appropriate module
5. Update pattern-index.md routing

### Quality Criteria for Updates
- Pattern must be supported by concrete evidence (PR, maintainer comment)
- Guidance must be actionable (not just descriptive)
- Anti-patterns must reference real mistakes (not hypothetical)
- Cross-references must be accurate and current

## Quality Gates

### Phase 0 Completion ✅
- [x] Core principles documented from official sources
- [x] Quick reference covers common scenarios
- [x] Pattern index provides navigation structure
- [x] Module architecture designed for Phase 1-3
- [x] Integration framework established

### Phase 1 Completion Criteria
- [ ] generator-development.md created and validated
- [ ] pr-workflow.md created and validated
- [ ] Patterns extracted from 100+ recent PRs
- [ ] All core module cross-references updated
- [ ] Pattern-index routing tables updated
- [ ] Memory budget verified (<7% for typical scenarios)

### Phase 2 Completion Criteria
- [ ] metamodel-patterns.md created and validated
- [ ] testing-standards.md created and validated
- [ ] architecture-decisions.md created and validated
- [ ] Historical patterns from 400+ PRs
- [ ] Complete cross-module consistency validation
- [ ] All gap areas resolved or documented

### Phase 3 Completion Criteria
- [ ] Community-ready documentation format
- [ ] Maintainer review and endorsement
- [ ] Update procedures tested
- [ ] Real-world validation through successful contributions
- [ ] Maintenance schedule established

## File System Layout

```
.kiro/steering/
├── core-principles.md          ← Always loaded (2.5K words)
├── quick-reference.md          ← Always loaded (4K words)
├── pattern-index.md            ← Always loaded (2K words)
├── module-architecture.md      ← Reference document
├── integration-framework.md    ← Reference document (this file)
├── analysis/
│   └── content-analysis-and-patterns.md  ← Working document
└── modules/
    ├── generator-development.md     [PHASE 1]
    ├── pr-workflow.md               [PHASE 1]
    ├── metamodel-patterns.md        [PHASE 2]
    ├── testing-standards.md         [PHASE 2]
    └── architecture-decisions.md    [PHASE 2]
```

## Maintenance Schedule

### Per-Session
- Verify core modules still aligned with current task
- Note any new patterns discovered during contribution

### Per-Phase
- Validate all cross-references
- Check module sizes against constraints
- Update gap tracking in pattern-index
- Review and incorporate recent PR learnings

### Per-Quarter
- Audit modules against recent project evolution
- Remove deprecated guidance
- Add newly established patterns
- Review community feedback on guide usefulness
