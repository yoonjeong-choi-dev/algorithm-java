package from201to300;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/palindrome-linked-list/
public class Prob234PalindromeLinkedList {
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

    public boolean isPalindrome(ListNode head) {
        return solutionWithList(head);
    }

    private boolean solutionWithList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) return false;
            left++;
            right--;
        }


        return true;
    }

    private boolean solutionWithConstantMemory(ListNode head) {
        // Assumption : size >=2
        if (head == null || head.next == null) return true;

        // Step 1 : 리스트를 두개로 나누기 => (n,n) or (n+1,n)
        ListNode first = head, second = head;
        while (second.next != null && second.next.next != null) {
            first = first.next;
            second = second.next.next;
        }
        second = first.next;
        first.next = null;
        first = head;

        // Step 2 : 두번째 리스트 반전
        // prev(null) -> node1(cur) -> node2 -> node3 -> node4
        // null <- node1(next prev), node2(next cur) -> node3 -> node4
        // null <- node1 <- node2(next prev), node3(next cur) -> node4
        ListNode prev = null, cur = second, next;
        while (cur != null) {
            // 다음 노드 임시 저장 : 현재 노드의 next 를 prev 노드에 연결하기 위해
            next = cur.next;

            // prev->cur 을 prev<-cur 로 변경
            cur.next = prev;

            // prev 및 cur 노드 변경
            prev = cur;
            cur = next;
        }
        second = prev;

        // compare first and second
        while (second != null) {
            if (first.val != second.val) return false;
            first = first.next;
            second = second.next;
        }

        return true;
    }
}
