package from901to1000;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/binary-tree-cameras/
public class Prob968BinaryTreeCameras {
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


    private int ans;
    private Set<TreeNode> isCovered;

    public int minCameraCover(TreeNode root) {
        ans = 0;
        isCovered = new HashSet<>();

        // 자식 노드들이 널인 경우도 있으므로, 널 저장해 놓음
        isCovered.add(null);
        postOrder(root, null);
        return ans;
    }

    private void postOrder(TreeNode node, TreeNode parent) {
        if (node == null) return;

        postOrder(node.left, node);
        postOrder(node.right, node);

        // 지금 노드에 설치해야 하는 경우
        // 1. 부모 노드가 없는 경우, 자식 노드들이 이미 커버가 되어도 현재 노드에 카메라를 설치해야함
        // 2. 부모 노드가 있는 경우에는, 자식 노드들 중 커버가 안된 노드가 있을 때만 커버
        // => 자식 노드들이 커버가 된 경우에는 부모 노드에 설치하는게 이득
        if ((parent == null && !isCovered.contains(node)) || !isCovered.contains(node.left) || !isCovered.contains(node.right)) {
            isCovered.add(node);
            isCovered.add(node.left);
            isCovered.add(node.right);
            isCovered.add(parent);

            ans++;
        }
    }
}
