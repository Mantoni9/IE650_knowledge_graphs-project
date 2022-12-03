package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.Artist;

public class SongArtistQuery extends AppQuery<Artist>{

    public SongArtistQuery(String uri) {
        super();
        addParameter("\\$TARGET", uri);
        addParameter("\\$NAME", Artist.NAME);
        this.constructQuery("song_artist_query");
    }
    @Override
    protected Artist create(QuerySolution qs) {
        return new Artist(qs);
    }
}
