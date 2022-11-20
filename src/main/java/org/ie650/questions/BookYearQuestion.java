package org.ie650.questions;

import org.ie650.queryresults.Book;

/**
 * What year was a given book written in?
 */
public class BookYearQuestion extends Question{
    private Book book;

    public BookYearQuestion(Book book) {
        this.book = book;
    }
}
