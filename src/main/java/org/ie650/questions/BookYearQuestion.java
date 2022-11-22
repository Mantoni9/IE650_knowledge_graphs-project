package org.ie650.questions;

import org.ie650.queryresults.Book;

import java.util.Random;

/**
 * What year was a given book written in?
 */
public class BookYearQuestion extends Question {
    private Book book;

    public BookYearQuestion(Book book) {
        this.book = book;
        this.correctAnswerIndex = new Random().nextInt(4);
        this.correctAnswer = book.getDate().substring(0, 4);
        int diff = 0;
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    diff = new Random().nextInt(75, 100);
                    break;
                case 1:
                    diff = new Random().nextInt(25, 50);
                    break;
                case 2:
                    diff = new Random().nextInt(10, 15);
                    break;
            }
            if (Integer.parseInt(this.correctAnswer + diff) > 2022) {
                falseAnswers.add("" + (Integer.parseInt(this.correctAnswer) - diff));
            } else {
                falseAnswers.add("" + (Integer.parseInt(this.correctAnswer) + (diff % 2 == 0 ? diff : -diff)));
            }
        }
    }

    @Override
    public String getPrompt() {
        return String.format("What year was '%s' by '%s' released in?", book.getName(), book.getAuthorName());
    }
}
