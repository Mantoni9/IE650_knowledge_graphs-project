SELECT DISTINCT ?$NAME
WHERE {
    ?work dbp:author ?author .
    ?author dbp:name ?$NAME .
    ?author dbo:birthDate ?birthdate .
    <$TARGET> dbo:birthDate ?target_birth .
    ?author dbp:nationality ?nationality .
    <$TARGET> dbp:nationality ?target_nationality .
    BIND("genre" AS ?genre)
    FILTER(?nationality = ?target_nationality)
    FILTER(ABS(year(?birthdate) - year(?target_birth)) < 20)
    FILTER(<$TARGET> != ?author)
}
LIMIT 100