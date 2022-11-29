package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.Costar;
import org.ie650.queryresults.QueryResult;

public class MovieCostarQuery extends AppQuery{

    public MovieCostarQuery() {
        addParameter("\\$ACTOR_ONE_NAME", Costar.ACTOR_ONE_NAME);
        addParameter("\\$ACTOR_TWO_NAME", Costar.ACTOR_TWO_NAME);
        addParameter("\\$MOVIE_NAME", Costar.MOVIE_NAME);
        addParameter("\\$ACTOR_ONE_URI", Costar.ACTOR_ONE);
        addParameter("\\$ACTOR_TWO_URI", Costar.ACTOR_TWO);
        this.constructQuery("movie_costar_query");
    }
    @Override
    protected QueryResult create(QuerySolution qs) {
        return new Costar(qs);
    }
}
