package org.ie650;

import org.ie650.queries.BookQuery;
import org.ie650.queryresults.Book;
import org.ie650.questions.BookYearQuestion;
import org.ie650.questions.Question;

import java.util.List;
import java.util.Random;

public class QuestionFactory {

    private Quiz.Topic topic;
    private List<Book> bookCandidates;

    public void setTopic(Quiz.Topic topic) {
        this.topic = topic;
        switch(topic) {
            case BOOKS:
                this.bookCandidates = new BookQuery(500).execute();
        }
    }
    public Question createQuestion() {
        switch(topic) {
            case BOOKS:
                Book candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
                return createRandomBookAction(candidate);
            default:
                return null;
        }
    }
    public Question createRandomBookAction(Book book) {
        int r = new Random().nextInt(1);
        switch (r) {
            case 0:
                return new BookYearQuestion(book);
            default:
                return null;
        }
    }

}
