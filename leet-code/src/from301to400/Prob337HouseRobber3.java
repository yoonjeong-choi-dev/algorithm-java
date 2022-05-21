package from301to400;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/house-robber-iii/
public class Prob337HouseRobber3 {
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

    private Map<TreeNode, Integer> ansWhenParentRobbed;
    private Map<TreeNode, Integer> ansWhenParentNotRobbed;

    public int rob(TreeNode root) {
        // 부모 노드를 훔쳤을 때 현재 노드에 대한 부분 문제 정답
        ansWhenParentRobbed = new HashMap<>();

        // 부모 노드를 훔치지 않았을 때 현재 노드에 대한 부분 문제 정답
        ansWhenParentNotRobbed = new HashMap<>();

        return recursive(root, false);
    }

    private int recursive(TreeNode node, boolean isParentRobbed) {
        if (node == null) return 0;

        if (isParentRobbed) {
            if (ansWhenParentRobbed.containsKey(node)) {
                return ansWhenParentRobbed.get(node);
            }

            // 현재 노드는 훔치는 것이 불가능
            int ans = recursive(node.left, false) + recursive(node.right, false);
            ansWhenParentRobbed.put(node, ans);
            return ans;
        } else {
            if (ansWhenParentNotRobbed.containsKey(node)) {
                return ansWhenParentNotRobbed.get(node);
            }

            // 현재 노드 훔치는 것 가능
            int ans = node.val + recursive(node.left, true) + recursive(node.right, true);

            // 현재 노드를 안춤히는 경우
            ans = Math.max(ans, recursive(node.left, false) + recursive(node.right, false));

            ansWhenParentNotRobbed.put(node, ans);
            return ans;
        }
    }
}
