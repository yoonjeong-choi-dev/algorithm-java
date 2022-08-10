package from701to800;

// https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
public class Prob708InsertIntoSortedCircularLinkedList {
    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);

        if (head == null) {
            node.next = node;
            return node;
        }

        Node prev = head, cur = head.next;
        boolean isInsert = false;
        do {
            if (prev.val <= insertVal && insertVal <= cur.val) {
                // Case 1 : prev < insert < cur
                isInsert = true;
            } else if (prev.val > cur.val) {
                // Case 2 : cur is the minimum node
                // => 최대값보다 크거나, 최소값보다 작은 경우
                if (prev.val <= insertVal || insertVal <= cur.val) {
                    isInsert = true;
                }
            }

            if (isInsert) {
                prev.next = node;
                node.next = cur;
                return head;
            }

            prev = cur;
            cur = cur.next;

        } while (cur != head);


        // cur == head && prev == tail :
        // all nodes are increasing && insertVal is maximum or minimum
        prev.next = node;
        node.next = cur;
        return head;
    }
}
