package from101to200;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/binary-tree-preorder-traversal/
// Ref : Problem 589, 94, 145
public class Prob144BinaryTreePreorder {
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

    // root -> left -> right
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new LinkedList<>();
        if (root == null) return ret;

        // 재귀 호출 방식을 스택으로 구현
        Stack<TreeNode> dfs = new Stack<>();
        dfs.add(root);

        TreeNode curNode;
        while (!dfs.isEmpty()) {
            curNode = dfs.pop();
            ret.add(curNode.val);

            // 스택으로 구현하기 때문에 오른쪽 자식 노드부터 저장
            if (curNode.right != null) {
                dfs.add(curNode.right);
            }
            if (curNode.left != null) {
                dfs.add(curNode.left);
            }
        }

        return ret;
    }
}
