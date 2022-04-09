package from1to100;

// https://leetcode.com/problems/remove-duplicates-from-sorted-list/
public class Prob83RemoveDuplicatesFromSortedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        int curValue;

        while (cur != null) {
            curValue = cur.val;

            // 이후 노드들 중 curValue 값을 가진 노드 삭제
            while(cur.next != null && cur.next.val == curValue) {
                cur.next = cur.next.next;
            }

            cur = cur.next;
        }


        return head;
    }
}
