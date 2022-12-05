package org.ie650;

import org.ie650.queries.*;
import org.ie650.queryresults.*;
import org.ie650.questions.*;

import java.util.List;
import java.util.Random;

public class QuestionFactory {

    private Quiz.Topic topic;
    private List<Book> bookCandidates;
    private List<Movie> movieCandidates;
    private List<Costar> costarCandidates;
    private List<Song> songCandidates;
    private List<Country> countryCandidates;

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
            case GEOGRAPHY:
                this.countryCandidates = new CountryQuery().execute(AppQuery.WIKIDATA_ENDPOINT);
        }
    }

    public Question createQuestion() {
        switch (topic) {
            case BOOKS:
                return createRandomBookAction();
            case MOVIES:
                return createRandomMovieQuestion();
            case SONGS:
                return createRandomSongQuestion();
            case GEOGRAPHY:
                return createRandomGeographyQuestion();
            default:
                return null;
        }
    }

    public Question createRandomBookAction() {
        int r = new Random().nextInt(4);
        Book candidate = bookCandidates.get(new Random().nextInt(bookCandidates.size()));
        r=1;
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
        int r = new Random().nextInt(2);
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

    public Question createRandomGeographyQuestion() {
        int r = new Random().nextInt(5);
        Country country = countryCandidates.get(new Random().nextInt(countryCandidates.size()));
        while (true) {
            try {
                switch (r) {
                    case 0:
                        return new CountryPopulationQuestion(countryCandidates);
                    case 1:
                        return new CountryHeadOfStateQuestion(country, countryCandidates);
                    case 2:
                        return new CountryCapitalQuestion(country, countryCandidates);
                    case 3:
                        return new CountryAreaQuestion(countryCandidates);
                    case 4:
                        return new CountryHDIQuestion(countryCandidates);
                    default:
                        return null;
                }
            } catch (QuestionException e) {
                country = countryCandidates.get(new Random().nextInt(countryCandidates.size()));
            }
        }
    }

}
