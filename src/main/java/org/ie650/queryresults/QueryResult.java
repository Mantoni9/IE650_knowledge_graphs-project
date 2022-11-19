package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public abstract class QueryResult {
    protected QuerySolution sol;

    public QueryResult(QuerySolution sol) {
        this.sol = sol;
    }
}
