package org.ie650.queries;

import org.apache.jena.query.QuerySolution;
import org.ie650.queryresults.Song;

public class SongQuery extends AppQuery<Song> {

    public SongQuery(int limit) {
        super();
        addParameter("\\$URI", Song.URI);
        addParameter("\\$SONG_NAME", Song.NAME);
        addParameter("\\$ARTIST_NAME", Song.ARTIST_NAME);
        addParameter("\\$RELEASE_DATE", Song.RELEASE_DATE);
        addParameter("\\$LIMIT", "" + limit);
        this.constructQuery("song_query");

    }

    @Override
    protected Song create(QuerySolution qs) {
        return new Song(qs);
    }
}
