package from401to500;

import java.util.*;

// https://leetcode.com/problems/sort-characters-by-frequency/
public class Prob451SortCharactersByFrequency {
    public String frequencySort(String s) {
        int len = s.length();
        Map<Character, Integer> counters = new HashMap<>();

        char curChar;
        for (int i = 0; i < len; i++) {
            curChar = s.charAt(i);
            counters.put(curChar, counters.getOrDefault(curChar, 0) + 1);
        }

        Character[] occurChar = new Character[counters.size()];

        int idx = 0;
        for (Character c : counters.keySet()) {
            occurChar[idx++] = c;
        }

        Arrays.sort(occurChar, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return counters.get(o2) - counters.get(o1);
            }
        });

        StringBuilder ans = new StringBuilder(len);
        for (Character c : occurChar) {
            int occur = counters.get(c);
            for (int i = 0; i < occur; i++) ans.append(c);
        }

        return ans.toString();
    }

    private String bucketSortSolution(String s) {
        int len = s.length();
        Map<Character, Integer> counters = new HashMap<>();

        char curChar;
        for (int i = 0; i < len; i++) {
            curChar = s.charAt(i);
            counters.put(curChar, counters.getOrDefault(curChar, 0) + 1);
        }

        // 버킷 정렬을 위해 최대 문자 발생 횟수 저장
        int maxOccur = Collections.max(counters.values());

        List<List<Character>> buckets = new ArrayList<>(maxOccur + 1);
        for (int i = 0; i <= maxOccur; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Map.Entry<Character, Integer> entry : counters.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        StringBuilder ans = new StringBuilder(len);
        for (int i = maxOccur; i > 0; i--) {
            for (Character c : buckets.get(i)) {
                for (int j = 0; j < i; j++) ans.append(c);
            }
        }
        return ans.toString();
    }
}
