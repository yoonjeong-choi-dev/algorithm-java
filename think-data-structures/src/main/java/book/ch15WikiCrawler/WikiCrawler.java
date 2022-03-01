package book.ch15WikiCrawler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import book.websearchengine.JedisIndex;
import book.websearchengine.WikiFetcher;

public class WikiCrawler {
    public final String source;
    private JedisIndex index;
    private Queue<String> linkQueue = new LinkedList<>();

    public WikiCrawler(String source, JedisIndex index) {
        this.source = source;
        this.index = index;
        linkQueue.offer(source);
    }

    public int queueSize() {
        return linkQueue.size();
    }

    public String crawl(boolean testing) throws IOException {
        // 검색할 url 큐가 비어있으면 크롤링 끝
        if (linkQueue.isEmpty()) return null;

        // 현재 탐색할 큐 검색
        String url = linkQueue.poll();

        // 테스트 코드가 아니고 이미 크롤링을 한 경우(redis 저장소에 저장되어 있는 경우) => 검색 종료
        if (!testing && index.isIndexed(url)) return null;

        // 파싱
        Elements paragraphs;
        if (testing) {
            paragraphs = WikiFetcher.getInstance().readWikipedia(url);
        } else {
            paragraphs = WikiFetcher.getInstance().fetchWikipedia(url);
        }

        // redis 저장소에 저장
        index.indexPage(url, paragraphs);

        // 해당 페이지의 링크들을 큐에 저장
        for (Element paragraph : paragraphs) {
            addLinksInPage(paragraph);
        }

        return url;
    }

    private void addLinksInPage(Element paragraph) {
        // a 태그만 추출
        Elements aTags = paragraph.select("a[href]");
        for (Element aTag : aTags) {
            String aUrl = aTag.attr("href");

            // /wiki/* 형태의 링크들만 위키피디아 내부 링크로 판단하여 큐에 저장
            if (aUrl.startsWith("/wiki/")) {
                linkQueue.offer("https://en.wikipedia.org" + aUrl);
            }
        }
    }



    // Test Method
    public void queueInternalLinks(Elements paragraphs) {
        for (Element paragraph : paragraphs) {
            addLinksInPage(paragraph);
        }
    }
}
