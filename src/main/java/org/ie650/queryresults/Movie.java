package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public class Movie extends QueryResult{

    public static String URI = "uri";
    public static String NAME = "name";
    public static String GROSS = "gross";
    public static String BUDGET = "budget";
    public static String DIRECTORS = "DIRECTORS";
    public static String ACTORS = "ACTORS";

    public Movie(QuerySolution sol) {
        super(sol);
    }

}
