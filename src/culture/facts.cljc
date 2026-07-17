(ns culture.facts
  "Country-level regional-culture catalog for the United States (USA) --
  national dishes, protected products, beverages, crafts, festivals and
  heritage sites, per ADR-2607171400 addendum 2 (cloud-itonami-
  municipality-culture-catalog Wave 1, in com-junkawasaki/root). Sibling
  namespace to `marketentry.facts` / `statute.facts` (ADR-2607141700);
  city-level counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"USA"
   [{:culture/id "usa.dish.hamburger"
     :culture/name "Hamburger"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Considered a national dish of the United States; its invention remains disputed, with multiple Americans -- including Louis Lassen, Charlie Nagreen, and Fletcher Davis -- claiming credit for creating the sandwich in the late 1800s and early 1900s."
     :culture/url "https://en.wikipedia.org/wiki/Hamburger"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "usa.dish.hot-dog"
     :culture/name "Hot dog"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Strongly associated with American culture after German sausages were imported to the United States; multiple figures claim credit for putting sausage in a bun, including Harry M. Stevens (1901), though it is not definitively known who started the practice."
     :culture/url "https://en.wikipedia.org/wiki/Hot_dog"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "usa.dish.apple-pie"
     :culture/name "Apple pie"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Although apple pie originated in England and has been eaten throughout Europe for centuries, it became deeply embedded in American identity through the phrase \"as American as apple pie\" and evolved into a symbol of American prosperity and national pride during the 19th and 20th centuries."
     :culture/url "https://en.wikipedia.org/wiki/Apple_pie"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "usa.beverage.bourbon-whiskey"
     :culture/name "Bourbon whiskey"
     :culture/country "USA"
     :culture/kind :beverage
     :culture/summary "Barrel-aged American whiskey made primarily from corn; in 1964 the U.S. Congress adopted a concurrent resolution declaring bourbon \"a distinctive product of the United States\" and sought to prohibit importation of whiskey labeled as bourbon from other countries."
     :culture/url "https://en.wikipedia.org/wiki/Bourbon_whiskey"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "usa.product.jeans"
     :culture/name "Jeans"
     :culture/country "USA"
     :culture/kind :product
     :culture/summary "Trousers made from denim, originally designed as workwear for miners and laborers in the United States, with copper rivets added by Jacob W. Davis and patented by Davis and Levi Strauss in 1873."
     :culture/url "https://en.wikipedia.org/wiki/Jeans"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "usa.festival.thanksgiving"
     :culture/name "Thanksgiving"
     :culture/country "USA"
     :culture/kind :festival
     :culture/summary "Federal holiday in the United States celebrated on the fourth Thursday of November, with origins in 17th-century New England and modern customs including feasting, parades, football games, and charitable activities."
     :culture/url "https://en.wikipedia.org/wiki/Thanksgiving_(United_States)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "usa.heritage.statue-of-liberty"
     :culture/name "Statue of Liberty"
     :culture/country "USA"
     :culture/kind :heritage
     :culture/summary "Colossal neoclassical sculpture on Liberty Island in New York Harbor, designed by French sculptor Frédéric Auguste Bartholdi and dedicated in 1886; designated a UNESCO World Heritage Site in 1984 as a masterpiece of human creative genius and cultural exchange between nations."
     :culture/url "https://en.wikipedia.org/wiki/Statue_of_Liberty"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "usa.heritage.independence-hall"
     :culture/name "Independence Hall"
     :culture/country "USA"
     :culture/kind :heritage
     :culture/summary "Historic Philadelphia building where the Declaration of Independence and U.S. Constitution were debated and adopted; designated a UNESCO World Heritage Site in 1979 for its outstanding universal significance to global democratic ideals."
     :culture/url "https://en.wikipedia.org/wiki/Independence_Hall"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-iso3166-usa culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "USA"))
                 " USA entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
