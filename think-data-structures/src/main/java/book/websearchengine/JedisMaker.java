package book.websearchengine;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

public class JedisMaker {

    public static Jedis make() throws IOException {
        // 설정 파일 로드
        String filename = "redis_config.txt";
        URL fileURL = JedisMaker.class.getClassLoader().getResource(filename);
        String filePath = URLDecoder.decode(fileURL.getFile(), "UTF-8");

        // 파일을 읽어 redis 접속 url 가져오기
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found : " + filename);
            printInstructions();
            return null;
        }

        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) break;
            stringBuilder.append(line);
        }
        bufferedReader.close();

        // url 정보 파싱
        URI uri;
        try {
            uri = new URI(stringBuilder.toString());
        } catch (URISyntaxException e) {
            System.out.println("Invalid url data in " + filename);
            System.out.println("The data is not a valid URL");
            printInstructions();
            return null;
        }

        String host = uri.getHost();
        int port = uri.getPort();
        String auth = uri.getAuthority().split("[:@]")[1];

        // Connect to redis server
        Jedis jedis = new Jedis(host, port);

        try {
            jedis.auth(auth);
        } catch (Exception e) {
            System.out.printf("Trying to connect to %s\n", host);
            System.out.printf("on port %d\n", port);
            System.out.printf("with auth code : %s\n", auth);
            System.out.printf("Got Exception : %s\n", e.toString());
            printInstructions();
            return null;
        }

        return jedis;
    }

    private static void printInstructions() {
        System.out.println("");
        System.out.println("To connect to RedisToGo, you have to provide a file called");
        System.out.println("redis_url.txt that contains the URL of your Redis server.");
        System.out.println("If you select an instance on the RedisToGo web page,");
        System.out.println("you should see a URL that contains the information you need:");
        System.out.println("redis://redistogo:AUTH@HOST:PORT");
        System.out.println("Create a file called redis_url.txt in the src/resources");
        System.out.println("directory, and paste in the URL.");
    }

    public static void main(String[] args) throws IOException {
        Jedis jedis = make();

        // String Type value
        jedis.set("mykey", "myvalue");
        String value = jedis.get("mykey");
        System.out.println("Got value: " + value);

        // Set Type value
        jedis.sadd("myset", "element1", "element2", "element3");
        System.out.println("element2 is member: " + jedis.sismember("myset", "element2"));

        // List Type value
        jedis.rpush("mylist", "element1", "element2", "element3");
        System.out.println("element at index 1: " + jedis.lindex("mylist", 1));

        // Hash Type value
        jedis.hset("myhash", "word1", Integer.toString(2));
        jedis.hincrBy("myhash", "word2", 1);
        System.out.println("frequency of word1: " + jedis.hget("myhash", "word1"));
        System.out.println("frequency of word2: " + jedis.hget("myhash", "word2"));

        jedis.close();
    }
}
