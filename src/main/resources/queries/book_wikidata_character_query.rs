SELECT  (SAMPLE(?c) AS ?character)
WHERE {
    <$URI> <http://www.wikidata.org/prop/direct/P674> ?x .
    ?x rdfs:label ?c .
    FILTER (langMatches( lang(?c), "en" ) )
}
GROUP BY ?x