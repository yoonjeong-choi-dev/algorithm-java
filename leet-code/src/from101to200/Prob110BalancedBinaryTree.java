package from101to200;

// https://leetcode.com/problems/balanced-binary-tree/
public class Prob110BalancedBinaryTree {
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

    public boolean isBalanced(TreeNode root) {

        return getHeight(root) != -1;
    }

    // 균형이 잡히지 않은 경우 -1 반환
    private int getHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        if (leftHeight == -1 || rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Make Examples
    private TreeNode[] makeRoots() {
        // Example 1
        TreeNode l1 = new TreeNode(9);
        TreeNode l2 = new TreeNode(15);
        TreeNode r2 = new TreeNode(7);
        TreeNode r1 = new TreeNode(20, l2, r2);
        TreeNode root1 = new TreeNode(3, l1, r1);

        // Example 2
        TreeNode leaf1 = new TreeNode(4);
        TreeNode leaf2 = new TreeNode(4);
        TreeNode node1 = new TreeNode(3, leaf1, leaf2);
        TreeNode leaf3 = new TreeNode(3);
        TreeNode left = new TreeNode(2, node1, leaf3);
        TreeNode right = new TreeNode(2);
        TreeNode root2 = new TreeNode(1, left, right);

        // Example 3
        TreeNode root3 = new TreeNode();

        return new TreeNode[]{root1, root2, root3};
    }

    public static void main(String[] args) {
        Prob110BalancedBinaryTree sol = new Prob110BalancedBinaryTree();

        TreeNode[] roots = sol.makeRoots();

        boolean[] ans = {true, false, true};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.isBalanced(roots[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
