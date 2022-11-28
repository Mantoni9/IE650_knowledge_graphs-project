package org.ie650;

import org.ie650.queries.BookQuery;
import org.ie650.queries.MovieQuery;
import org.ie650.queryresults.Book;
import org.ie650.queryresults.Movie;
import org.ie650.questions.*;

import java.util.List;
import java.util.Random;

public class QuestionFactory {

    private Quiz.Topic topic;
    private List<Book> bookCandidates;
    private List<Movie> movieCandidates;

    public void setTopic(Quiz.Topic topic) {
        this.topic = topic;
        switch (topic) {
            case BOOKS:
                this.bookCandidates = new BookQuery(1000).execute();
            case MOVIES:
                this.movieCandidates = new MovieQuery(200).execute();
        }
    }

    public Question createQuestion() {
        switch (topic) {
            case BOOKS:
                return createRandomBookAction();
            case MOVIES:
                return createRandomMovieQuestion();
            default:
                return null;
        }
    }

    public Question createRandomBookAction() {
        int r = new Random().nextInt(3);
        Book candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
/*        while (true) {
            try {
                return new BookAuthorQuestion(candidate);

            } catch (QuestionException e) {
                candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
            }
        }*/
        switch (r) {
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
            case 2:
                while (true) {
                    try {
                        return new BookCharacterQuestion(candidate, bookCandidates);
                    } catch (QuestionException e) {
                        candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
                    }
                }
            default:
                return null;
        }
    }

    public Question createRandomMovieQuestion() {
        int r = new Random().nextInt(2);
        Movie candidate = movieCandidates.get(new Random().nextInt(movieCandidates.size()));
        switch (r) {
            case 0:
                Movie candidateTwo = null;
                while(candidateTwo == null || candidateTwo == candidate) {
                    candidateTwo = movieCandidates.get(new Random().nextInt(movieCandidates.size()));
                }
                return new MovieGrossQuestion(candidate, candidateTwo);
            case 1:
                return new MovieDirectorQuestion(candidate, movieCandidates);
            default:
                return null;
        }
    }

}
