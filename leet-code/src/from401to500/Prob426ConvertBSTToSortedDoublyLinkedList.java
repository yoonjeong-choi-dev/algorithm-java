package from401to500;

// https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
public class Prob426ConvertBSTToSortedDoublyLinkedList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        return preorderRecursive(root);
    }

    private Node preorderRecursive(Node node) {
        Node left = node.left;
        Node right = node.right;

        node.left = node;
        node.right = node;

        Node temp;
        if (left != null) {
            temp = preorderRecursive(left);

            // temp 연결 리스트의 마지막에 현재 노드 삽입
            node.left = temp.left;
            node.right = temp;

            temp.left.right = node;
            temp.left = node;

            node = temp;
        }

        if (right != null) {
            temp = preorderRecursive(right);

            // 현재 연결 리스트 뒤에 temp 연결 리스트 삽입
            Node lastNode = temp.left;
            temp.left.right = node;
            temp.left = node.left;

            node.left.right = temp;
            node.left = lastNode;
        }

        return node;
    }

    public void test() {
        Node root = new Node(4, new Node(1), new Node(5));
        root.left = new Node(2, new Node(1), new Node(3));
        root.right = new Node(5);

        Node ret = treeToDoublyList(root);

        printCircular(ret);
    }

    private void print(Node node) {
        if (node == null) {
            System.out.println("NULL");
            return;
        }

        System.out.printf("[%d] left : %s, right : %s\n", node.val,
                node.left == null ? "NULL" : String.valueOf(node.left.val),
                node.right == null ? "NULL" : String.valueOf(node.right.val));
    }

    private void printCircular(Node node) {
        System.out.println("Circular Node Info");
        if (node == null) {
            System.out.println("NULL");
            return;
        }

        int val = node.val;
        do {
            print(node);
            node = node.right;
        } while(node.val != val);
    }

    public static void main(String[] args) {
        Prob426ConvertBSTToSortedDoublyLinkedList sol = new Prob426ConvertBSTToSortedDoublyLinkedList();
        sol.test();
    }
}
