package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.Movie;

public class MovieQuery extends AppQuery<Movie> {

    public MovieQuery(int limit) {
        super();
        addParameter("\\$GROSS", Movie.GROSS);
        addParameter("\\$BUDGET", Movie.BUDGET);
        addParameter("\\$URI", Movie.URI);
        addParameter("\\$NAME", Movie.NAME);
        addParameter("\\$ACTORS", Movie.ACTORS);
        addParameter("\\$DIRECTORS", Movie.DIRECTORS);
        this.constructQuery("movie_query");
    }

    @Override
    protected Movie create(QuerySolution qs) {
        return new Movie(qs);
    }
}
