package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public class Book extends QueryResult {
    public static String NAME = "name";
    public static String DATE = "date";
    public static String AUTHOR = "author";

    public Book(QuerySolution sol) {
        super(sol);
    }

    public String getName() {
        return this.sol.getLiteral(NAME).getLexicalForm();
    }

    public String getAuthor() {
        return this.sol.getLiteral(AUTHOR).getLexicalForm();
    }

    public String getDate() {
        return this.sol.getLiteral(DATE).getLexicalForm();
    }
}
