package from1301to1400;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/deepest-leaves-sum/
public class Prob1302DeepestLeavesSum {
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

    public int deepestLeavesSum(TreeNode root) {
        int ans;

        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);

        TreeNode curNode;
        while (true) {
            ans = 0;
            for (int i = bfs.size(); i > 0; i--) {
                curNode = bfs.poll();
                ans += curNode.val;

                if (curNode.left != null) bfs.add(curNode.left);
                if (curNode.right != null) bfs.add(curNode.right);
            }

            if(bfs.isEmpty())
                return ans;
        }
    }
}
