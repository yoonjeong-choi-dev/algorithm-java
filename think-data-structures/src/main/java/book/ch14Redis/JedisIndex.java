package book.ch14Redis;

import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import book.websearchengine.TermCounter;

public class JedisIndex {
    private Jedis jedis;

    public JedisIndex(Jedis jedis) {
        this.jedis = jedis;
    }

    public void indexPage(String url, Elements paragraphs) {
        // TermCounter 객체 생성
        TermCounter termCounter = new TermCounter(url);
        termCounter.process(paragraphs);

        String termCounterKey = termCounterKey(url);


        // 각 term에 대한 등장 횟수 저장
        Transaction transaction = jedis.multi();

        // 이미 url에 대한 TermCounter 정보가 있는 경우 삭제 후 업데이트
        transaction.del(termCounterKey);

        // 정보 업데이트
        for (String term : termCounter.keySet()) {
            Integer count = termCounter.get(term);

            // hashset 생성 : termCounterKey 키의 값에 Hash(term, count) 저장
            transaction.hset(termCounterKey, term, count.toString());

            // URLSet:term 키 값에 url 저장
            // Set(term, url) 저장
            transaction.sadd(urlSetKey(term), url);
        }
        transaction.exec();
    }

    public Map<String, Integer> getCounts(String term) {
        // term 검색어가 등장하는 모든 url 검색
        Set<String> urls = getURLs(term);

        Map<String, Integer> ret = new HashMap<>();
        for (String url : urls) {
            ret.put(url, getCount(url, term));
        }

        return ret;
    }

    // useful methods
    public Integer getCount(String url, String term) {
        // url에 해당하는 TermCounter 정보
        String redisKey = termCounterKey(url);
        String count = jedis.hget(redisKey, term);
        return new Integer(count);
    }

    public Set<String> getURLs(String term) {
        // term 검색어가 등장하는 페이지들의 url
        return jedis.smembers(urlSetKey(term));
    }

    // helper methods to make key for redis
    private String urlSetKey(String term) {
        // Index 객체 저장용
        // 검색어에 대한 페이지 정보를 저장하기 위한 키
        // 해당 정보는 Set<term, url> 형태로 저장됨
        return "URLSet:" + term;
    }

    private String termCounterKey(String url) {
        // TermCounter 객체 저장용
        // TermCounter 객체는 url 페이지에 있는 모든 단어들의 횟수를 저장
        // 내부 멤버 변수인 Map<String(term), Integer(count)> 정보를 저장하기 위한 키
        return "TermCounter:" + url;
    }

    // helper methods to get some data from redis
    public Set<String> urlSetKeys() {
        return jedis.keys("URLSet:*");
    }

    public Set<String> termCounterKeys() {
        return jedis.keys("TermCounter:*");
    }

    public Set<String> termSet() {
        Set<String> keys = urlSetKeys();
        Set<String> terms = new HashSet<>();

        for (String key : keys) {
            // 기본적으로 URLSet:term 형태의 키로 저장되어 있음
            String[] arr = key.split(":");
            if (arr.length < 2) terms.add("");
            else terms.add(arr[1]);
        }
        return terms;
    }

    // helper methods for debugging
    public void printIndex() {
        for (String term : termSet()) {
            System.out.printf("Term : %s\n", term);

            // 각 검색어에 대해서 검색어가 등장하는 페이지 url 및 검색 횟수
            Set<String> urls = getURLs(term);
            for (String url : urls) {
                Integer count = getCount(url, term);
                System.out.printf("\t%s : %d\n", url, count);
            }
        }
    }

    // helper methods for clear the redis
    public void deleteURLSets() {
        Set<String> keys = urlSetKeys();
        Transaction transaction = jedis.multi();
        for (String key : keys) {
            transaction.del(key);
        }
        transaction.exec();
    }

    public void deleteTermCounters() {
        Set<String> keys = termCounterKeys();
        Transaction transaction = jedis.multi();
        for (String key : keys) {
            transaction.del(key);
        }
        transaction.exec();
    }

    public void deleteAllKeys() {
        Set<String> keys = jedis.keys("*");
        Transaction transaction = jedis.multi();
        for (String key : keys) {
            transaction.del(key);
        }
        transaction.exec();
    }
}
