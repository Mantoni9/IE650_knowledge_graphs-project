SELECT ?book ?$BOOK_AUTHOR ?$BOOK_NAME
WHERE
{
  {
    SELECT ?book (SAMPLE(?inner_author) AS ?$BOOK_AUTHOR) (SAMPLE(?inner_name) AS ?$BOOK_NAME)
    WHERE
      { ?book
          a dbo:Book ;
          dbp:name ?inner_name ;
          dbp:author ?inner_author .
          OPTIONAL { ?book dbp:publicationDate ?publicationDate . }
          OPTIONAL { ?book dbp:releaseDate ?releaseDate . }
          OPTIONAL { ?book dbp:published ?published . }
          FILTER (bound(?releaseDate) || bound(?publicationDate) || bound(?published))
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
GROUP BY ?book ?$BOOK_AUTHOR ?$BOOK_NAME
ORDER BY DESC(?countX)
LIMIT $LIMIT