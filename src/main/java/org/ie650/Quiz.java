package org.ie650;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.ie650.questions.Question;

public class Quiz {

    public enum Topic {
        BOOKS,
        MOVIES,
    }

    private TextIO textIO;
    private QuestionFactory factory;
    private Topic selectedTopic;

    public Quiz() {
        this.textIO = TextIoFactory.getTextIO();
        this.factory = new QuestionFactory();
    }

    public void start() {
        this.selectedTopic = textIO.newEnumInputReader(Quiz.Topic.class)
                .withAllValuesNumbered()
                .read("Select Topic: ");
        this.factory.setTopic(this.selectedTopic);
        loop();
    }

    public void loop() {
        while(true) {
            Question q = this.factory.createQuestion();
            textIO.getTextTerminal().println();
            String answer = textIO.newStringInputReader().withNumberedPossibleValues(q.getPossibleAnswers())
                    .read(q.getPrompt());
            if(answer.equals(q.getPossibleAnswers().get(q.getCorrectAnswerIndex()))) {
                textIO.getTextTerminal().println("Correct");
            } else {
                textIO.getTextTerminal().println("False");
            }
        }
    }
}
