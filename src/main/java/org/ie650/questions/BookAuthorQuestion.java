package org.ie650.questions;

import org.ie650.queries.BookAuthorQuery;
import org.ie650.queryresults.Author;
import org.ie650.queryresults.Book;

import java.util.List;
import java.util.Random;

public class BookAuthorQuestion extends Question{

    private Book book;

    public BookAuthorQuestion(Book book) {
        this.book = book;
        this.correctAnswerIndex = new Random().nextInt(4);
        this.correctAnswer = book.getAuthorName();
        List<Author> similarAuthors = new BookAuthorQuery(book.getAuthor()).execute();
        if(similarAuthors.size() == 0) {
            throw new RuntimeException();
        }
        for(int i = 0; i<3; i++) {
            this.falseAnswers.add(similarAuthors.get(new Random().nextInt(similarAuthors.size())).getName());
        }
    }
    @Override
    public String getPrompt() {
        return String.format("Who wrote '%s'?", book.getName());
    }
}
