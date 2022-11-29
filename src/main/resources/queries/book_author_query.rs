SELECT DISTINCT ?$NAME
WHERE {
    ?work dbp:author ?author .
    ?author dbp:name ?$NAME .
    ?author dbo:birthDate ?birthdate .
    <$TARGET> dbo:birthDate ?target_birth .

    FILTER(ABS(year(?birthdate) - year(?target_birth)) < 20)
    FILTER(<$TARGET> != ?author)

    OPTIONAL {<$TARGET> dbo:birthPlace ?target_birthplace .}
    OPTIONAL {?target_birthplace dbo:country ?target_country .}
    OPTIONAL {?author dbo:birthPlace ?birthplace .}
    OPTIONAL {?birthplace dbo:country ?country .}

    OPTIONAL {?author dbp:nationality ?nationality .}
    OPTIONAL {<$TARGET> dbp:nationality ?target_nationality .}

    BIND(IF(BOUND(?nationality) && BOUND(?target_nationality), 1, 0) AS ?use_nationality)
    BIND(IF(?use_nationality = 0 && BOUND(?country) && BOUND(?target_country), 1, 0) AS ?use_country)

    FILTER(?use_nationality = 1 || ?use_country = 1)
    FILTER(?use_nationality = 0 || ?target_nationality = ?nationality)
    FILTER(?use_country = 0 || ?country = ?target_country)
}