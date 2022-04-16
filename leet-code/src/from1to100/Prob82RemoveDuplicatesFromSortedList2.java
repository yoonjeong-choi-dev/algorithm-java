package from1to100;

// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
public class Prob82RemoveDuplicatesFromSortedList2 {
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
        // 연결 리스트의 헤드 노드를 삭제하는 경우를 고려하여 더미 노드를 만들어서 탐색
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        ListNode curNode = prev.next;

        int curVal;
        while(curNode != null) {
            if(curNode.next != null && curNode.val == curNode.next.val) {
                // 중복된 게 있는 경우 : 중복된 노드들을 모두 넘어간 후 이전 노드에 새로운 노드 연결
                curVal = curNode.val;
                while(curNode != null && curNode.val == curVal) curNode = curNode.next;

                // 이전 노드에 현재 중복된 노드 다음 노드를 연결
                // 이후 연결된 현재 노드 삭제 여부는 다음 반복문에서 다시 찾음
                prev.next = curNode;
            } else {
                // 현재 노드가 삭제 노드가 아닌 경우
                prev = curNode;
                curNode = curNode.next;
            }

        }

        return dummy.next;
    }

}
