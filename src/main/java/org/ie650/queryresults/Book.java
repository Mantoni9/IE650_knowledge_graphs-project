package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public class Book extends QueryResult {
    public static String NAME = "name";
    public static String DATE = "date";
    public static String AUTHOR_NAME = "authorName";
    public static String AUTHOR = "author";

    public Book(QuerySolution sol) {
        super(sol);
    }

    public String getName() {
        return this.sol.getLiteral(NAME).getLexicalForm();
    }

    public String getAuthorName() {
        return this.sol.getLiteral(AUTHOR_NAME).getLexicalForm();
    }

    public String getAuthor() {
        return this.sol.getResource(AUTHOR).getURI();
    }
    public String getDate() {
        return this.sol.getLiteral(DATE).getLexicalForm();
    }
}
