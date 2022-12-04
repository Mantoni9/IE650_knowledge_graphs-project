package org.ie650.questions;

import org.ie650.queryresults.Country;

import java.util.List;
import java.util.Random;

public class CountryCapitalQuestion extends Question {

    private Country Country;

    public CountryCapitalQuestion(Country country, List<Country> candidates) {
        this.Country = country;
        this.correctAnswerIndex = new Random().nextInt(4);
        this.correctAnswer = country.getName(Country.CAPITAL);
        while (this.falseAnswers.size() < 3) {
            this.falseAnswers.add(candidates.get(new Random().nextInt(candidates.size())).getName(Country.CAPITAL));
        }
    }

    @Override
    public String getPrompt() {
        return String.format("What is the capital of '%s'?", Country.getName(Country.NAME));
    }
}
