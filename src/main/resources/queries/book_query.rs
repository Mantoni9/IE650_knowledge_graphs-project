SELECT ?$URI ?$BOOK_AUTHOR ?$AUTHOR_NAME ?$BOOK_NAME ?$BOOK_DATE
WHERE
{
  {
    SELECT ?$URI (SAMPLE(?inner_author) AS ?$BOOK_AUTHOR) (SAMPLE(?inner_author_name) AS ?$AUTHOR_NAME) (SAMPLE(?inner_name) AS ?$BOOK_NAME) (SAMPLE(?inner_date) as ?$BOOK_DATE)
    WHERE
      { ?$URI a dbo:Book ;
          dbp:name ?inner_name ;
          dbp:author ?inner_author .
          ?inner_author dbp:name ?inner_author_name
          OPTIONAL { ?$URI dbp:publicationDate ?publicationDate . FILTER( isLiteral(?publicationDate) && datatype(?publicationDate) = xsd:date)}
          OPTIONAL { ?$URI dbp:releaseDate ?releaseDate . FILTER( isLiteral(?releaseDate ) && datatype(?releaseDate ) = xsd:date)}
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
}
ORDER BY DESC(?countX)
LIMIT $LIMIT