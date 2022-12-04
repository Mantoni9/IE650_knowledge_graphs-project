PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wds: <http://www.wikidata.org/entity/statement/>
PREFIX wdv: <http://www.wikidata.org/value/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wds: <http://www.wikidata.org/entity/statement/>
PREFIX wdv: <http://www.wikidata.org/value/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>
SELECT DISTINCT  ?country ?countryLabel (SAMPLE(?inner_capitalLabel) AS ?capital) (SAMPLE(?inner_continentLabel) AS ?continentLabel)
                 (MAX(?area) AS ?maxArea) (MAX(?population) AS ?maxPop) (SAMPLE(?inner_headLabel) as ?head) ?hdi ?drivingSideLabel
WHERE
  { ?country  wdt:P31  wd:Q3624078
    FILTER NOT EXISTS { ?country  wdt:P31  wd:Q3024240 }
    FILTER NOT EXISTS { ?country  wdt:P31  wd:Q28171280 }
    ?country  wdt:P2046   ?area ;
              wdt:P1082   ?population ;
              wdt:P36     ?inner_capital ;
              wdt:P30     ?inner_continent ;
              wdt:P6      ?inner_head ;
              wdt:P1081   ?hdi ;
              wdt:P1622   ?drivingSide .
    ?inner_continent rdfs:label  ?inner_continentLabel .
    ?inner_capital rdfs:label  ?inner_capitalLabel .
    ?inner_head rdfs:label ?inner_headLabel .
    FILTER langMatches(lang(?inner_continentLabel), "en")
    FILTER langMatches(lang(?inner_capitalLabel), "en")
    FILTER(lang(?inner_headLabel) = "en")
    SERVICE wikibase:label
      { bd:serviceParam
                  wikibase:language  "en"
      }
  }
GROUP BY ?country ?countryLabel ?hdi ?drivingSideLabel
HAVING ( COUNT(DISTINCT ?inner_capital) = 1)
ORDER BY DESC(?maxPop)