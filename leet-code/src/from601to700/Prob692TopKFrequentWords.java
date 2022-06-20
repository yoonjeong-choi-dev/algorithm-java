package from601to700;

import java.util.*;

// https://leetcode.com/problems/top-k-frequent-words/
public class Prob692TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> occurMap = new HashMap<>();
        for (String word : words) {
            occurMap.put(word, occurMap.getOrDefault(word, 0) + 1);
        }

        // min heap
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (occurMap.get(o1) != occurMap.get(o2)) {
                    return occurMap.get(o1) - occurMap.get(o2);
                } else {
                    return o2.compareTo(o1);
                }
            }
        });

        for (String word : occurMap.keySet()) {
            pq.add(word);
            if (pq.size() > k) pq.poll();
        }

        LinkedList<String> ans = new LinkedList<>();
        while (!pq.isEmpty()) {
            ans.addFirst(pq.poll());
        }
        return ans;
    }
}
