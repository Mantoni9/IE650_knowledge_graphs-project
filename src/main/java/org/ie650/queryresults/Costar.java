package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public class Costar extends QueryResult{
    public static String ACTOR_ONE = "actor_one_uri";
    public static String ACTOR_TWO = "actor_two_uri";
    public static String ACTOR_ONE_NAME = "actor_one_name";
    public static String ACTOR_TWO_NAME = "actor_two_name";
    public static String MOVIE_NAME = "movie_name";

    public Costar(QuerySolution sol) {
        super(sol);
    }
}
