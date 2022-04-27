package from101to200;

import java.util.*;

// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
public class Prob103BinaryTreeZigzagLevelOrderTraversal {
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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();

        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.offer(root);
        boolean isReverse = false;

        TreeNode curNode;
        while (!bfs.isEmpty()) {
            // 현재 레벨 탐색
            List<Integer> curAns = new ArrayList<>();
            for (int i = bfs.size(); i > 0; i--) {
                curNode = bfs.poll();
                curAns.add(curNode.val);

                if (curNode.left != null) bfs.offer(curNode.left);
                if (curNode.right != null) bfs.offer(curNode.right);
            }

            if(isReverse) Collections.reverse(curAns);

            ans.add(curAns);
            isReverse = !isReverse;
        }

        return ans;
    }

}
