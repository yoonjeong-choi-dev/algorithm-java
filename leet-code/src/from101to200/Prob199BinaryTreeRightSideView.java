package from101to200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-right-side-view/
public class Prob199BinaryTreeRightSideView {
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

    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<>();

        List<Integer> ans = new ArrayList<>();

        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.offer(root);
        TreeNode curNode;
        while (!bfs.isEmpty()) {
            // search cur Level : 마지막 1개 빼고 탐색
            for (int i = bfs.size(); i > 0; i--) {
                curNode = bfs.poll();
                if (curNode.left != null) bfs.offer(curNode.left);
                if (curNode.right != null) bfs.offer(curNode.right);

                if (i == 1) ans.add(curNode.val);
            }
        }

        return ans;
    }
}
