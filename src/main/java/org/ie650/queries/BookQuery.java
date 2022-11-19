package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.QueryBuilder;
import org.ie650.queryresults.Book;

public class BookQuery extends AppQuery<Book> {

    public BookQuery(int limit) {
        String book = "?book";
        QueryBuilder builder = new QueryBuilder();
        builder
                .select(Book.NAME)
                .select(Book.PAGES)
                .select(Book.AUTHOR)
                .where(book, "a", "dbo:Book")
                .where(book, "dbp:name", Book.NAME)
                .where(book, "dbp:pages", Book.PAGES)
                .where(book, "dbp:author", Book.AUTHOR)
                .limit(limit);
        this.query = builder.build();
    }

    @Override
    protected Book create(QuerySolution qs) {
        return new Book(qs);
    }
}
