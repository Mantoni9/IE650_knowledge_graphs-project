package org.ie650.queries;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.Book;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BookQuery extends AppQuery<Book> {

    public BookQuery(int limit) {
        String query = "";
        try {
            URI uri = URI.create(getClass().getResource("/queries/book_query.rs").toString());
            query = String.join(" ", Files.readAllLines(Paths.get(uri)));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        query = query
                .replaceAll("\\$BOOK_NAME", Book.NAME)
                .replaceAll("\\$BOOK_AUTHOR", Book.AUTHOR)
                .replaceAll("\\$BOOK_DATE", Book.DATE)
                .replaceAll("\\$LIMIT", "" + limit);
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setCommandText(query);
        pss.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
        pss.setNsPrefix("dbo", "http://dbpedia.org/ontology/");
        pss.setNsPrefix("dbp", "http://dbpedia.org/property/");
        this.query = pss.asQuery();
    }

    @Override
    protected Book create(QuerySolution qs) {
        return new Book(qs);
    }
}
