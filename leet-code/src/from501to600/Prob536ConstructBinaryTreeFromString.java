package from501to600;

// https://leetcode.com/problems/construct-binary-tree-from-string/
public class Prob536ConstructBinaryTreeFromString {
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

    private String s;

    public TreeNode str2tree(String s) {
        this.s = s;
        return getTree(0, s.length() - 1);
    }

    private TreeNode getTree(int start, int end) {
        if (start > end) return null;

        while(s.charAt(start) == '(') {
            start++;
            end--;
        }

        // [start, idx] : node value
        int idx = start;
        while (idx <= end && s.charAt(idx) != '(') idx++;

        int value = Integer.parseInt(s.substring(start, idx));

        // Leaf Node
        if (idx > end) return new TreeNode(Integer.parseInt(s.substring(start, idx)));

        // find the end index for left tree
        // Condition : You always start to construct the left child node of the parent first if it exists.
        // 왼쪽 괄호 개수
        int leftStart = idx;
        idx = leftStart+1;
        int leftNum = 1;
        while (idx <= end && leftNum != 0) {
            if (s.charAt(idx) == '(') leftNum++;
            else if (s.charAt(idx) == ')') {
                leftNum--;
                if (leftNum == 0) break;
            }
            idx++;
        }

        // [leftIdx:idx-1] : left tree
        TreeNode left = getTree(leftStart, idx);
        TreeNode right = getTree(idx + 1, end);
        return new TreeNode(value, left, right);
    }

    public static void main(String[] args) {
        Prob536ConstructBinaryTreeFromString sol = new Prob536ConstructBinaryTreeFromString();
        sol.str2tree("4(2(3)(1))(6(5))");
    }
}
