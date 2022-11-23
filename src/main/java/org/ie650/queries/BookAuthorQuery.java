package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.Author;

public class BookAuthorQuery extends AppQuery<Author>{

    public BookAuthorQuery(String uri) {
        super();
        addParameter("\\$TARGET", uri);
        addParameter("\\$NAME", Author.NAME);
        this.constructQuery("book_author_query");
    }
    @Override
    protected Author create(QuerySolution qs) {
        return new Author(qs);
    }
}
