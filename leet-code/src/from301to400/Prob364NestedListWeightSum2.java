package from301to400;

import java.util.List;

// https://leetcode.com/problems/nested-list-weight-sum-ii/
public class Prob364NestedListWeightSum2 {
    interface NestedInteger {
        boolean isInteger();
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    private int maxDepth;

    public int depthSumInverse(List<NestedInteger> nestedList) {
        maxDepth = getMaxDepth(nestedList);
        return getSum(nestedList, 0);
    }

    private int getMaxDepth(List<NestedInteger> list) {
        int ans = 1;
        for (NestedInteger node : list) {
            if (!node.isInteger() ) {
                ans = Math.max(ans, getMaxDepth(node.getList()) + 1);
            }
        }
        return ans;
    }


    private int getSum(List<NestedInteger> list, int depth) {
        int ans = 0;
        for (NestedInteger node : list) {
            if (node.isInteger()) {
                ans += node.getInteger() * (maxDepth - depth);
            } else {
                ans += getSum(node.getList(), depth + 1);
            }
        }
        return ans;
    }
}
