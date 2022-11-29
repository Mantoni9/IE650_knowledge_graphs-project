SELECT DISTINCT ?$MOVIE_NAME ?$ACTOR_ONE_URI ?$ACTOR_TWO_URI ?$ACTOR_ONE_NAME ?$ACTOR_TWO_NAME
WHERE {
  ?movie a dbo:Film .
  ?movie dbo:starring ?$ACTOR_ONE_URI .
  ?movie dbo:starring ?$ACTOR_TWO_URI .
  ?movie rdfs:label ?$MOVIE_NAME .
  ?$ACTOR_ONE_URI dbp:name ?$ACTOR_ONE_NAME .
  ?$ACTOR_TWO_URI dbp:name ?$ACTOR_TWO_NAME .
  ?movie dbo:gross ?movieGross .
  FILTER(?$ACTOR_ONE_URI != ?$ACTOR_TWO_URI)
  FILTER(datatype(?movieGross) = <http://dbpedia.org/datatype/usDollar>)
  FILTER (langMatches( lang(?$MOVIE_NAME), "en" ))
  FILTER(REGEX(str(?movieGross), "[0-9].[0-9]{1,}E(8|9)"))
  FILTER (str(?$ACTOR_ONE_URI) < str(?$ACTOR_TWO_URI))
}
ORDER BY DESC(?movieGross)
LIMIT 10000