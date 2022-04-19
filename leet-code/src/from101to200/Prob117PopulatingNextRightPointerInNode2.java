package from101to200;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
public class Prob117PopulatingNextRightPointerInNode2 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;

        // 가장 왼쪽 노드가 leaf 노드일 때까지 탐색
        Node curNode = root, prevNode, leftNode;
        while (curNode != null) {

            // 지금 레벨의 노드들의 next 작업이 완료되었다고 가정하고, 아래 레벨의 next 작업
            // leftNode : 자식 노드들을 탐색하면서 처음에 발견한 자식 노드를 저장하여 다음 레벨 작업 시 사용
            leftNode = null;
            prevNode = null;

            while (curNode != null) {
                // 자식 노드가 있는 경우 (왼오, 왼, 오)에 대해서만 next 작업
                if (curNode.left != null && curNode.right != null) {
                    if (prevNode != null) prevNode.next = curNode.left;
                    if (leftNode == null) leftNode = curNode.left;

                    curNode.left.next = curNode.right;
                    prevNode = curNode.right;
                } else if (curNode.left != null) {
                    if (prevNode != null) prevNode.next = curNode.left;
                    if (leftNode == null) leftNode = curNode.left;

                    prevNode = curNode.left;
                } else if (curNode.right != null) {
                    if (prevNode != null) prevNode.next = curNode.right;
                    if (leftNode == null) leftNode = curNode.right;

                    prevNode = curNode.right;
                }

                curNode = curNode.next;
            }

            curNode = leftNode;
        }

        return root;
    }

    private void printNode(Node node) {
        if(node ==null) {
            System.out.println("NULL");
            return;
        }

        System.out.printf("[%d] left : %s, right : %s, next : %s\n", node.val,
                node.left == null ? "NULL" : String.valueOf(node.left.val),
                node.right == null ? "NULL" : String.valueOf(node.right.val),
                node.next == null ? "NULL" : String.valueOf(node.next.val));
    }

    public void test() {
        Node root = new Node(3);
        Node left = new Node(9);

        Node right1 = new Node(15);
        Node right2 = new Node(7);
        Node right = new Node(20);
        right.left = right1;
        right.right = right2;

        root.left = left;
        root.right = right;
        connect(root);
    }

    public static void main(String[] args) {
        Prob117PopulatingNextRightPointerInNode2 sol = new Prob117PopulatingNextRightPointerInNode2();
        sol.test();
    }

}
