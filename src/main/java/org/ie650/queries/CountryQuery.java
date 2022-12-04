package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.Country;
import org.ie650.queryresults.Movie;

public class CountryQuery extends AppQuery<Country> {

    public CountryQuery() {
        super();
        this.constructQuery("country_query");
    }

    @Override
    protected Country create(QuerySolution qs) {
        return new Country(qs);
    }
}
