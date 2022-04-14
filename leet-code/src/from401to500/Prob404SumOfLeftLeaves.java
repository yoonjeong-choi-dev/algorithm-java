package from401to500;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/sum-of-left-leaves/
public class Prob404SumOfLeftLeaves {
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

    public int sumOfLeftLeaves(TreeNode root) {
        return myBfsSolution(root);
    }

    // Linear Complexity
    // Runtime: 1 ms, faster than 27.03% of Java
    // Memory Usage: 42.9 MB, less than 5.46% of Java
    private int myBfsSolution(TreeNode root) {
        int ans = 0;
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.offer(root);

        TreeNode curNode;
        while (!bfs.isEmpty()) {
            // 현재 노드의 왼쪽 자식 노드가 리프 노드인지 체크
            curNode = bfs.poll();

            if (curNode.left != null) {
                if (curNode.left.left == null && curNode.left.right == null) {
                    // 왼쪽 자식 노드가 리프 노드이면 더함
                    ans += curNode.left.val;
                } else {
                    // 왼쪽 자식 노드가 리프 노드가 아니면 큐에 저장
                    bfs.offer(curNode.left);
                }
            }

            if (curNode.right != null) {
                bfs.offer(curNode.right);
            }
        }

        return ans;
    }

    // TODO : Morris Inorder Tree Traversal(https://www.youtube.com/watch?v=wGXB9OWhPTg)
    // 왼쪽 서브트리의 가장 오른쪽 노드에 현재 루트 노드를 링크로 거는 방식으로 inorder traversal 하는 방식
    // 여기서 링크가 걸리는 노드는 inorder 방식에서 root에 해당
    // left -> root -> right 방식에서 left의 가장 오른쪽에 root 링크를 걸어 inorder traversal 를 구현하는 것
    // With O(1) memory
    private int morisTraversal(TreeNode root) {
        int ans = 0;

        TreeNode curNode = root;
        TreeNode prevInorderNode;

        // curNode 노드를 루트 노드로 하는 트리 고려
        while (curNode != null) {
            if (curNode.left == null) {
                // 왼쪽 서브 트리가 없는 경우 : 오른쪽 서브 트리에 대해서만 탐색하면 된다
                // left(done) -> root -> right 에서 root 방문 후 right 이동

                // VISIT(curNode)
                curNode = curNode.right;
                continue;
            }

            // 왼쪽 서브가 존재하는 경우
            if (curNode.left.left == null && curNode.left.right == null) {
                // 왼쪽 노드가 리프 노드인 경우 => 합
                ans += curNode.left.val;
            }

            // 왼쪽 노드가 리프 노드가 아닌 경우 가장 오른쪽에 있는 노드 찾기 i.e 왼쪽 서브 트리의 in-order traversal 의 마지막 노드 찾기
            // !prevInorderNode.equals(curNode) 조건 이유 :
            // 가장 오른쪽 노드의 오른쪽 자식에 현재 노드를 링크를 걸기 때문(prevInorderNode.right = curNode)
            prevInorderNode = curNode.left;
            while (prevInorderNode.right != null && !prevInorderNode.right.equals(curNode)) {
                prevInorderNode = prevInorderNode.right;
            }

            if (prevInorderNode.right == null) {
                // 가장 오른쪽에 있는 노드인 경우 : 현재 노드가 리프 노드인 경우도 여기에 포함
                // 가장 오른쪽에 있는 노드의 오른쪽 서브 트리에 현재 탐색중인 트리 링크를 걸고, 왼쪽 서브 트리 탐색
                // left -> root -> right 에서 left 에 해당
                prevInorderNode.right = curNode;
                curNode = curNode.left;
            } else {
                // 가장 오른쪽에 있는 노드가 현재 서브 트리인 경우
                // i.e 왼쪽 서브 트리의 오른쪽 노드가 이전 서브트리로 링크가 걸린 경우
                // 링크가 걸린 이전 서브트리(root) 방문 후, 링크 해제 및 이전 서브트리의 오른쪽 서브트리로 이동
                // left -> root -> right 에서 root 방문 및 right 이동

                // VISIT(curNode)
                prevInorderNode.right = null;
                curNode = curNode.right;
            }
        }

        return ans;
    }
}
