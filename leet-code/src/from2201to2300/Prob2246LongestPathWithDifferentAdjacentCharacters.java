package from2201to2300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/longest-path-with-different-adjacent-characters/
public class Prob2246LongestPathWithDifferentAdjacentCharacters {

    // node -> list of children
    private Map<Integer, List<Integer>> children;
    private String s;
    private int ans;

    public int longestPath(int[] parent, String s) {
        ans = 1;
        this.s = s;
        children = new HashMap<>();

        for (int i = 1; i < parent.length; i++) {
            if (!children.containsKey(parent[i])) children.put(parent[i], new ArrayList<>());
            children.get(parent[i]).add(i);
        }

        dfs(0);
        return ans;
    }

    // node 를 루트로 하는 서브트리에 대한 문제 정답 반환
    private int dfs(int node) {
        // leaf node
        if (!children.containsKey(node)) return 1;

        // 자식 서브트리들 결과 중에 최대값 2개 가져오기 => 루트 노드를 지나는 path 를 만들기 위해서
        // childRet[0] : first, childRet[1] : second
        int[] maxVal = new int[2];
        int curAns;
        for (int child : children.get(node)) {
            curAns = dfs(child);
            ans = Math.max(ans, curAns);

            // 유효한 자식 노드에 대한 경로가 존재하는 경우
            if (s.charAt(node) != s.charAt(child)) {
                if (curAns > maxVal[0]) {
                    maxVal[1] = maxVal[0];
                    maxVal[0] = curAns;
                } else if (curAns > maxVal[1]) {
                    maxVal[1] = curAns;
                }
            }
        }

        ans = Math.max(ans, 1 + maxVal[0] + maxVal[1]);
        return 1 + maxVal[0];
    }
}