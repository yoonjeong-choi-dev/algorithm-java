package from601to700;

// https://leetcode.com/problems/trim-a-binary-search-tree/
public class Prob669TrimBST {
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

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;

        // 루트가 최대값보다 큰 경우에는 왼쪽 서브 트리만 고려하면 된다 i.e 오른쪽 서브트리는 삭제
        if (root.val > high) return trimBST(root.left, low, high);

        // 루트가 최소값보다 작은 경우에는 오른쪽 서브 트리만 고려하면 된다 i.e 왼쪽 서브트리는 삭제
        if (root.val < low) return trimBST(root.right, low, high);

        // 각 서브 트리에 대해서 재귀호출
        root.left = trimBST(root.left, low, root.val);
        root.right = trimBST(root.right, root.val, high);

        return root;
    }
}
