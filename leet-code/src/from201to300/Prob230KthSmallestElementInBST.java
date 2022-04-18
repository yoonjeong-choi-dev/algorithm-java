package from201to300;

import java.util.Stack;

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
public class Prob230KthSmallestElementInBST {
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

    private int curCount;
    private int ans;

    public int kthSmallest(TreeNode root, int k) {
        curCount = k;
        ans = -1;

        inorderRecursive(root);
        return ans;
    }

    private void inorderRecursive(TreeNode node) {
        if (node == null) return;
        if (curCount == 0) return;

        inorderRecursive(node.left);

        curCount--;
        if (curCount == 0) {
            ans = node.val;
            return;
        }

        inorderRecursive(node.right);
    }

    private int inorderWithStackSolution(TreeNode root, int k) {
        // inorder of BST => increasing sort
        Stack<TreeNode> dfs = new Stack<>();
        dfs.add(root);

        TreeNode curNode, temp;
        while (!dfs.isEmpty()) {
            curNode = dfs.pop();

            // 리프 노드인 경우 방문
            if (curNode.left == null && curNode.right == null) {
                k--;

                if (k == 0) return curNode.val;
                continue;
            }

            // 스택을 통한 inorder => right -> root -> left 순으로 저장해야함
            if (curNode.right != null) {
                dfs.push(curNode.right);
                curNode.right = null;
            }

            temp = curNode.left;
            curNode.left = null;
            dfs.push(curNode);

            if (temp != null) dfs.push(temp);
        }

        // 유효한 k 값인 경우 이 코드는 도달 불가능
        return -1;
    }
}