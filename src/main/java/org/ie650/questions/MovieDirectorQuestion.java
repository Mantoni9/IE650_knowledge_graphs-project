package org.ie650.questions;

import org.ie650.queryresults.Movie;

import java.util.List;
import java.util.Random;

public class MovieDirectorQuestion extends Question {

    private Movie movie;
    public MovieDirectorQuestion(Movie movie, List<Movie> candidates) {
        this.movie = movie;
        this.correctAnswerIndex = new Random().nextInt(4);
        this.correctAnswer = getDirectorString(movie.getDirectors());
        for (int i = 0; i < 3; i++) {
            this.falseAnswers.add(getDirectorString(candidates.get(new Random().nextInt(candidates.size())).getDirectors()));
        }
    }

    @Override
    public String getPrompt() {
        return String.format("Who directed '%s'?", this.movie.getName());
    }

    public String getDirectorString(List<String> directors) {
        if (directors.size() == 1) {
            return directors.get(0);
        } else {
            String s = directors.get(0);
            for (int i = 1; i < directors.size() - 1; i++) {
                s += ", " + directors.get(i);
            }
            s += "and " + directors.get(directors.size() - 1);
            return s;
        }
    }
}
