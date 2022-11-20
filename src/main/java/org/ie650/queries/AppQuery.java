package org.ie650.queries;

import org.apache.jena.query.*;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTPBuilder;
import org.apache.jena.sparql.exec.http.QuerySendMode;
import org.ie650.queryresults.QueryResult;

import java.util.ArrayList;
import java.util.List;

public abstract class AppQuery<T extends QueryResult> {

    public static String endpoint = "http://dbpedia.org/sparql";
    protected Query query;

    public List<T> execute() {
        List<T> result = new ArrayList<>();
        try (QueryExecution qe = QueryExecutionHTTPBuilder.service(endpoint).sendMode(QuerySendMode.asGetAlways).query(query).build()) {
            ResultSet rs = qe.execSelect();
            //ResultSetFormatter.out(System.out, rs, query) ;
            while(rs.hasNext()) {
                QuerySolution sol = rs.nextSolution() ;
                result.add(create(sol));
            }
        }
        return result;
    }

    protected abstract T create(QuerySolution qs);
}
