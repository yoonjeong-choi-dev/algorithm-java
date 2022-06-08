package from201to300;

import java.util.*;

// https://leetcode.com/problems/alien-dictionary/
public class Prob269AlienDictionary {
    public String alienOrder(String[] words) {
        int numWords = words.length;

        Set<Character> allChars = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                allChars.add(c);
            }
        }

        // Create Graph
        Map<Character, Set<Character>> graph = new HashMap<>();

        // 단어 간 선후 관계를 통해 연결 정보 업데이트
        // => 단어들을 구성하는 문자들은 모두 연결되어 있음 i.e connected graph
        for (int i = 0; i < numWords - 1; i++) {
            for (int j = i + 1; j < numWords; j++) {
                // word1 -> word2
                String word1 = words[i];
                String word2 = words[j];

                int minLen = Math.min(word1.length(), word2.length());
                boolean isSubstr = true;

                for (int k = 0; k < minLen; k++) {
                    if (word1.charAt(k) != word2.charAt(k)) {
                        // c1 -> c2
                        char c1 = word1.charAt(k);
                        char c2 = word2.charAt(k);

                        if(!graph.containsKey(c1)) graph.put(c1, new HashSet<>());
                        if(!graph.containsKey(c2)) graph.put(c2, new HashSet<>());

                        graph.get(c1).add(c2);

                        isSubstr = false;
                        break;
                    }
                }

                // If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
                if (isSubstr && word1.length() > word2.length()) return "";
            }
        }

        int[] inDegree = new int[26];

        for (Character key : graph.keySet()) {
            for (Character c : graph.get(key)) {
                inDegree[c - 'a']++;
            }
        }

        List<Character> roots = new ArrayList<>();
        for (Character key : graph.keySet()) {
            if (inDegree[key - 'a'] == 0) roots.add(key);
        }

        // in-degree == 0 인 노드가 없는 경우 : 사이클 존재
        if (roots.isEmpty() && !graph.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();

        Set<Character> isVisited = new HashSet<>();
        List<Character> nextRoots;
        while (!roots.isEmpty()) {
            nextRoots = new ArrayList<>();

            // 현재 루트 노드들 삭제
            for (Character root : roots) {
                sb.append(root);
                isVisited.add(root);

                for (Character next : graph.get(root)) {
                    // 사이클 존재
                    if (isVisited.contains(next)) return "";

                    inDegree[next - 'a']--;
                    if (inDegree[next - 'a'] == 0) nextRoots.add(next);
                }
            }

            roots = nextRoots;
        }

        // 위상 정렬 이후 탐색지 않은 문자가 있는 경우
        if(isVisited.size() != graph.size()) return "";

        // 마지막으로 우선순위가 없는 문자들을 마지막에 붙임
        for (Character key : allChars) {
            if (!isVisited.contains(key)) sb.append(key);
        }

        return sb.toString();
    }
}
