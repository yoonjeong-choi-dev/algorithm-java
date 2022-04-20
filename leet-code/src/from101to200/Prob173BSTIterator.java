package from101to200;

import java.util.Stack;

// https://leetcode.com/problems/binary-search-tree-iterator/
public class Prob173BSTIterator {
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

    // iterator over the in-order traversal of a binary search tree (BST)
    class BSTIterator {

        private final Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            stack.add(root);
        }

        public int next() {
            TreeNode curNode;
            while(!stack.isEmpty()) {
                curNode = stack.pop();
                if(curNode.left == null && curNode.right == null) return curNode.val;

                // 스택을 이용하기 때문에 right -> root -> left 순서로 저장
                if(curNode.right != null) stack.add(curNode.right);

                // 트리의 상태 변경 없이 저장하기 위해 현재 노드를 이용하여 리프 노드 생성
                stack.add(new TreeNode(curNode.val));

                if(curNode.left != null) stack.add(curNode.left);
            }

            // 여기는 절대 올 수 없음
            return -1;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}
