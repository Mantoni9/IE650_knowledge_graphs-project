package org.ie650.questions;

import org.ie650.queries.AppQuery;
import org.ie650.queries.BookWikidataCharacterQuery;
import org.ie650.queries.BookWikidataQuery;
import org.ie650.queryresults.Book;
import org.ie650.queryresults.QueryResult;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BookCharacterQuestion extends Question{

    private Book book;
    private List<String> characters;

    public BookCharacterQuestion(Book book, List<Book> candidates) {
        this.book = book;
        this.correctAnswerIndex = new Random().nextInt(4);
        List<QueryResult> results = new BookWikidataQuery(book).execute();
        String wikidataUri = null;
        if(results.size() != 0) {
            wikidataUri = results.get(0).getQuerySolution().getResource("wikidataUri").getURI();
        } else {
            throw new QuestionException();
        }
        results = new BookWikidataCharacterQuery(wikidataUri).execute(AppQuery.WIKIDATA_ENDPOINT);
        if(results.size() == 0) {
            throw new QuestionException();
        }
        this.characters = new LinkedList<>();
        for(QueryResult qr : results) {
            characters.add(qr.getQuerySolution().getLiteral("character").getString());
        }
        this.correctAnswer = book.getName();
        int i = 0;
        while(i < 3) {
            Book candidate = candidates.get(new Random().nextInt(candidates.size()));
            if(candidate == book) {
                continue;
            } else {
                falseAnswers.add(candidate.getName());
                i++;
            }
        }
    }
    @Override
    public String getPrompt() {
        String prompt = "";
        for(String c : characters.subList(0, characters.size() - 1)) {
            prompt += c + ", ";
        }
        prompt += "and " + characters.get(characters.size()-1);
        prompt += " are characters in which book?";
        return prompt;
    }
}
