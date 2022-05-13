package from1601to1700;

// https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/
public class Prob1634AddTwoPolynomialsRepresentedAsLinkedLists {
    class PolyNode {
        int coefficient, power;
        PolyNode next = null;

        PolyNode() {
        }

        PolyNode(int x, int y) {
            this.coefficient = x;
            this.power = y;
        }

        PolyNode(int x, int y, PolyNode next) {
            this.coefficient = x;
            this.power = y;
            this.next = next;
        }
    }

    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode ans = new PolyNode(-1, -1);
        PolyNode cur = ans;

        // PolyNode.power > PolyNode.next.power
        while (poly1 != null || poly2 != null) {
            if (poly1 == null) {
                cur.next = poly2;
                break;
            } else if (poly2 == null) {
                cur.next = poly1;
                break;
            }

            if(poly1.power == poly2.power && poly1.coefficient + poly2.coefficient == 0) {
                poly1 = poly1.next;
                poly2 = poly2.next;
                continue;
            }

            PolyNode next = new PolyNode();
            if (poly1.power == poly2.power) {
                next.power = poly1.power;
                next.coefficient = poly1.coefficient + poly2.coefficient;

                poly1 = poly1.next;
                poly2 = poly2.next;
            } else if (poly1.power > poly2.power) {
                next.power = poly1.power;
                next.coefficient = poly1.coefficient;

                poly1 = poly1.next;
            } else {
                next.power = poly2.power;
                next.coefficient = poly2.coefficient;

                poly2 = poly2.next;
            }

            cur.next = next;
            cur = next;
        }

        return ans.next;
    }
}
