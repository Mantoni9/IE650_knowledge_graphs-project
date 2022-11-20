package org.ie650;

import org.ie650.queries.BookQuery;
import org.ie650.queryresults.Book;

import java.io.IOException;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        //TextIO textIO = TextIoFactory.getTextIO();
        try {
            List<Book> results = new BookQuery(1000).execute();
            for(Book b : results) {
                System.out.println(b.getName() + " " + b.getAuthor() + " " + b.getDate());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
