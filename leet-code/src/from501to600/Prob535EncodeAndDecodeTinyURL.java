package from501to600;

import java.util.HashMap;

// https://leetcode.com/problems/encode-and-decode-tinyurl/
public class Prob535EncodeAndDecodeTinyURL {

    // Base-62 변환 사용 : [0-9], [a-z], [A-Z] 로 구성된 가변 길이의 단축된 URL 생성기
    class Codec {
        private final String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private final int base = base62.length();
        private int curId = 1;
        private final String baseUrl = "http://tinyurl.com/";

        // shorten -> url
        private final HashMap<String, String> hashMap = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String shorten = makeShortenUrl();
            hashMap.put(shorten, longUrl);

            return baseUrl + shorten;
        }

        private String makeShortenUrl() {
            // 현재 id를 이용하여 base62 인코딩
            StringBuilder sb = new StringBuilder();
            int num = curId;
            while (num != 0) {
                sb.append(base62.charAt(num % 62));
                num /= 62;
            }

            curId++;
            return sb.toString();
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return hashMap.get(shortUrl.replace(baseUrl, ""));
        }
    }
}
