# Source Code Analysis Observations

**Date:** July 10, 2026  
**Files Analyzed:** 11 representative source files covering AST nodes, generators, metamodel, tests, and parsing

## Key Implementation Patterns Discovered

| Pattern | Where | Key Insight |
|---------|-------|-------------|
| `@Generated` marks | AST nodes | Never edit generated methods |
| Fluent setters | AST nodes | All setters return `this` |
| Observer pattern | AST nodes | `notifyPropertyChange()` on every mutation |
| Tree integrity | AST nodes | Parent-child managed in setters automatically |
| Package-private tests | Test files | No `public` on test classes/methods |
| Static parse methods | Tests | `StaticJavaParser.parseXxx()` for simple cases |
| `f()` format helper | Generators | Use instead of String.format() |
| `assertNotNull` | Everywhere | Project's own Utils.assertNotNull, not java.util |
| ParseResult (no throw) | Parser | Parsing never throws for bad input, collects Problems |
| Grammar → AST | java.jj | Productions build AST nodes with range(begin, token()) |
| Language validators | Validators | Negative in 1.0, remove in appropriate version |

## Contradictions Between Documentation and Code

1. **Copyright header variation** — Older files have dual author credit (Gesser + Team). New files use team-only.
2. **assertNotNull source** — Must use project's own `Utils.assertNotNull`, not documented explicitly.
3. **Test utilities undocumented** — `TestUtils.assertEqualsStringIgnoringEol()` widely used but nowhere mentioned.
4. **Generated vs manual boundary** — Not documented which node methods are hands-off.

None require maintainer clarification — all gaps incorporated into steering guide.
