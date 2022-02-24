package book.ch07wikiphilosophy;

import book.websearchengine.WikiFetcher;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Getting to Philosophy 테스트
public class WikiPhilosophy {
    final static List<String> visited = new ArrayList<>();

    public static void main(String[] args) {
        String destination = "https://en.wikipedia.org/wiki/Philosophy";
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        try {
            testConjecture(destination, source, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testConjecture(String destination, String source, int limitDepth) throws IOException {
        String curUrl = source;
        for(int i=0;i<limitDepth;i++){
            // 페이지 링크 사이에 순환 존재
            if(visited.contains(curUrl)) {
                System.out.println("Exit : Loop Existence");
                System.out.println(visited);
                return;
            }

            visited.add(curUrl);

            System.out.printf("Fetching ... '%s'\n", curUrl);
            Elements paragraphs = WikiFetcher.getInstance().fetchWikipedia(curUrl);

            WikiLinkFinder linkFinder = new WikiLinkFinder(paragraphs);
            Element link = linkFinder.findFirst();

            if(link == null) {
                System.err.println("Exit : No Link");
                return;
            }
            System.out.printf("<-- Next Page : %s -->\n", link.text());
            curUrl = link.attr("abs:href");
            if(curUrl.equals(destination)) {
                System.out.println("SUCCESS!");
                return;
            }
        }

        System.out.println("FAIL : Max Loop");
        System.out.println("[History]...........");
        System.out.println(visited);

    }


}
