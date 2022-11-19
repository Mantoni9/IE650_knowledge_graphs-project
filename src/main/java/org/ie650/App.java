package org.ie650;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class App 
{
    public static void main( String[] args )
    {
        QueryBuilder builder = new QueryBuilder();
        String books = "books";
        Query q = builder.select(books).where(books, "a", "dbo:Book").build();
        try (QueryExecution qe = QueryBuilder.executeQuery(q)) {
            ResultSet results = qe.execSelect();
            ResultSetFormatter.out(System.out, results, qe.getQuery()) ;
        }
    }
}
