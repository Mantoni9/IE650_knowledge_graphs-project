SELECT ?wikidataUri
WHERE {
    <$URI> owl:sameAs ?wikidataUri .
    FILTER(STRSTARTS(STR(?wikidataUri), "http://www.wikidata.org/"))
}