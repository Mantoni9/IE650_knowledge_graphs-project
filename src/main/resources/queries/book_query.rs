SELECT ?$BOOK_AUTHOR ?$BOOK_NAME ?$BOOK_DATE
WHERE
{
  {
    SELECT ?book (SAMPLE(?inner_author_name) AS ?$BOOK_AUTHOR) (SAMPLE(?inner_name) AS ?$BOOK_NAME) (SAMPLE(?inner_date) as ?$BOOK_DATE)
    WHERE
      { ?book a dbo:Book ;
          dbp:name ?inner_name ;
          dbp:author ?inner_author .
          ?inner_author dbp:name ?inner_author_name
          OPTIONAL { ?book dbp:publicationDate ?publicationDate . FILTER( isLiteral(?publicationDate) && datatype(?publicationDate) = xsd:date)}
          OPTIONAL { ?book dbp:releaseDate ?releaseDate . FILTER( isLiteral(?releaseDate ) && datatype(?releaseDate ) = xsd:date)}
          OPTIONAL { ?book dbp:published ?published . FILTER( isLiteral(?published ) && datatype(?published ) = xsd:date)}
          OPTIONAL { ?book dbo:publicationDate ?dboPublicationDate . FILTER( isLiteral(?dboPublicationDate ) && datatype(?dboPublicationDate ) = xsd:date)}
          FILTER (bound(?releaseDate) || bound(?publicationDate) || bound(?published) || bound(?dboPublicationDate))
          bind(COALESCE(?publicationDate, ?releaseDate, ?published, ?dboPublicationDate) as ?inner_date)
      }
    GROUP BY ?book
    }
    {
      SELECT ?book (COUNT(?x) as ?countX)
      WHERE
        {
          ?book a dbo:Book .
          ?x dbo:wikiPageWikiLink ?book .
        }
       GROUP BY ?book
    }
}
ORDER BY DESC(?countX)
LIMIT $LIMIT