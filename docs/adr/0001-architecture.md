# ADR-0001: Architecture — United States market-entry compliance actor (`marketentry`)

**Status**: accepted
**Date**: 2026-07-10

## Decision

Promote `cloud-itonami-iso3166-usa` from `:blueprint` to `:implemented`
by forking the JPN `marketentry` actor (ADR-2607105300) and swapping
jurisdiction facts/demo data.

### HARD checks

1. spec-basis (SAM.gov / FAR G2 citations)
2. evidence-incomplete
3. **sam-uei-unverified** (FLAGSHIP) — Unique Entity ID required for federal awards
4. engagement-fee-mismatch
5. **ein-unverified** (conditional) — IRS Employer Identification Number
6. already-drafted / already-submitted

`:filing/submit` never auto-commits at any phase.
