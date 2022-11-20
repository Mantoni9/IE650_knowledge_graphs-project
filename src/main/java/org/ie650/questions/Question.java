package org.ie650.questions;

import java.util.LinkedList;
import java.util.List;

public abstract class Question {
    protected List<String> possibleAnswers;
    protected int correctAnswer;

    public Question() {
        this.possibleAnswers = new LinkedList<>();
    }

    public List<String> getPossibleAnswers() {
        return this.possibleAnswers;
    }

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

}
