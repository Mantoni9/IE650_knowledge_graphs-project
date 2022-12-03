SELECT ?$URI SAMPLE(?$BUDGET) (GROUP_CONCAT(DISTINCT ?director_name; separator=",") AS ?$DIRECTORS) ?$GROSS ?$NAME (GROUP_CONCAT(?actor_name; separator=",") AS ?$ACTORS) ?prefixedGross
WHERE {
  ?$URI a dbo:Film .
  ?$URI dbo:budget ?$BUDGET .
  ?$URI dbo:director ?director .
  ?director dbp:name ?director_name .
  ?$URI dbo:$GROSS ?$GROSS .
  ?$URI rdfs:label ?$NAME .
  ?$URI dbo:starring ?actor .
  ?actor dbp:name ?actor_name .
  BIND(STRAFTER(?gross, "E") AS ?exponent)
  BIND(CONCAT(?exponent, ?gross) AS ?prefixedGross)
  FILTER(datatype(?$GROSS) = <http://dbpedia.org/datatype/usDollar>)
  FILTER(REGEX(str(?$GROSS), "[0-9].[0-9]{1,}E(8|9)"))
  FILTER (langMatches( lang(?$NAME), "en" ) )
  FILTER(!CONTAINS(STR(?$URI), "series"))
  FILTER(!CONTAINS(STR(?$URI), "franchise"))
}
GROUP BY ?$URI ?$GROSS ?$NAME ?prefixedGross
ORDER BY DESC(?prefixedGross)
LIMIT 300