package from201to300;

// https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
public class Prob255VerifyPreorderSequenceInBinarySearchTree {

    private int[] preorder;

    public boolean verifyPreorder(int[] preorder) {
        // pre : root -> left -> right
        this.preorder = preorder;
        return recur(0, preorder.length - 1, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean recur(int start, int end, int upper, int lower) {
        // null node
        if(start > end) return true;

        // lower < root < upper
        int root = preorder[start];
        if (root <= lower || root >= upper) return false;

        // leaf node
        if (start == end) return true;

        // [start], [start+1, rightStart-1], [rightStart, end]
        int rightStart = start + 1;
        for (; rightStart <= end; rightStart++) {
            if (preorder[rightStart] > root) break;
        }

        // left tree check
        if (!recur(start + 1, rightStart - 1, root, lower)) return false;

        // right tree check
        if (!recur(rightStart, end, upper, root)) return false;

        return true;
    }
}
