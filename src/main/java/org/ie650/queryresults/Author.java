package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public class Author extends QueryResult{
    public static String NAME = "name";

    public Author(QuerySolution sol) {
        super(sol);
    }

    public String getName() {
        return this.sol.getLiteral(NAME).getLexicalForm();
    }

}
