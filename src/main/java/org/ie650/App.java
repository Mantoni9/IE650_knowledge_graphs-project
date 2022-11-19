package org.ie650;

import org.ie650.queries.BookQuery;
import org.ie650.queryresults.Book;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        List<Book> result = new BookQuery(100).execute();
        for(Book x : result) {
            System.out.println(x.getName().getString() + " " + x.getPages() + " " + x.getAuthor().toString());
        }
    }
}
