package from201to300;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class Prob235LowestCommonAncestorInBST {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode curNode = root;
        while (curNode != null) {
            if (curNode.val == p.val || curNode.val == q.val) {
                // 두 노드가 부모-자식 관계인 경우
                return curNode;
            } else if (curNode.val > p.val && curNode.val > q.val) {
                // 두 노드가 왼쪽 서브트리에 존재
                curNode = curNode.left;
            } else if (curNode.val < p.val && curNode.val < q.val) {
                // 두 노드가 오른쪽 서브트리에 존재
                curNode = curNode.right;
            } else {
                // 두 노드가 양쪽 서브트리로 갈라지는 경우 해당 노드가 조상 노드
                return curNode;
            }
        }

        return null;
    }
}
