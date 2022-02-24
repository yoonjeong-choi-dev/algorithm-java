package book.ch08Indexer;

import book.websearchengine.WikiFetcher;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TermCounterExample {
    public static void main(String[] args) throws IOException {
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements paragraphs = WikiFetcher.getInstance().fetchWikipedia(url);

        TermCounter counter = new TermCounter(url);
        counter.process(paragraphs);
        System.out.println(counter);
    }
}
