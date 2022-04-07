package from601to700;

// https://leetcode.com/problems/merge-two-binary-trees/
public class Prob617MergeTwoBinaryTrees {
    public class TreeNode {
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

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;

        treeTraverse(root1, root2);

        return root1;
    }

    private void treeTraverse(TreeNode node1, TreeNode node2) {
        // Assumption : node1 != null
        if (node2 == null) return;

        // 현재 노드 합치기
        node1.val = node1.val + node2.val;

        // 왼쪽 서브트리
        if (node1.left != null) {
            treeTraverse(node1.left, node2.left);
        } else {
            node1.left = node2.left;
        }

        // 오른쪽 서브트리
        if (node1.right != null) {
            treeTraverse(node1.right, node2.right);
        } else {
            node1.right = node2.right;
        }
    }
}
