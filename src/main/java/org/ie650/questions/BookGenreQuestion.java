package org.ie650.questions;

import org.ie650.queryresults.Book;

import java.util.List;
import java.util.Random;

public class BookGenreQuestion extends Question {

    private Book book;
    public BookGenreQuestion(Book book, List<Book> candidates) {
        if(book.getGenres() == null) {
            throw new QuestionException();
        }
        this.book = book;
        this.correctAnswerIndex = new Random().nextInt(4);
        this.correctAnswer = this.book.getName() + " by " + this.book.getAuthorName();
        for(int i = 0; i<3; i++) {
            Book c = candidates.get(new Random().nextInt(candidates.size()));
            this.falseAnswers.add(c.getName() + " by " + c.getAuthorName());
        }
    }

    @Override
    public String getPrompt() {
        List<String> genres = this.book.getGenres();
        String s = "Which book is best described by the genre" + (genres.size() <= 1 ?  " " : "s ");
        s += "'" + genres.get(0) + "'";
        for(int i = 1; i<genres.size(); i++) {
            s += ", '" + genres.get(i) + "'";
        }
        s += "?";
        return s;
    }
}
