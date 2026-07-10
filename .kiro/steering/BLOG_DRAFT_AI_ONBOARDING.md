# Onboarding Your AI Pair Programmer: Extending Spec-Driven Development to AI Team Members

## The Problem Nobody Talks About

Every developer who's used an AI coding assistant has experienced this: you ask it to contribute to your project and it produces something that *works* but doesn't *belong*. The code compiles, the tests pass, but a seasoned contributor would never write it that way. The AI doesn't know your project's conventions, architectural decisions, or the unwritten rules that make a contribution feel native.

We solved this problem for human contributors decades ago. It's called onboarding.

## The Insight: AI Needs Onboarding Too

When a new developer joins a project, they don't start writing code immediately. They:

1. **Read the documentation** — CONTRIBUTING.md, wiki pages, architecture guides
2. **Study merged PRs** — Understanding "the way things are done"
3. **Observe rejected PRs** — Learning what NOT to do
4. **Internalize patterns** — Building a mental model of project culture
5. **Apply knowledge** — Contributing effectively with fewer review cycles

An AI assistant that skips this process is like a contractor who starts coding without reading the project docs. Technically capable, but culturally oblivious.

**The difference:** A human stores onboarding knowledge in memory. An AI needs it stored in structured documentation that persists between sessions.

## Spec-Driven Development Meets AI Memory

If you practice spec-driven development — writing requirements, design documents, and quality criteria before implementation — you already have the mindset for this. AI onboarding is simply extending that discipline to cover *how your AI team member should think about your project*.

The deliverable isn't code. It's a **steering documentation system** — a structured knowledge base that transforms your AI from a generic code generator into a project-aware contributor.

## The Approach: A Modular Steering System

We developed this approach while contributing to JavaParser, a 6,000+ star open source project with strict architectural standards. After receiving maintainer feedback on our first PR that highlighted code quality issues (not bugs — the code worked, but it bypassed project abstractions), we realized: the AI needed to be onboarded properly.

### The Architecture

```
.kiro/steering/
├── core-principles.md      ← Always loaded. Non-negotiable rules.
├── quick-reference.md      ← Always loaded. Common scenarios.
├── pattern-index.md        ← Always loaded. Navigation system.
└── modules/                ← Loaded on demand per context.
    ├── generator-development.md
    ├── pr-workflow.md
    └── ...
```

**Three layers:**

1. **Core Principles** (~2-3K words) — The non-negotiable rules that apply to every contribution. Architecture patterns, code style requirements, process essentials. This is loaded in every session.

2. **Quick Reference** (~3-5K words) — Actionable guidance for common scenarios. Step-by-step workflows, checklists, decision trees, anti-patterns with corrections. Also always loaded.

3. **On-Demand Modules** (~8-15K words each) — Deep knowledge for specific contexts. Only loaded when relevant, keeping the AI's context window efficient for actual work.

### Memory Efficiency

With a 1M token context window, the always-loaded core takes ~0.35% of available context. Even with an on-demand module loaded, total steering overhead stays under 3%. The AI has 97%+ of its capacity available for actual work.

Compare this to a human: you can't "unload" your project knowledge when working. The AI's modular memory is actually more efficient than ours.

## The Process: Phased Onboarding

### Phase 0: Documentation Study (~1 session)

Read all existing project documentation and extract patterns:
- Contributing guidelines, README, wiki pages
- CI/CD configurations (reveals automated quality gates)
- Build scripts (reveals process dependencies)
- Representative source files (reveals implementation patterns)

**Deliverable:** Working steering system v0.1 — immediately usable.

### Phase 1: Recent PR Analysis (~2-3 sessions)

Study 50-100 recent merged PRs to learn implicit patterns:
- Maintainer feedback themes (what gets called out repeatedly)
- Success patterns (what gets approved smoothly)
- Communication norms (how discussions are conducted)

**Deliverable:** Priority modules for highest-impact areas.

### Phase 2: Deep Historical Analysis (~3-4 sessions)

Comprehensive pattern extraction from project history:
- Architectural decisions and their rationale
- Pattern evolution over time
- Anti-patterns from rejected approaches

**Deliverable:** Complete module library covering all major contribution scenarios.

### Phase 3: Community Integration (~1-2 sessions)

Optimization and publication for broader adoption:
- Module cross-linking and performance optimization
- Integration with project's existing documentation
- Maintenance procedures and community contribution guidelines
- Validation through real-world contributions

**Deliverable:** Published, maintainer-endorsed community resource with maintenance framework.

## Quality Assurance: How Do You Know It Works?

This is where spec-driven thinking pays off. We defined quality gates before starting:

- **Source Validation** — Only official, maintainer-authored content informs the guide
- **Pattern Consistency** — No contradictory guidance across modules
- **Coverage Baseline** — Gaps explicitly identified and tracked
- **Usability Test** — Simulate a real contribution scenario using only the guide

The usability test is the critical one. After creating our steering system, we walked through our actual PR scenario (fixing a visitor generator) using only the guide. Every decision point was covered. Every common mistake was documented with corrections.

## Real Results (Phase 0)

Before onboarding:
- Maintainer flagged 8 issues across code quality, test quality, and scope management
- Multiple review cycles needed
- Time spent learning project patterns through trial and error

After completing Phase 0:
- Guide covers all 8 feedback patterns that were raised
- Metamodel-first principle is the first thing the AI checks
- Scope discipline is enforced at every decision point
- Evidence requirements built into workflow

The Phase 0 investment: ~17 credits (about 1 focused session).
The estimated total investment (Phase 0-3): ~2,000 credits across 7-10 sessions.
The return: Every future session starts with "experienced contributor" knowledge, improving further with each phase.

## The Broader Vision: AI as a Real Team Member

This isn't about making AI produce better code in isolation. It's about making AI a **real team member** — one that:

- Understands the project's architecture and respects it
- Follows established patterns without being told each time
- Communicates with maintainers in appropriate style
- Makes decisions consistent with project history
- Gets better over time as new patterns are learned

When you onboard a human contributor, you invest days or weeks. The return is years of productive contribution. AI onboarding takes hours. The return is immediate and perpetual — the knowledge never degrades, never gets forgotten, and can be shared with every contributor who uses AI assistance.

## Getting Started

If you want to onboard your AI assistant to your project:

**Minimal approach (30 minutes):**
```
Read all my project documentation and create .kiro/steering/ with:
1. core-principles.md - Non-negotiable project rules
2. quick-reference.md - Common scenarios with guidance
3. pattern-index.md - Keyword lookup and gap tracking

Validate by simulating one realistic contribution scenario.
```

**Full approach (phased program):**
Start with documentation analysis, extend through PR pattern extraction, and continuously refine through real contribution experience.

**The key insight:** Your AI assistant's quality ceiling isn't its model capability — it's how well it understands your specific project. Onboarding closes that gap.

## Conclusion: Work in Progress

Spec-driven development taught us to think before we code. AI onboarding extends that principle: **think about what your AI needs to know before it contributes.**

We completed Phase 0 and are now gathering real-world experience using the steering system during actual development. The questions we're exploring:

- Does the guide reduce maintainer feedback cycles in practice?
- Which gaps surface first during real contributions?
- Is the modular architecture valuable, or is the core system sufficient for most work?
- When does the investment in Phase 1+ pay for itself?

Based on these experiences, we'll decide whether and when to proceed with Phase 1 (PR pattern analysis), Phase 2 (comprehensive historical extraction), and Phase 3 (community publication). The phased approach means we can stop at any point with a useful deliverable — there's no all-or-nothing commitment.

The result isn't just better AI output. It's a reusable knowledge artifact that benefits every contributor — human or AI — who works on your project. It's institutional knowledge preservation. It's accelerated onboarding. It's quality at scale.

Your AI pair programmer is ready to learn. The question is whether you'll invest in teaching it your project's way of working, or keep accepting "works but doesn't belong" code forever.

---

*The steering documentation system and onboarding methodology described in this post were developed during contributions to the JavaParser open source project. The approach is language-agnostic and applicable to any codebase with established conventions. We are actively using and refining this approach — follow-up posts will share what we learn from real-world usage.*
