package org.ie650.questions;

import org.ie650.queries.SongArtistQuery;
import org.ie650.queryresults.Artist;
import org.ie650.queryresults.Song;

import java.util.List;
import java.util.Random;

public class SongArtistQuestion extends Question {

    private Song song;

    public SongArtistQuestion(Song song, List<Song> candidates) {
        this.song = song;
        this.correctAnswerIndex = new Random().nextInt(4);
        this.correctAnswer = song.getArtistName();
        List<Artist> similarArtists = new SongArtistQuery(song.getUri()).execute();
        for (int i = 0; i < 3; i++) {
            if (i < similarArtists.size()) {
                this.falseAnswers.add(similarArtists.get(i).getName());
            } else {
                this.falseAnswers.add(candidates.get(new Random().nextInt(candidates.size())).getName());
            }
        }
    }

    @Override
    public String getPrompt() {
        return String.format("Who's the Artist of '%S'?", song.getName());
    }
}
