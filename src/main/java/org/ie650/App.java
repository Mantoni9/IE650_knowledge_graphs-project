package org.ie650;

public class App
{
    public static void main( String[] args )
    {
        Quiz quiz = new Quiz();
        quiz.start();
        /*
        try {
            List<Book> results = new BookQuery(1000).execute();
            for(Book b : results) {
                System.out.println(b.getName() + " " + b.getAuthor() + " " + b.getDate());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
         */
    }
}
