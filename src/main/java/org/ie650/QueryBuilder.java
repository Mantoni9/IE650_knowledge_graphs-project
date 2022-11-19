package org.ie650;

import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTPBuilder;

import java.util.HashSet;
import java.util.Set;

public class QueryBuilder {

    public static String endpoint = "http://dbpedia.org/sparql";
    private Set<String> vars;
    private Set<String> restrictions;
    private String orderBy;
    private int limit;


    public QueryBuilder() {
        this.vars = new HashSet<>();
        this.restrictions = new HashSet<>();
        this.orderBy = "";
        this.limit = 100;
    }

    public QueryBuilder select(String var) {
        this.vars.add(var);
        return this;
    }

    public QueryBuilder where(String subject, String predicate, String object) {
        String res = (vars.contains(subject) ? "?" : "") + subject;
        res += " " + predicate + " ";
        res += (vars.contains(object) ? "?" : "") + object;
        this.restrictions.add(res);
        return this;
    }

    public QueryBuilder orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public QueryBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    public Query build() {
        StringBuilder query = new StringBuilder();
        query.append("PREFIX dbo: <http://dbpedia.org/ontology/> ");
        query.append("SELECT ");
        for(var var : vars) {
            query.append(String.format("?%s ", var));
        }
        query.append("WHERE { ");
        for(var res: restrictions) {
            query.append(String.format("%s .", res));
        }
        query.append(" }");
        if(!this.orderBy.equals("")) {
            query.append(" ORDER BY ");
            query.append(this.orderBy);
        }
        query.append(" LIMIT ");
        query.append(this.limit);
        return QueryFactory.create(query.toString());
    }

    public static QueryExecution executeQuery(Query query) {
        return QueryExecutionHTTPBuilder.service(endpoint).query(query).build();
    }
}
