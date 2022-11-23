package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public class QueryResult {
    protected QuerySolution sol;

    public QueryResult(QuerySolution sol) {
        this.sol = sol;
    }

    public QuerySolution getQuerySolution() {
        return this.sol;
    }
}
