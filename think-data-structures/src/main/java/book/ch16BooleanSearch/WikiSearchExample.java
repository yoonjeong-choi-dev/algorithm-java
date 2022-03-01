package book.ch16BooleanSearch;

import book.websearchengine.JedisIndex;
import book.websearchengine.JedisMaker;
import redis.clients.jedis.Jedis;

import java.io.IOException;

public class WikiSearchExample {
    public static void main(String[] args) throws IOException {

        // make a JedisIndex
        Jedis jedis = JedisMaker.make();
        JedisIndex index = new JedisIndex(jedis);

        // search for the first term
        String term1 = "java";
        System.out.println("Query: " + term1);
        WikiSearch search1 = WikiSearch.search(term1, index);
        search1.printRelevance();

        // search for the second term
        String term2 = "programming";
        System.out.println("Query: " + term2);
        WikiSearch search2 = WikiSearch.search(term2, index);
        search2.printRelevance();

        // compute the intersection of the searches
        System.out.println("Query: " + term1 + " AND " + term2);
        WikiSearch intersection = search1.and(search2);
        intersection.printRelevance();
    }
}
