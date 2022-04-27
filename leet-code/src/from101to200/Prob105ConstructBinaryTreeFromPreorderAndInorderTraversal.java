package from101to200;

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class Prob105ConstructBinaryTreeFromPreorderAndInorderTraversal {
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

    private int[] preorder, inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        int end = preorder.length - 1;
        return recursive(0, end, 0, end);
    }

    // preorder[start:end] 와 inorder[start:end] 정보에 대한 트리 반환
    private TreeNode recursive(int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        if (preStart == preEnd) return new TreeNode(preorder[preStart]);


        // preorder and inorder consist of unique values
        // => root 값을 이용하여 inorder 정보 찾기
        int root = preorder[preStart];
        int curIdx;
        for (curIdx = inStart; curIdx <= inEnd; curIdx++) {
            if (inorder[curIdx] == root) break;
        }

        // inorder left, root, right :  [inStart:curIdx-1], [curIdx], [curIdx+1, inEnd]
        int preorderLeftEnd = preStart + curIdx - inStart;

        return new TreeNode(root,
                recursive(preStart + 1, preorderLeftEnd, inStart, curIdx - 1),
                recursive(preorderLeftEnd + 1, preEnd, curIdx + 1, inEnd));
    }
}
