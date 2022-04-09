package from1to100;

// https://leetcode.com/problems/merge-two-sorted-lists/
public class Prob21MergeSortedList {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Caution : list1 <- list2 형태로 구현하려하면, list1이 계속 업데이트되면서 무한루프 빠짐
        ListNode resultHead = new ListNode(0);
        ListNode curNode = resultHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curNode.next = list1;
                list1 = list1.next;
            } else {
                curNode.next = list2;
                list2 = list2.next;
            }
            curNode = curNode.next;
        }

        // 나머지 연결 리스트 연결
        curNode.next = list1 == null ? list2 : list1;

        return resultHead.next;
    }
}
