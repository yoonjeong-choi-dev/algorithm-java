package from1201to1300;

import java.util.*;

// https://leetcode.com/problems/web-crawler/
public class Prob1236WebCrawler {
    interface HtmlParser {
        List<String> getUrls(String url);
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        // http://{host_name}/
        int endHostIdx = getHostEndIdx(startUrl);

        Set<String> visited = new HashSet<>();
        Queue<String> bfs = new ArrayDeque<>();

        visited.add(startUrl);
        bfs.add(startUrl);

        String url;
        List<String> links;
        boolean isSameHost;
        while (!bfs.isEmpty()) {
            url = bfs.poll();
            links = htmlParser.getUrls(url);

            for (String link : links) {
                // Do not crawl the same link twice
                if (visited.contains(link)) continue;

                // Explore only the links that are under the same hostname
                if (endHostIdx != getHostEndIdx(link)) continue;

                isSameHost = true;
                for (int i = 7; i <= endHostIdx; i++) {
                    if (startUrl.charAt(i) != link.charAt(i)) {
                        isSameHost = false;
                        break;
                    }
                }

                if (isSameHost) {
                    bfs.add(link);
                    visited.add(link);
                }
            }
        }

        return new ArrayList<>(visited);
    }

    private int getHostEndIdx(String url) {
        int len = url.length();
        int endHostIdx = 7;
        while (endHostIdx < len && url.charAt(endHostIdx) != '/') endHostIdx++;
        //System.out.printf("%s -> %s\n", url, url.substring(0, endHostIdx));
        return endHostIdx - 1;
    }
}
