package from1201to1300;

import java.util.*;

public class Prob1214TwoSumBST {
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

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        // root1 정보 저장
        Set<Integer> set = new HashSet<>();

        // bfs
        Queue<TreeNode> bfs = new ArrayDeque<>();
        bfs.add(root1);

        TreeNode cur;
        while (!bfs.isEmpty()) {
            cur = bfs.poll();
            set.add(cur.val);

            if(cur.left != null) bfs.add(cur.left);
            if(cur.right != null) bfs.add(cur.right);
        }

        // bfs with root2
        bfs.add(root2);
        while(!bfs.isEmpty()) {
            cur = bfs.poll();
            if(set.contains(target - cur.val)) return true;

            if(cur.left != null) bfs.add(cur.left);
            if(cur.right != null) bfs.add(cur.right);
        }

        return false;
    }

}
