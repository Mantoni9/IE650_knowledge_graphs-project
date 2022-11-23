package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.QueryResult;

public class BookWikidataCharacterQuery extends AppQuery{

    public BookWikidataCharacterQuery(String wikidataUri) {
        addParameter("\\$URI", wikidataUri);
        this.constructQuery("book_wikidata_character_query");
    }
    @Override
    protected QueryResult create(QuerySolution qs) {
        return new QueryResult(qs);
    }
}
