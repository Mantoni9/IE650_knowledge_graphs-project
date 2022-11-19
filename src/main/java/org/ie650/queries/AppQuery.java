package org.ie650.queries;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTPBuilder;
import org.ie650.queryresults.QueryResult;

import java.util.ArrayList;
import java.util.List;

public abstract class AppQuery<T extends QueryResult> {

    public static String endpoint = "http://dbpedia.org/sparql";
    protected Query query;

    public List<T> execute() {
        List<T> result = new ArrayList<>();
        try (QueryExecution qe = QueryExecutionHTTPBuilder.service(endpoint).query(query).build()) {
            ResultSet rs = qe.execSelect();
            while(rs.hasNext()) {
                QuerySolution sol = rs.nextSolution() ;
                result.add(create(sol));
            }
        }
        return result;
    }

    protected abstract T create(QuerySolution qs);
}
