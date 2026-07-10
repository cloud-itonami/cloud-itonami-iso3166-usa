(ns marketentry.facts
  "Per-jurisdiction public-procurement market-entry regulatory catalog
  for the United States market-entry actor. G2-style spec-basis table
  the Market-Entry Compliance Governor checks every
  `:jurisdiction/assess` proposal against.

  This blueprint's own text (docs/business-model.md) names the U.S.
  surface: SAM.gov entity registration + NAICS self-certification,
  federal EIN, state business registration, Buy American / TAA content
  thresholds on certain contracts, and SBA socio-economic set-asides.
  Coverage is reported HONESTLY (see `coverage`).")

(def catalog
  {"USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + Unique Entity ID (UEI) + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                              "SAM.gov registration record"
                              "State business registration record"
                              "SAM UEI verification record"]
          :rep-owner-authority "GSA / SAM.gov entity administrators"
          :rep-legal-basis "2 CFR / FAR entity validation — Unique Entity ID (UEI) required for federal awards"
          :rep-provenance "https://sam.gov/"
          :corporate-number-owner-authority "Internal Revenue Service (IRS)"
          :corporate-number-legal-basis "Employer Identification Number (EIN)"
          :corporate-number-provenance "https://www.irs.gov/businesses/small-businesses-self-employed/apply-for-an-employer-identification-number-ein-online"}
   "JPN" {:name "Japan"
          :owner-authority "デジタル庁 / 全省庁統一資格 審査機関"
          :legal-basis "全省庁統一資格 / GEPS"
          :national-spec "unified central-government tender qualification"
          :provenance "https://www.chotatujoho.go.jp/va/com/ShikakuTop.html"
          :required-evidence ["法人番号確認記録"
                              "全省庁統一資格申請記録"
                              "GEPS 事業者登録記録"
                              "日本居住代理人確認記録"]}
   "DEU" {:name "Germany"
          :owner-authority "e-Vergabe platforms"
          :legal-basis "GWB / VgV"
          :national-spec "e-Vergabe supplier registration"
          :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract"
                              "e-Vergabe registration record"
                              "USt-IdNr record"
                              "Authorized-representative record"]}
   "GBR" {:name "United Kingdom"
          :owner-authority "Crown Commercial Service / Find a Tender"
          :legal-basis "Public Contracts Regulations 2015"
          :national-spec "Find a Tender Service registration"
          :provenance "https://www.find-tender.service.gov.uk/"
          :required-evidence ["Companies House record"
                              "Find a Tender registration record"
                              "VAT registration record"
                              "Authorized-representative record"]}})

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
      :note (str "cloud-itonami-iso3166-usa R0: " (count catalog)
                 " jurisdictions seeded. Extend marketentry.facts/catalog, never fabricate.")})))

(defn required-evidence-satisfied?
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))
