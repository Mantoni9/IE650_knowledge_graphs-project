SELECT DISTINCT ?$NAME ?$GENRE
WHERE {
    ?work dbp:author ?author .
    ?author dbp:name ?$NAME .
    ?author dbo:birthDate ?$BIRTHDATE .
    <$TARGET> dbo:birthDate ?target_birth .
    ?author dbp:nationality ?$NATIONALITY .
    <$TARGET> dbp:nationality ?target_nationality .
    BIND("genre" AS ?$GENRE)
    FILTER(?$NATIONALITY = ?target_nationality)
    FILTER(ABS(year(?$BIRTHDATE) - year(?target_birth)) < 20)
    FILTER(<$TARGET> != ?author)
}
LIMIT 100