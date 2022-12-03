package org.ie650.questions;

import org.ie650.queryresults.Song;

import java.util.Random;

/**
 * In which year was the song released?
 */
public class SongYearQuestion extends Question {
    private Song song;

    public SongYearQuestion(Song song) {
        this.song = song;
        this.correctAnswerIndex = new Random().nextInt(4);
        this.correctAnswer = song.getReleaseYear();
        int diff = 0;
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    diff = new Random().nextInt(75, 100);
                    break;
                case 1:
                    diff = new Random().nextInt(25, 50);
                    break;
                case 2:
                    diff = new Random().nextInt(10, 15);
                    break;
            }
            if (Integer.parseInt(this.correctAnswer + diff) > 2022) {
                falseAnswers.add("" + (Integer.parseInt(this.correctAnswer) - diff));
            } else {
                falseAnswers.add("" + (Integer.parseInt(this.correctAnswer) + (diff % 2 == 0 ? diff : -diff)));
            }
        }
    }

    @Override
    public String getPrompt() {
        return String.format("In which year was '%s' by '%s' released?", song.getName(), song.getArtistName());
    }
}
