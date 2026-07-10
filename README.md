# cloud-itonami-iso3166-usa

Open ISO 3166 Blueprint for **USA**: the United States — **`:implemented`**.

Independent public-sector market-entry & procurement-compliance service
for an already-incorporated operator entering U.S. public contracts
(SAM.gov / FAR / EIN / Buy American / SBA set-asides).

## Implementation (R0)

| Piece | Location |
|---|---|
| Actor | `src/marketentry/*` |
| Governor | `:market-entry-compliance-governor` |
| Flagship HARD | `sam-uei-unverified` (SAM Unique Entity ID) |
| Tests | `clojure -M:dev:test` |
| Demo | `clojure -M:dev:run` |

`:filing/submit` is never auto-committed at any phase.

## What this is NOT

- **Not the government of the United States.** Commercial market-entry
  compliance for operators who bid into government, never the government.

## License

AGPL-3.0-or-later.
