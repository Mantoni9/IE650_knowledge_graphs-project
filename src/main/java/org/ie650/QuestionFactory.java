package org.ie650;

import org.ie650.queries.BookQuery;
import org.ie650.queryresults.Book;
import org.ie650.questions.*;

import java.util.List;
import java.util.Random;

public class QuestionFactory {

    private Quiz.Topic topic;
    private List<Book> bookCandidates;

    public void setTopic(Quiz.Topic topic) {
        this.topic = topic;
        switch (topic) {
            case BOOKS:
                this.bookCandidates = new BookQuery(200).execute();
        }
    }

    public Question createQuestion() {
        switch (topic) {
            case BOOKS:
                return createRandomBookAction();
            default:
                return null;
        }
    }

    public Question createRandomBookAction() {
        int r = new Random().nextInt(2);
        Book candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
        while (true) {
            try {
                return new BookCharacterQuestion(candidate, bookCandidates);

            } catch (QuestionException e) {
                candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
            }
        }
/*        switch (r) {
            case 0:
                while (true) {
                    try {
                        return new BookAuthorQuestion(candidate);
                    } catch (QuestionException e) {
                        candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
                    }
                }
            case 1:
                return new BookYearQuestion(candidate);
            default:
                return null;
        }*/
    }

}
