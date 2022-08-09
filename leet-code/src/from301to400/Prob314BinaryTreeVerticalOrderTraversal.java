package from301to400;

import java.util.*;

// https://leetcode.com/problems/binary-tree-vertical-order-traversal/
public class Prob314BinaryTreeVerticalOrderTraversal {
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


    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Map<Integer, List<Integer>> columns = new HashMap<>();

        // BFS
        Queue<TreeNode> nodes = new ArrayDeque<>();
        Queue<Integer> cols = new ArrayDeque<>();

        nodes.add(root);
        cols.add(0);

        TreeNode curNode;
        int curCol;
        while (!nodes.isEmpty()) {
            curNode = nodes.poll();
            curCol = cols.poll();

            if (!columns.containsKey(curCol)) columns.put(curCol, new ArrayList<>());
            columns.get(curCol).add(curNode.val);

            // If two nodes are in the same row and column, the order should be from left to right
            // => left 부터 방문
            if (curNode.left != null) {
                nodes.add(curNode.left);
                cols.add(curCol - 1);
            }
            if (curNode.right != null) {
                nodes.add(curNode.right);
                cols.add(curCol + 1);
            }
        }


        // columns 키 값들은 연속적 -> 최소 및 최대값 계산
        int minCol = 0, maxCol = 0;
        for (int col : columns.keySet()) {
            minCol = Math.min(minCol, col);
            maxCol = Math.max(maxCol, col);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int col = minCol; col <= maxCol; col++) {
            ans.add(columns.get(col));
        }
        return ans;
    }

}
