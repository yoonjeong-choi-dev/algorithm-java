package from801to900;

import java.util.*;

// https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
public class Prob863AllNodesDistanceKInBinaryTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Create Parent Map
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root, null);

        Queue<TreeNode> bfs = new ArrayDeque<>();
        bfs.add(root);

        TreeNode curNode;
        while (!bfs.isEmpty()) {
            curNode = bfs.poll();

            if (curNode.left != null) {
                parentMap.put(curNode.left, curNode);
                bfs.add(curNode.left);
            }

            if (curNode.right != null) {
                parentMap.put(curNode.right, curNode);
                bfs.add(curNode.right);
            }
        }

        // bfs from target with child,left,parent
        List<Integer> ans = new ArrayList<>();

        Set<TreeNode> visited = new HashSet<>();
        bfs.add(target);

        int curDist = 0;
        TreeNode parent;
        while (!bfs.isEmpty()) {
            if (curDist == k) {
                while (!bfs.isEmpty()) {
                    ans.add(bfs.poll().val);
                }
                return ans;
            }

            for (int i = bfs.size() - 1; i >= 0; i--) {
                curNode = bfs.poll();
                visited.add(curNode);

                parent = parentMap.get(curNode);
                if(parent != null && !visited.contains(parent)) bfs.add(parent);
                if(curNode.left != null && !visited.contains(curNode.left)) bfs.add(curNode.left);
                if(curNode.right != null && !visited.contains(curNode.right)) bfs.add(curNode.right);
            }

            curDist++;
        }

        return ans;
    }
}
