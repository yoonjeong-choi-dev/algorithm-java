package from501to600;

// https://leetcode.com/problems/convert-bst-to-greater-tree/
public class Prob538ConvertBSTToGreaterTree {
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

    private int curSum;
    public TreeNode convertBST(TreeNode root) {
        if(root ==null) return null;

        curSum = 0;
        reverseInorder(root);

        return root;
    }

    // right -> root -> left 탐색 : 최대값부터 최소값까지 순회
    // 최대값부터 순회하면서 합을 누적
    private void reverseInorder(TreeNode node) {
        if(node.right != null) reverseInorder(node.right);

        // 현재 노드 방문 및 누적합 업데이트
        node.val += curSum;
        curSum = node.val;

        if(node.left != null) reverseInorder(node.left);
    }
}
