SELECT DISTINCT ?$NAME
WHERE {
    ?artist dbp:name ?$NAME .
    ?artist dbo:birthDate ?birthdate .
    <$TARGET> dbo:birthDate ?target_birth .

    FILTER(ABS(year(?birthdate) - year(?target_birth)) < 20)
    FILTER(<$TARGET> != ?artist)

    OPTIONAL {<$TARGET> dbo:birthPlace ?target_birthplace .}
    OPTIONAL {?target_birthplace dbo:country ?target_country .}
    OPTIONAL {?artist dbo:birthPlace ?birthplace .}
    OPTIONAL {?birthplace dbo:country ?country .}

    OPTIONAL {?artist dbp:nationality ?nationality .}
    OPTIONAL {<$TARGET> dbp:nationality ?target_nationality .}

    BIND(IF(BOUND(?nationality) && BOUND(?target_nationality), 1, 0) AS ?use_nationality)
    BIND(IF(?use_nationality = 0 && BOUND(?country) && BOUND(?target_country), 1, 0) AS ?use_country)

    FILTER(?use_nationality = 1 || ?use_country = 1)
    FILTER(?use_nationality = 0 || ?target_nationality = ?nationality)
    FILTER(?use_country = 0 || ?country = ?target_country)
}