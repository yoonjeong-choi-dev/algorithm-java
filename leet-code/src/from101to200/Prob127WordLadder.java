package from101to200;

import java.util.*;

// https://leetcode.com/problems/word-ladder/
public class Prob127WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;

        if (!wordList.contains(beginWord)) wordList.add(beginWord);
        int numWords = wordList.size();

        // Make graph
        Map<String, List<String>> adjMap = new HashMap<>();
        for (String word : wordList) adjMap.put(word, new ArrayList<>());
        for (int i = 0; i < numWords - 1; i++) {
            for (int j = i + 1; j < numWords; j++) {
                String s1 = wordList.get(i);
                String s2 = wordList.get(j);

                // check s1 and s2 are connected
                if (isConnected(s1, s2)) {
                    adjMap.get(s1).add(s2);
                    adjMap.get(s2).add(s1);
                }
            }
        }

        // BFS
        int curStep = 1;
        Set<String> visited = new HashSet<>();
        Queue<String> bfs = new ArrayDeque<>();
        bfs.add(beginWord);
        visited.add(beginWord);

        String curWord;
        while (!bfs.isEmpty()) {
            for (int i = bfs.size() - 1; i >= 0; i--) {
                curWord = bfs.poll();
                if (endWord.equals(curWord)) return curStep;

                for (String next : adjMap.get(curWord)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        bfs.add(next);
                    }
                }
            }

            curStep++;
        }

        return 0;
    }

    private boolean isConnected(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int len = s1.length();

        int diff = 0;
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) diff++;
        }

        return diff == 1;
    }
}
