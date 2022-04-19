package from1to100;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/recover-binary-search-tree/
public class Prob99RecoverBST {
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

    private List<TreeNode> path;

    public void recoverTree(TreeNode root) {


    }

    private void mySolutionWithPathArray(TreeNode root) {
        path = new ArrayList<>();
        inorder(root);

        //selectionSort();

        // 리스트를 순회하면서 잘못 위치한 두 개의 노드 찾기
        // (i,i+1) 및 (j, j+1) ,i<j 에서 순서가 어긋남
        // => i 와 j+1 노드 위치 변경
        TreeNode node1 = null, node2 = null;
        for (int i = 1; i < path.size(); i++) {
            if (path.get(i - 1).val <= path.get(i).val) continue;

            // (j, j+1) 에 대한 저장
            node2 = path.get(i);

            if(node1 == null) {
                // (i,i+1) 에 대한 저장
                node1 = path.get(i-1);
            } else {
                break;
            }
        }

        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        path.add(node);
        inorder(node.right);
    }

    private void selectionSort() {
        // inorder => 2개를 제외하고는 정렬되어 있는 상태
        // 거의 다 정렬되어 있기때문에 삽입 정렬을 이용하면 빠르게 정렬 가능
        int j, temp, val;
        for (int i = 1; i < path.size(); i++) {
            // arr[0:i-1] 까지는 정렬되어 있는 상황에서 [0:i-1] 사이에 i 삽입 위치를 찾음
            val = path.get(i).val;

            j = i;
            while (j > 0) {
                temp = path.get(j - 1).val;
                if (temp <= val) break;

                path.get(j).val = temp;
                j--;
            }
            path.get(j).val = val;
        }
    }

    private void print() {
        for (TreeNode node : path) {
            System.out.printf("%d ", node.val);
        }
        System.out.println();
    }

    public void test() {
        TreeNode root = new TreeNode(1, new TreeNode(3, null, new TreeNode(2)), null);
        recoverTree(root);
    }

    public static void main(String[] args) {
        Prob99RecoverBST sol = new Prob99RecoverBST();
        sol.test();
    }

}
