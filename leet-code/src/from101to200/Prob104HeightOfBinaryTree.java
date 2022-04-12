package from101to200;

// https://leetcode.com/problems/maximum-depth-of-binary-tree/
public class Prob104HeightOfBinaryTree {
    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public int maxDepth(TreeNode root) {
        return recursive(root);
    }

    private int recursive(TreeNode node) {
        if(node == null) return 0;

        return 1 + Math.max(recursive(node.left), recursive(node.right));
    }
}
