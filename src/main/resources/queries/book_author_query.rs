SELECT ?author ?$NAME COUNT(DISTINCT(?y)) ?z
WHERE
    {
        ?y dbo:wikiPageWikiLink ?author .
        {
        SELECT (COUNT(?x) as ?z)
        WHERE
          {
            ?x dbo:wikiPageWikiLink <$TARGET> .
          }
        }
        {
        SELECT DISTINCT ?author ?$NAME
        WHERE {
            ?work dbp:author ?author .
            ?author dbp:name ?$NAME .
            ?author dbo:birthDate ?birthdate .
            <$TARGET> dbo:birthDate ?target_birth .

            FILTER(ABS(year(?birthdate) - year(?target_birth)) < 20)
            FILTER(<$TARGET> != ?author)

            OPTIONAL {
            <$TARGET> dbo:birthPlace ?target_birthplace .
            ?target_birthplace dbo:country ?target_country .
            ?author dbo:birthPlace ?birthplace .
            ?birthplace dbo:country ?country .
            }

            OPTIONAL {?author dbp:nationality ?nationality .
            <$TARGET> dbp:nationality ?target_nationality .}

            BIND(IF(BOUND(?nationality) && BOUND(?target_nationality), 1, 0) AS ?use_nationality)
            BIND(IF(?use_nationality = 0 && BOUND(?country) && BOUND(?target_country), 1, 0) AS ?use_country)

            FILTER(?use_nationality = 1 || ?use_country = 1)
            FILTER(?use_nationality = 0 || ?target_nationality = ?nationality)
            FILTER(?use_country = 0 || ?country = ?target_country)
        }
    }
}
GROUP BY ?author ?$NAME ?z
ORDER BY ABS(COUNT(?y) - ?z)
LIMIT 5