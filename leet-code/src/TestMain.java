import java.util.*;

public class TestMain {

    static class Null {
        char c;
    }

    public static void main(String[] args) {
        TestMain test = new TestMain();
        // 2147483647
        int a = 1000000007;
        int b = a*2;
        int c = a*3;
        System.out.println(b);
        System.out.println(c);

        System.out.println(1<<1);
        System.out.println(1<<2);


        int z = -10;
        System.out.println(z % 3);

        System.out.printf("%d, %d\n",Integer.MAX_VALUE, Integer.MIN_VALUE);
    }



    private int ans;
    private Set<TreeNode> covered;

    public int minCameraCover(TreeNode root) {
        ans = 0;
        covered = new HashSet<>();

        // 자식이 null 인 경우도 있으므로 null 을 추가해놓는다
        covered.add(null);

        postOrder(root, null);

        return ans;
    }

    private void postOrder(TreeNode node, TreeNode parent) {
        if (node == null) return;

        // post order : left -> right -> root
        postOrder(node.left, node);
        postOrder(node.right, node);

        // 현재 노드에 카메라를 설치해야 하는 상황
        // 루트 노드인 경우 : post order 이므로, 현재 노드가 커버되지 않은 경우 카메라 설치 필요
        // 루트 노드가 아닌 경우 : 자식 노드들 중에 커버되지 않은 노드가 있는 경우
        // => 전략 : 자식보다는 부모 노드 쪽에 설치
        if ((parent == null && !covered.contains(node)) || (!covered.contains(node.left) || !covered.contains(node.right))) {
            ans++;

            covered.add(parent);
            covered.add(node);
            covered.add(node.left);
            covered.add(node.right);
        }
    }


    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        int num1, num2;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 + num2);
                    break;
                case "-":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 - num2);
                    break;
                case "*":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 * num2);
                    break;
                case "/":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 / num2);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
            }
        }

        return stack.peek();
    }

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    private int gcd(int n, int m) {

        int temp;
        while (m != 0) {
            temp = n % m;
            n = m;
            m = temp;
        }

        return n;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

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

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
