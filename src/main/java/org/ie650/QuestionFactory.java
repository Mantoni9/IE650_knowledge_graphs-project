package org.ie650;

import org.ie650.queries.BookQuery;
import org.ie650.queries.MovieCostarQuery;
import org.ie650.queries.MovieQuery;
import org.ie650.queries.SongQuery;
import org.ie650.queryresults.Book;
import org.ie650.queryresults.Costar;
import org.ie650.queryresults.Movie;
import org.ie650.queryresults.Song;
import org.ie650.questions.*;

import java.util.List;
import java.util.Random;

public class QuestionFactory {

    private Quiz.Topic topic;
    private List<Book> bookCandidates;
    private List<Movie> movieCandidates;
    private List<Costar> costarCandidates;

    private List<Song> songCandidates;

    public void setTopic(Quiz.Topic topic) {
        this.topic = topic;
        switch (topic) {
            case BOOKS:
                this.bookCandidates = new BookQuery(200).execute();
                break;
            case MOVIES:
                this.movieCandidates = new MovieQuery(200).execute();
                this.costarCandidates = new MovieCostarQuery().execute();
                break;
            case SONGS:
                this.songCandidates = new SongQuery(200).execute();
                break;
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
        int r = new Random().nextInt(4);
        Book candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
        while (true) {
            try {
                switch (r) {
                    case 0:
                        return new BookGenreQuestion(candidate, bookCandidates);
                    case 1:
                        return new BookAuthorQuestion(candidate, bookCandidates);
                    case 2:
                        return new BookYearQuestion(candidate);
                    case 3:
                        return new BookCharacterQuestion(candidate, bookCandidates);
                    default:
                        return null;
                }
            } catch (QuestionException e) {
                candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
            }
        }
    }

    public Question createRandomMovieQuestion() {
        int r = new Random().nextInt(3);
        Movie candidate = movieCandidates.get(new Random().nextInt(movieCandidates.size()));
        switch (r) {
            case 0:
                return new MovieCostarQuestion(movieCandidates, costarCandidates);
            case 1:
                Movie candidateTwo = null;
                while (candidateTwo == null || candidateTwo == candidate) {
                    candidateTwo = movieCandidates.get(new Random().nextInt(movieCandidates.size()));
                }
                return new MovieGrossQuestion(candidate, candidateTwo);
            case 2:
                return new MovieDirectorQuestion(candidate, movieCandidates);
            default:
                return null;
        }
    }

    public Question createRandomSongQuestion() {
        int r = new Random().nextInt(3);
        Song candidate = songCandidates.get(new Random().nextInt(songCandidates.size()));
        switch (r) {
            case 0:
                return new SongArtistQuestion(candidate, songCandidates);
            case 1:
                return new SongYearQuestion(candidate);
            default:
                return null;
        }
    }

}
