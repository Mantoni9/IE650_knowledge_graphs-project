package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public class Artist extends QueryResult {
    public static String NAME = "name";

    public Artist(QuerySolution sol) {
        super(sol);
    }

    public String getName() {
        return this.sol.getLiteral(NAME).getLexicalForm();
    }
}
