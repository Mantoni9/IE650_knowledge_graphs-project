package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

import java.util.Arrays;
import java.util.List;

public class Movie extends QueryResult{

    public static String URI = "uri";
    public static String NAME = "name";
    public static String GROSS = "gross";
    public static String BUDGET = "budget";
    public static String DIRECTORS = "directors";
    public static String ACTORS = "actors";

    public Movie(QuerySolution sol) {
        super(sol);
    }

    public long getGross() {
        return Double.valueOf(this.sol.getLiteral(GROSS).getString()).longValue();
    }

    public String getName() {
        return this.sol.getLiteral(NAME).getLexicalForm();
    }

    public List<String> getDirectors() {
        return Arrays.asList(this.sol.getLiteral(DIRECTORS).getLexicalForm().split(","));
    }

}
