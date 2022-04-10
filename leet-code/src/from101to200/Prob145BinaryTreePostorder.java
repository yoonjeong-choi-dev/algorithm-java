package from101to200;

import from1to100.Prob94BinaryTreeInorder;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/binary-tree-postorder-traversal/
// Ref : Problem 144, 94
public class Prob145BinaryTreePostorder {
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

    // left -> right -> root
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new LinkedList<>();
        if (root == null) return ret;

        // 재귀 호출 방식을 스택으로 구현
        Stack<TreeNode> dfs = new Stack<>();
        dfs.add(root);

        TreeNode curNode,left,right;
        while (!dfs.isEmpty()) {
            curNode = dfs.pop();
            left = curNode.left;
            right = curNode.right;

            if(left == null && right == null) {
                // 자식 노드들이 없는 경우 결과 저장
                ret.add(curNode.val);
                continue;
            }

            // 자식 노드가 있는 경우, 자식 노드들 탐색 후 저장해야 하므로 현재 노드를 리프 노드처럼 만든다
            curNode.right = curNode.left = null;
            dfs.add(curNode);

            // 스택으로 구현하기 때문에 오른쪽 자식 노드부터 저장
            if (right != null) {
                dfs.add(right);
            }
            if (left != null) {
                dfs.add(left);
            }

        }

        return ret;
    }
}
