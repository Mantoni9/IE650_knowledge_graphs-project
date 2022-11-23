package org.ie650.queries;

import org.apache.jena.query.*;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTPBuilder;
import org.apache.jena.sparql.exec.http.QuerySendMode;
import org.ie650.queryresults.QueryResult;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AppQuery<T extends QueryResult> {

    public static String DBPEDIA_ENDPOINT = "http://dbpedia.org/sparql";
    public static String WIKIDATA_ENDPOINT = "https://query.wikidata.org/sparql";
    protected Query query;
    private Map<String, String> parameters;

    public AppQuery() {
        this.parameters = new HashMap<String, String>();
    }

    public void addParameter(String key, String value) {
        this.parameters.put(key, value);
    }

    public void constructQuery(String name) {
        String query = "";
        try {
            URI uri = URI.create(getClass().getResource("/queries/" + name + ".rs").toString());
            query = String.join(" ", Files.readAllLines(Paths.get(uri)));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        for(Map.Entry<String, String> parameter : parameters.entrySet()) {
            query = query.replaceAll(parameter.getKey(), parameter.getValue());
        }
        System.out.println(query);
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setCommandText(query);
        pss.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
        pss.setNsPrefix("dbo", "http://dbpedia.org/ontology/");
        pss.setNsPrefix("dbp", "http://dbpedia.org/property/");
        pss.setNsPrefix("owl", "http://www.w3.org/2002/07/owl#");
        pss.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        this.query = pss.asQuery();
    }

    public List<T> execute(String endpoint) {
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

    public List<T> execute() {
        return this.execute(DBPEDIA_ENDPOINT);
    }

    protected abstract T create(QuerySolution qs);
}
