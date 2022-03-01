package book.ch14Redis;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.Map;

import book.websearchengine.WikiFetcher;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisIndexTest {
    private static String url1, url2;
    private Jedis jedis;
    private JedisIndex index;

    @Before
    public void setUp() throws Exception {
        jedis = JedisMaker.make();
        index = new JedisIndex(jedis);

        loadIndex(index);
    }

    private static void loadIndex(JedisIndex index) throws IOException {
        url1 = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements paragraphs = WikiFetcher.getInstance().fetchWikipedia(url1);
        index.indexPage(url1, paragraphs);

        url2 = "https://en.wikipedia.org/wiki/Programming_language";
        paragraphs = WikiFetcher.getInstance().fetchWikipedia(url2);
        index.indexPage(url2, paragraphs);
    }

    @After
    public void tearDown() throws Exception {
        jedis.close();
    }

    @Test
    public void testGetCounts() {
        Map<String, Integer> map = index.getCounts("the");
        assertThat(map.get(url1), is(260));
        assertThat(map.get(url2), is(270));
    }

    @Test
    public void printIndexTest() {
        index.printIndex();
    }
}