package from501to600;

// https://leetcode.com/problems/inorder-successor-in-bst-ii/
public class Prob510InorderSuccessorInBST2 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    // inorder : left -> root -> right
    public Node inorderSuccessor(Node node) {
        // Case 1 : Right subtree exists
        // 해당 경우가 아닌 경우에는 부모 노드를 검색해야 함
        if(node.right != null) {
            Node ans = node.right;
            while(ans.left != null) ans = ans.left;
            return ans;
        }

        // 노드가 부모 노드의 왼쪽 자식 노드인 경우까지 위로 올라간다
        while(node.parent != null && node.parent.left != node) node = node.parent;
        return node.parent;
    }
}
