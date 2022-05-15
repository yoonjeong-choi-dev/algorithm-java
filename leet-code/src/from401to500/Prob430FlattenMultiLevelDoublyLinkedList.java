package from401to500;

// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
public class Prob430FlattenMultiLevelDoublyLinkedList {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    public Node flatten(Node head) {
        if (head == null) return null;

        recursive(head);

        return head;
    }

    // flatten 후 마지막 노드 반환
    private Node recursive(Node node) {
        if (node.next == null && node.child == null) return node;

        Node cur = node;

        Node child, childLast, next;
        while (true) {
            next = cur.next;

            // 현재 노드가 하위 노드를 가지는 경우 하위 노드(레벨) 처리
            if (cur.child != null) {
                child = cur.child;
                childLast = recursive(cur.child);

                cur.child = null;

                cur.next = child;
                child.prev = cur;

                childLast.next = next;
                if (next != null) {
                    next.prev = childLast;
                } else {
                    cur = childLast;
                }
            }

            if(next == null) break;
            cur = next;
        }

        return cur;
    }
}
