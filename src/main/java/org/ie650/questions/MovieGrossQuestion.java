package org.ie650.questions;

import org.ie650.queryresults.Movie;

import java.util.Random;

public class MovieGrossQuestion extends Question{

    public MovieGrossQuestion(Movie one, Movie two) {
        this.correctAnswerIndex = new Random().nextInt(2);
        Movie higherGross = one.getGross() > two.getGross() ? one : two;
        this.correctAnswer = higherGross.getName();
        this.falseAnswers.add(one == higherGross ? two.getName() : one.getName());
    }

    @Override
    public String getPrompt() {
        return "Which movie had the higher gross revenue?";
    }
}
