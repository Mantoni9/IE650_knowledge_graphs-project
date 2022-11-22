package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.Book;

public class BookQuery extends AppQuery<Book> {

    public BookQuery(int limit) {
        super();
        addParameter("\\$BOOK_NAME", Book.NAME);
        addParameter("\\$AUTHOR_NAME", Book.AUTHOR_NAME);
        addParameter("\\$BOOK_AUTHOR", Book.AUTHOR);
        addParameter("\\$BOOK_DATE", Book.DATE);
        addParameter("\\$LIMIT", "" + limit);
        this.constructQuery("book_query");
    }

    @Override
    protected Book create(QuerySolution qs) {
        return new Book(qs);
    }
}
