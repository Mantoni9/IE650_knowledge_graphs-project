package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.Book;
import org.ie650.queryresults.QueryResult;

public class BookWikidataQuery extends AppQuery{

    public BookWikidataQuery(Book book) {
        addParameter("\\$URI", book.getUri());
        this.constructQuery("book_wikidata_query");
    }
    @Override
    protected QueryResult create(QuerySolution qs) {
        return new QueryResult(qs);
    }
}
