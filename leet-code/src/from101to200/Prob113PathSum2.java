package from101to200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/path-sum-ii/
public class Prob113PathSum2 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    List<List<Integer>> ans;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans = new LinkedList<>();
        dfs(root, targetSum, new LinkedList<>());
        return ans;
    }


    private void dfs(TreeNode node, int remain, List<Integer> curPath) {
        if (node == null) return;

        // 리프 노드이고 나머지가 현재 노드값과 같은 경우 정답 경로
        if (node.left == null && node.right == null && remain == node.val) {
            List<Integer> curAns = new ArrayList<>(curPath);
            curAns.add(node.val);
            ans.add(curAns);
            return;
        }

        curPath.add(node.val);
        dfs(node.left, remain - node.val, curPath);
        dfs(node.right, remain - node.val, curPath);
        curPath.remove(curPath.size() - 1);
    }
}
