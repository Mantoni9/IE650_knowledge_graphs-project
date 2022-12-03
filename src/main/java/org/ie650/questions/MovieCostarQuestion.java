package org.ie650.questions;

import org.ie650.queryresults.Costar;
import org.ie650.queryresults.Movie;

import java.util.List;
import java.util.Random;

public class MovieCostarQuestion extends Question {

    String actorOneName;
    String actorTwoName;
    private Movie movie;
    public MovieCostarQuestion(List<Movie> candidates, List<Costar> costars) {
        Costar candidate = costars.get(new Random().nextInt(costars.size()));
        this.actorOneName = candidate.getName(Costar.ACTOR_ONE_NAME);
        this.actorTwoName = candidate.getName(Costar.ACTOR_TWO_NAME);
        this.correctAnswer = candidate.getName(Costar.MOVIE_NAME);
        for(int i = 0; i<3; i++) {
            this.falseAnswers.add(candidates.get(new Random().nextInt(candidates.size())).getName());
        }
    }

    @Override
    public String getPrompt() {
        return String.format("In which movie did %s and %s costar?", this.actorOneName, this.actorTwoName);
    }
}
