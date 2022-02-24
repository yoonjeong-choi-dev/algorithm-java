package book.ch08Indexer;

import book.websearchengine.WikiFetcher;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

public class IndexExample {
    public static void main(String[] args)  throws IOException {
        Index index = new Index();

        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements paragraphs = WikiFetcher.getInstance().fetchWikipedia(url);
        index.indexPage(url, paragraphs);

        url = "https://en.wikipedia.org/wiki/Programming_language";
        paragraphs = WikiFetcher.getInstance().fetchWikipedia(url);
        index.indexPage(url, paragraphs);

        System.out.println(index);

        Set<TermCounter> set = index.get("occur");
        System.out.printf("'occur' page : %d\n", set.size());
    }
}
