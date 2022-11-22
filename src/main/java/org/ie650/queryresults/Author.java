package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public class Author extends QueryResult{
    public static String NAME = "name";
    public static String BIRTHDATE = "birthDate";
    public static String GENRE = "genre";
    public static String NATIONALITY = "nationality";

    public Author(QuerySolution sol) {
        super(sol);
    }

    public String getName() {
        return this.sol.getLiteral(NAME).getLexicalForm();
    }

}
