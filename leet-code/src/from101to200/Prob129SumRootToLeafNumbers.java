package from101to200;

import java.util.Stack;

// https://leetcode.com/problems/sum-root-to-leaf-numbers/
public class Prob129SumRootToLeafNumbers {
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

    public int sumNumbers(TreeNode root) {
        int ans = 0;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> pathStack = new Stack<>();
        nodeStack.push(root);
        pathStack.push(0);

        TreeNode curNode;
        int curPath;
        while (!nodeStack.isEmpty()) {
            curNode = nodeStack.pop();
            curPath = pathStack.pop() * 10 + curNode.val;

            if(curNode.left == null && curNode.right == null) {
                ans += curPath;
                continue;
            }

            if(curNode.left != null) {
                nodeStack.push(curNode.left);
                pathStack.push(curPath);
            }

            if(curNode.right != null) {
                nodeStack.push(curNode.right);
                pathStack.push(curPath);
            }
        }

        return ans;
    }
}
