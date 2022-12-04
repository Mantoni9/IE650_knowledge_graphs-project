package org.ie650.questions;

import org.ie650.queryresults.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CountryHDIQuestion extends Question {

    private Country Country;

    public CountryHDIQuestion(List<Country> candidates) {
        List<Country> options = new ArrayList<Country>();
        Country max = null;
        this.correctAnswerIndex = new Random().nextInt(2);
        while (options.size() < 2) {
            Country c = candidates.get(new Random().nextInt(candidates.size()));
            if (!options.contains(c)) {
                if(max == null || max.getHDI() < c.getHDI()) {
                    this.correctAnswer = c.getName(Country.NAME);
                    max = c;
                }
                options.add(c);
            }
        }
        for(Country c : options) {
            if(c != max) {
                this.falseAnswers.add(c.getName(Country.NAME));
            }
        }
    }

    @Override
    public String getPrompt() {
        return "Which country has the higher Human Development Index (HDI)?";
    }
}
