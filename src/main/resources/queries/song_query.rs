SELECT ?$URI ?$ARTIST ?$ARTIST_NAME ?$NAME ?$RELEASE_DATE
WHERE {
    ?$URI a dbo:Song .
    ?$URI dbo:artist ?$ARTIST .
    ?$ARTIST dbp:name ?$ARTIST_NAME .
    ?$URI dbp:award ?award .
    ?$URI rdfs:label ?$NAME .
    ?$URI dbo:releaseDate ?$RELEASE_DATE .

    FILTER (langMatches( lang(?$NAME), "en" ) )
    FILTER (STR(?award)="Platinum")
    FILTER(!CONTAINS(STR(?$URI), "album"))
}
GROUP BY ?$URI ?$NAME ?$RELEASE_DATE
ORDER BY DESC(?$RELEASE_DATE)
LIMIT $LIMIT