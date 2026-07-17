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

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for the United States:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
