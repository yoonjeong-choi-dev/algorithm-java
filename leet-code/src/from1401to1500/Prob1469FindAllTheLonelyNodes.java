package from1401to1500;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/find-all-the-lonely-nodes/
public class Prob1469FindAllTheLonelyNodes {
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

    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> ans = new LinkedList<>();

        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);

        TreeNode curNode;
        while (!bfs.isEmpty()) {
            curNode = bfs.poll();

            if (curNode.left == null && curNode.right != null) {
                ans.add(curNode.right.val);
            } else if (curNode.left != null && curNode.right == null) {
                ans.add(curNode.left.val);
            }

            if(curNode.left != null) bfs.add(curNode.left);
            if(curNode.right != null) bfs.add(curNode.right);
        }

        return ans;
    }
}
