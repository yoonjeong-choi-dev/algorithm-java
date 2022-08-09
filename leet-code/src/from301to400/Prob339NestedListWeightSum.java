package from301to400;

import java.util.List;

// https://leetcode.com/problems/nested-list-weight-sum/

public class Prob339NestedListWeightSum {
    interface NestedInteger {
        boolean isInteger();

        // Return null if this NestedInteger holds a nested list
        Integer getInteger();
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> list, int depth) {
        int ans = 0;
        for (NestedInteger node : list) {
            if (node.isInteger()) {
                ans += depth * node.getInteger();
            } else {
                ans += dfs(node.getList(), depth + 1);
            }
        }

        return ans;
    }
}
