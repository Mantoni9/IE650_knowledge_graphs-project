package org.ie650.questions;

import org.ie650.queryresults.Country;

import java.util.List;
import java.util.Random;

public class CountryHeadOfStateQuestion extends Question {

    private Country Country;

    public CountryHeadOfStateQuestion(Country country, List<Country> candidates) {
        this.Country = country;
        this.correctAnswerIndex = new Random().nextInt(4);
        this.correctAnswer = country.getName(Country.HEAD);
        while (this.falseAnswers.size() < 3) {
            this.falseAnswers.add(candidates.get(new Random().nextInt(candidates.size())).getName(Country.HEAD));
        }
    }

    @Override
    public String getPrompt() {
        return String.format("Who is the head of state of '%s'?", Country.getName(Country.NAME));
    }
}
