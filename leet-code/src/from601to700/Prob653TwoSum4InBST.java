package from601to700;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
// Ref : Problem 167, 94
public class Prob653TwoSum4InBST {
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

    public boolean findTarget(TreeNode root, int k) {
        // Problem 167 차용 : 정렬된 배열에서의 두개 합
        // 이진 탐색 트리를 inorder 탐색을 하면 오름차순

        // inorder 탐색을 통해 오름차순으로 정렬
        List<Integer> list = new LinkedList<>();
        inorderTraversal(root, list);

        // Problem 167 차용
        int left = 0, right = list.size() - 1;
        int sum;

        while (left < right) {
            sum = list.get(left) + list.get(right);
            if (sum == k) return true;
            else if (sum < k) {
                // 합이 target 보다 작은 경우에는 최소값에 해당하는 왼쪽 인덱스 변경
                left++;
            } else {
                // 합이 target 보다 큰 경우에는 최대값에 해당하는 오른쪽 인덱스 변경
                right--;
            }
        }

        // 탐색이 끝났을 때도 찾지 못한 경우
        return false;
    }


    private void inorderTraversal(TreeNode node, List<Integer> ret) {
        if (node == null) return;

        inorderTraversal(node.left, ret);
        ret.add(node.val);
        inorderTraversal(node.right, ret);
    }
}
