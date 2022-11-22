package org.ie650.questions;

import java.util.LinkedList;
import java.util.List;

public abstract class Question {
    protected List<String> falseAnswers;
    protected String correctAnswer;
    protected int correctAnswerIndex;

    public Question() {
        this.falseAnswers = new LinkedList<>();
    }

    public List<String> getPossibleAnswers() {
        List<String> answers = new LinkedList<String>(this.falseAnswers);
        answers.add(this.correctAnswerIndex, correctAnswer);
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return this.correctAnswerIndex;
    }

    public abstract String getPrompt();

}
