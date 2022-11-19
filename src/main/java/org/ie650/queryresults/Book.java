package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;

public class Book extends QueryResult {
    public static String NAME = "name";
    public static String PAGES = "pages";
    public static String AUTHOR = "author";

    public Book(QuerySolution sol) {
        super(sol);
    }

    public Literal getName() {
        return this.sol.getLiteral(NAME);
    }

    public RDFNode getAuthor() {
        return this.sol.get(AUTHOR);
    }

    public Literal getPages() {
        return this.sol.getLiteral(PAGES);
    }
}
