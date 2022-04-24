package from701to800;

// https://leetcode.com/problems/design-linked-list/
public class Prob707DesignLinkedList {
    class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    class MyLinkedList {

        private ListNode head;
        private ListNode tail;
        private int size;

        public MyLinkedList() {
            head = null;
            tail = null;
            size = 0;
        }

        public int get(int index) {
            if (index >= size) return -1;

            ListNode temp = head;
            while (index != 0) {
                temp = temp.next;
                index--;
            }
            return temp.val;
        }

        public void addAtHead(int val) {
            ListNode node = new ListNode(val);

            if (size == 0) {
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
            size++;
        }

        public void addAtTail(int val) {
            ListNode node = new ListNode(val);
            if (size == 0) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index == 0) addAtHead(val);
            else if (index == size) addAtTail(val);
            else if (index < size) {
                ListNode cur = head;
                while (index != 0) {
                    cur = cur.next;
                    index--;
                }

                ListNode node = new ListNode(val);
                node.prev = cur.prev;
                cur.prev.next = node;
                node.next = cur;
                cur.prev = node;

                size++;
            }
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size || size == 0) return;

            if (size == 1) {
                head = tail = null;
            } else if (index == 0) {
                head = head.next;
                head.prev = null;
            } else if (index == size - 1) {
                tail = tail.prev;
            } else {
                ListNode cur = head;
                while (index != 0) {
                    cur = cur.next;
                    index--;
                }

                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
            }

            size--;
        }
    }
}
