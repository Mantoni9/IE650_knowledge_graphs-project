package org.ie650.queryresults;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.QuerySolution;

public class Song extends QueryResult{

    public static String URI = "uri";

    public static String NAME = "name";
    public static String ARTIST_NAME = "artistName";
    public static String RELEASE_DATE = "releaseDate";




    public Song(QuerySolution sol) {super(sol);}

    public String getUri() {
        return this.sol.getResource(URI).getURI();
    }

    public String getName() {
        return this.sol.getLiteral(NAME).getLexicalForm();
    }

    public String getArtistName() {
        return this.sol.getLiteral(ARTIST_NAME).getLexicalForm();
    }

    public String getReleaseYear() {
        if(this.sol.getLiteral(RELEASE_DATE).getDatatype() == XSDDatatype.XSDdate) {
            return this.sol.getLiteral(RELEASE_DATE).getString().substring(0,4);
        } else {
            return this.sol.getLiteral(RELEASE_DATE).getString();
        }
    }

}
