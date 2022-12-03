SELECT ?$URI ?$BOOK_AUTHOR ?$AUTHOR_NAME ?$BOOK_NAME ?$BOOK_DATE ?$GENRES ?countX ?countY
WHERE
{
  {
    SELECT ?$URI (SAMPLE(?inner_author) AS ?$BOOK_AUTHOR) (SAMPLE(?inner_author_name) AS ?$AUTHOR_NAME) (SAMPLE(?inner_name) AS ?$BOOK_NAME) (SAMPLE(?inner_date) as ?$BOOK_DATE) (GROUP_CONCAT(DISTINCT(?inner_genre_label); separator=",") AS ?$GENRES)
    WHERE
      { ?$URI a dbo:Book ;
          dbp:name ?inner_name .

          OPTIONAL { ?$URI dbp:author ?dbp_inner_author .
          ?dbp_inner_author dbp:name ?dbp_inner_author_name . }
          OPTIONAL { ?$URI dbo:author ?dbo_inner_author .
          ?dbo_inner_author dbp:name ?dbo_inner_author_name . }
          FILTER(bound(?dbp_inner_author) || bound(?dbo_inner_author))
          bind(COALESCE(?dbp_inner_author, ?dbo_inner_author) AS ?inner_author)
          bind(COALESCE(?dbp_inner_author_name, ?dbo_inner_author_name) AS ?inner_author_name)

          OPTIONAL {
            ?$URI dbp:genre ?inner_genre .
            ?inner_genre rdfs:label ?inner_genre_label
            FILTER (langMatches( lang(?inner_genre_label), "en" ) )
          }

          OPTIONAL { ?$URI dbp:publicationDate ?publicationDate . FILTER( isLiteral(?publicationDate) && datatype(?publicationDate) = xsd:date)}
          OPTIONAL { ?$URI dbp:releaseDate ?releaseDate . FILTER( isLiteral(?releaseDate ) && (datatype(?releaseDate ) = xsd:date) || (datatype(?releaseDate ) = xsd:integer))}
          OPTIONAL { ?$URI dbp:published ?published . FILTER( isLiteral(?published ) && datatype(?published ) = xsd:date)}
          OPTIONAL { ?$URI dbo:publicationDate ?dboPublicationDate . FILTER( isLiteral(?dboPublicationDate ) && datatype(?dboPublicationDate ) = xsd:date)}
          FILTER (bound(?releaseDate) || bound(?publicationDate) || bound(?published) || bound(?dboPublicationDate))
          bind(COALESCE(?publicationDate, ?releaseDate, ?published, ?dboPublicationDate) as ?inner_date)
      }
    GROUP BY ?$URI
    }
    {
      SELECT ?$URI (COUNT(?x) as ?countX)
      WHERE
        {
          ?$URI a dbo:Book .
          ?x dbo:wikiPageWikiLink ?$URI .
        }
       GROUP BY ?$URI
    }
    {
    SELECT ?$URI (COUNT(?y) as ?countY)
      WHERE
        {
          ?$URI a dbo:Book .
          ?$URI dbo:wikiPageWikiLink ?y .
        }
       GROUP BY ?$URI
    }
}
ORDER BY DESC(?countX + ?countY)
LIMIT $LIMIT