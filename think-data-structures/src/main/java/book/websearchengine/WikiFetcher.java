package book.websearchengine;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

// 크롤링 시, 요청 시간을 조절하여 서비스 약관 위반을 막기 위한 싱글톤 인스턴스
public class WikiFetcher {
    // 싱글턴
    private static WikiFetcher instance;
    private WikiFetcher() {}
    public static WikiFetcher getInstance() {
        if(instance == null) {
            instance = new WikiFetcher();
        }
        return instance;
    }

    // 요청 시간 조절을 위한 변수
    private long lastRequestTime = -1;
    private final long minInterval = 1000;

    public Elements fetchWikipedia(String url) throws IOException {
        // 요청 사이의 텀을 확인하여 필요한 경우 잠시 시간을 기다린 뒤 요청
        sleepIfNeeded();

        Connection conn = Jsoup.connect(url);
        Document doc = conn.get();
        Element content = doc.getElementById("mw-content-text");
        return content.select("p");
    }

    private void sleepIfNeeded() {
        // 직전에 요청한 경우
        if(lastRequestTime != -1) {
            long currentTime = System.currentTimeMillis();
            long nextRequestTime = lastRequestTime + minInterval;
            if(currentTime < nextRequestTime) {
                try {
                    Thread.sleep(nextRequestTime - currentTime);
                } catch (InterruptedException e) {
                    System.out.println("[WARN] sleep interrupted in fetchWikipedia");
                }
            }
        }
        lastRequestTime = System.currentTimeMillis();
    }
}
