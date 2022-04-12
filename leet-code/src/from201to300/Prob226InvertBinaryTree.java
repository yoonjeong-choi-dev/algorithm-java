package from201to300;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/invert-binary-tree/
public class Prob226InvertBinaryTree {
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

    public TreeNode invertTree(TreeNode root) {
        return recursiveSolution(root);
    }

    private TreeNode recursiveSolution(TreeNode root) {
        if (root == null) return null;

        // 각 자식 노드들에 대해서 반전
        TreeNode left = recursiveSolution(root.left);
        TreeNode right = recursiveSolution(root.right);

        // 현재 노드의 자식 노드 교환
        root.left = right;
        root.right = left;
        return root;
    }

    private TreeNode useQueue(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        TreeNode cur, temp;

        while (!bfs.isEmpty()) {
            // 현재 노드에 대해서 자식 교환
            cur = bfs.poll();
            temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            // 자식 노드 큐에 넣어서 bfs
            if (cur.left != null) bfs.add(cur.left);
            if (cur.right != null) bfs.add(cur.right);
        }

        return root;
    }
}
