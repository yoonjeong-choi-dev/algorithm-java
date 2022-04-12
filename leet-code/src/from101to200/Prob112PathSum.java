package from101to200;

import java.util.LinkedList;
import java.util.Stack;

// https://leetcode.com/problems/path-sum/
public class Prob112PathSum {
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


    // Improve Runtime
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 현재 노드가 없는 경우 무조건 거짓
        if (root == null) return false;

        // 리프 노드이고 경로 합이 타겟인 경우
        if (root.left == null && root.right == null && targetSum == root.val) return true;

        // 왼쪽 및 오른쪽 자식 노드에 대해서 탐색
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    private boolean useStackDFS(TreeNode root, int targetSum) {
        if (root == null) return false;

        LinkedList<TreeNode> nodes = new LinkedList<>();
        LinkedList<Integer> sums = new LinkedList<>();
        nodes.add(root);
        sums.add(root.val);

        // DFS
        TreeNode curNode;
        int curSum;
        while (!nodes.isEmpty()) {
            curNode = nodes.pollLast();
            curSum = sums.pollLast();

            // 현재 노드가 리프 노드이고, 합이 타겟인 경우 => TRUE
            if (curNode.left == null && curNode.right == null && curSum == targetSum) {
                return true;
            }

            // 스택에 저장해야 하므로 오른쪽 노드부터 저장
            if (curNode.right != null) {
                nodes.add(curNode.right);
                sums.add(curSum + curNode.right.val);
            }

            if (curNode.left != null) {
                nodes.add(curNode.left);
                sums.add(curSum + curNode.left.val);
            }
        }

        // 탐색을 완료할 때까지 찾지 못한 경우
        return false;
    }
}
