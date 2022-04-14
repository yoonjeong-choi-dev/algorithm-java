package from1201to1300;

// https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
public class Prob1290ConvertBinaryNumberInLinkedList {
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

    public int getDecimalValue(ListNode head) {
        //return mySimpleSolution(head);
        return improvedSolution(head);
    }

    private int mySimpleSolution(ListNode head) {
        if (head.next == null) return head.val;

        int curPower = 1;
        ListNode curNode = head.next;
        while (curNode != null) {
            curPower *= 2;
            curNode = curNode.next;
        }

        int ans = 0;
        curNode = head;
        while (curNode != null) {
            ans += curPower * curNode.val;

            curNode = curNode.next;
            curPower /= 2;
        }
        return ans;
    }

    private int improvedSolution(ListNode head) {
        int ans = head.val;
        while (head.next != null) {
            // MSB 부터 저장하였으므로, 현재 값을 1비트 쉬프트하고 더하기
            ans = (ans << 1) | head.next.val;

            head = head.next;
        }

        return ans;
    }
}
