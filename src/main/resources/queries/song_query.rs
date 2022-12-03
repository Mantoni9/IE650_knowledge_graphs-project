SELECT ?$URI ?$ARTIST_NAME ?$SONG_NAME ?$RELEASE_DATE
WHERE {
    ?$URI a dbo:Song .
    ?$URI dbo:artist ?artist .
    ?artist dbp:name ?$ARTIST_NAME .
    ?$URI dbp:award ?award .
    ?$URI dbp:name ?$SONG_NAME .
    ?$URI dbo:releaseDate ?$RELEASE_DATE .

    FILTER (STR(?award)="Platinum")
    FILTER(!CONTAINS(STR(?$URI), "album"))
}
ORDER BY DESC(?$RELEASE_DATE)