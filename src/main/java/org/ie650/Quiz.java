package org.ie650;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.ie650.questions.Question;

public class Quiz {

    public enum Topic {
        BOOKS,
        MOVIES,
        SONGS,
        GEOGRAPHY,
    }

    private String ascii = " ____  __.________  ________        .__        \n" +
            "|    |/ _/  _____/  \\_____  \\  __ __|__|_______\n" +
            "|      </   \\  ___   /  / \\  \\|  |  \\  \\___   /\n" +
            "|    |  \\    \\_\\  \\ /   \\_/.  \\  |  /  |/    / \n" +
            "|____|__ \\______  / \\_____\\ \\_/____/|__/_____ \\\n" +
            "        \\/      \\/         \\__>              \\/";
    private TextIO textIO;
    private QuestionFactory factory;
    private Topic selectedTopic;

    public Quiz() {
        this.textIO = TextIoFactory.getTextIO();
        this.factory = new QuestionFactory();
    }

    public void start() {
        textIO.getTextTerminal().getProperties().put("pane.title", "KG Quiz");
        textIO.getTextTerminal().setBookmark("empty");
        textIO.getTextTerminal().print(ascii);
        textIO.getTextTerminal().print("\n");
        this.selectedTopic = textIO.newEnumInputReader(Quiz.Topic.class)
                .withAllValuesNumbered()
                .read("Select Topic: ");
        textIO.getTextTerminal().resetToBookmark("empty");
        textIO.getTextTerminal().print("Collecting candidates ...");
        this.factory.setTopic(this.selectedTopic);
        loop();
    }

    public void loop() {
        while (true) {
            textIO.getTextTerminal().resetToBookmark("empty");
            textIO.getTextTerminal().print("Generating question. This might take a while ...");
            Question q = this.factory.createQuestion();
            textIO.getTextTerminal().resetToBookmark("empty");
            String answer = textIO.newStringInputReader().withNumberedPossibleValues(q.getPossibleAnswers())
                    .read(q.getPrompt());
            if (answer.equals(q.getPossibleAnswers().get(q.getCorrectAnswerIndex()))) {
                textIO.getTextTerminal().println("Correct");
            } else {
                textIO.getTextTerminal().println("False\n");
                textIO.getTextTerminal().print(String.format("The correct answer is:\n%s) %s\n", q.getCorrectAnswerIndex() + 1, q.getPossibleAnswers().get(q.getCorrectAnswerIndex())));
            }
            textIO.getTextTerminal().print("\nPress Enter key to continue ...");
            textIO.getTextTerminal().read(true);
        }
    }
}
