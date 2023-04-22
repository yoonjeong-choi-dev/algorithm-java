package from101to200;

import java.util.*;

// https://leetcode.com/problems/word-ladder-ii/
public class Prob126WordLadder2 {

    class PathInfo {
        int idx;
        List<Integer> path;

        PathInfo(int idx, List<Integer> path) {
            this.idx = idx;
            this.path = path;
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // Make Graph
        wordList.add(beginWord);
        int numVertex = wordList.size();
        int endIdx = -1, startIdx = numVertex - 1;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numVertex; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < numVertex; i++) {
            String cur = wordList.get(i);
            if (endIdx == -1 && cur.equals(endWord)) endIdx = i;

            for (int j = i + 1; j < numVertex; j++) {
                if (checkConnection(cur, wordList.get(j))) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        if (endIdx == -1) return new ArrayList<>();

        List<List<String>> ans = new ArrayList<>();

        Set<Integer> visited = new HashSet<>();
        Queue<PathInfo> bfs = new ArrayDeque<>();
        PathInfo start = new PathInfo(startIdx, new ArrayList<>());
        start.path.add(startIdx);
        bfs.add(start);

        boolean isEnd = false;
        List<PathInfo> curLevel;
        while (!bfs.isEmpty()) {
            curLevel = new ArrayList<>();
            while (!bfs.isEmpty()) curLevel.add(bfs.poll());
            for (PathInfo curInfo : curLevel) visited.add(curInfo.idx);

            for (PathInfo curInfo : curLevel) {

                for (int nextIdx : graph.get(curInfo.idx)) {
                    // 다음 노드가 현재 레벨의 노드인 경우는 다시 돌아갈 필요 X : 최단 경로를 구하는 것이기 때문
                    if (visited.contains(nextIdx)) continue;

                    if (nextIdx == endIdx) {
                        // find the shortest path
                        List<String> curAns = new ArrayList<>();
                        for (int path : curInfo.path) curAns.add(wordList.get(path));
                        curAns.add(endWord);
                        ans.add(curAns);

                        isEnd = true;
                    } else {
                        List<Integer> nextPath = new ArrayList<>(curInfo.path);
                        nextPath.add(nextIdx);

                        bfs.add(new PathInfo(nextIdx, nextPath));
                    }
                }
            }

            if (isEnd) break;
        }


        return ans;
    }

    private boolean checkConnection(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int diff = 0, len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) diff++;

            if (diff > 1) return false;
        }
        return true;
    }
}
