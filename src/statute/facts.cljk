(ns statute.facts
  "General-law compliance catalog for the United States (USA) -- extends
  this repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of federal statutes
  a company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn's `statute.facts` (ADR-2607141700,
  cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL uscode.house.gov (Office of the Law
  Revision Counsel) URL -- never fabricated. A law not in this table has
  NO spec-basis, full stop; extend `catalog`, do not invent an id/url.
  Unlike Japan's single-statute e-Gov citations, U.S. federal law is
  organized by Title/Chapter, so `:statute/url` here points at the
  chapter-level official text (the smallest stable, independently
  corroborated citation available -- individual sections within a
  chapter were not each independently verified).")

(def catalog
  "iso3 -> vector of statute entries."
  {"USA"
   [{:statute/id "usa.sarbanes-oxley"
     :statute/title "Sarbanes-Oxley Act of 2002 (Public Company Accounting Oversight and Corporate Responsibility)"
     :statute/jurisdiction "USA"
     :statute/kind :law
     :statute/law-number "Public Law 107-204, 15 U.S.C. Chapter 98"
     :statute/url "https://uscode.house.gov/view.xhtml?path=/prelim@title15/chapter98&edition=prelim"
     :statute/url-provenance :official-uscode
     :statute/enacted-date "2002-07-30"
     :statute/retrieved-at "2026-07-14"
     :statute/topic #{:corporate-governance :financial-reporting}}
    {:statute/id "usa.ftc-act-section5"
     :statute/title "Federal Trade Commission Act, Section 5 (Unfair or Deceptive Acts or Practices)"
     :statute/jurisdiction "USA"
     :statute/kind :law
     :statute/law-number "15 U.S.C. § 45"
     :statute/url "https://uscode.house.gov/view.xhtml?req=(title:15+section:45+edition:prelim)"
     :statute/url-provenance :official-uscode
     :statute/retrieved-at "2026-07-14"
     :statute/topic #{:data-protection :privacy :consumer-protection}}
    {:statute/id "usa.fair-labor-standards-act"
     :statute/title "Fair Labor Standards Act of 1938"
     :statute/jurisdiction "USA"
     :statute/kind :law
     :statute/law-number "29 U.S.C. Chapter 8 (§§ 201-219)"
     :statute/url "https://uscode.house.gov/view.xhtml?path=/prelim@title29/chapter8&edition=prelim"
     :statute/url-provenance :official-uscode
     :statute/enacted-date "1938-06-25"
     :statute/retrieved-at "2026-07-14"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-usa statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "USA")) " USA statutes seeded with an "
                 "official uscode.house.gov citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
