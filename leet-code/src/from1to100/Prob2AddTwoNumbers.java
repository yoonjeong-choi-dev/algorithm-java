package from1to100;

// https://leetcode.com/problems/add-two-numbers/
public class Prob2AddTwoNumbers {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = l1;
        ListNode prev = null;
        int carry = 0;

        while (l1 != null && l2 != null) {
            l1.val += l2.val + carry;
            if (l1.val > 9) {
                l1.val -= 10;
                carry = 1;
            } else {
                carry = 0;
            }

            prev = l1;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 == null) {
            prev.next = l2;
            l1 = l2;
        }

        while (l1 != null) {
            l1.val += carry;
            if (l1.val > 9) {
                l1.val -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            prev = l1;
            l1 = l1.next;
        }

        if (carry == 1) {
            prev.next = new ListNode(1);
        }


        return ans;
    }
}
