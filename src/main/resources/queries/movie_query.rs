SELECT ?$URI ?$BUDGET (GROUP_CONCAT(DISTINCT ?director_name, ", ") AS ?$DIRECTORS) ?$GROSS ?$NAME (GROUP_CONCAT(?actor_name, ", ") AS ?$ACTORS)
WHERE {
  ?$URI a dbo:Film .
  ?$URI dbo:$BUDGET ?$BUDGET .
  ?$URI dbo:director ?director .
  ?director dbp:name ?director_name .
  ?$URI dbo:$GROSS ?$GROSS .
  ?$URI rdfs:label ?$NAME .
  ?$URI dbo:starring ?actor .
  ?actor dbp:name ?actor_name .
  FILTER(datatype(?$GROSS) = <http://dbpedia.org/datatype/usDollar>)
  FILTER(REGEX(str(?$GROSS), "[0-9].[0-9]{1,}E(8|9)"))
  FILTER (langMatches( lang(?$NAME), "en" ) )
  FILTER(!CONTAINS(STR(?$URI), "series"))
  FILTER(!CONTAINS(STR(?$URI), "franchise"))
}
ORDER BY DESC(?$GROSS)