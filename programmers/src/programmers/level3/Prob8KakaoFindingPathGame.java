package programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/42892
public class Prob8KakaoFindingPathGame {

    // 이진 트리 클래스
    private static class Node {
        private final int index;

        // 문제에 나와있는 특별한 규칙에 의해서 x 좌표만 가지고 이진트리 생성 가능
        // 조건 5 : 임의의 노드 V의 왼쪽 서브 트리(left subtree)에 있는 모든 노드의 x값은 V의 x 값보다 작다
        // => x 기준으로 자식 노드 선택 가능
        private int x;

        // 이진트리 자식 노드
        Node left, right;

        public Node(int index, int x) {
            this.index = index;
            this.x = x;

            left = null;
            right = null;
        }

        public void add(int index, int x) {
            // 조건 2 : 모든 노드는 서로 다른 x값을 가진다
            if (x < this.x) {
                // 왼쪽 서브 트리
                if (left == null) {
                    // 왼쪽 노드가 없는 경우 생성하고 끝
                    left = new Node(index, x);
                } else {
                    // 왼쪽 노드가 있는 경우 재귀 호출
                    left.add(index, x);
                }

            } else {
                // 오른쪽 서브 트리
                if (right == null) {
                    // 오른쪽 노드가 없는 경우 생성하고 끝
                    right = new Node(index, x);
                } else {
                    // 오른쪽 노드가 있는 경우 재귀 호출
                    right.add(index, x);
                }
            }
        }

        // 현재 노드에서 전위 순회 : 현재 정점 -> 왼쪽 -> 오른쪽
        public void preorder(List<Integer> path) {
            path.add(index);
            if (left != null) left.preorder(path);
            if (right != null) right.preorder(path);
        }

        // 현재 노드에서 후위 순회 : 왼쪽 -> 오른쪽 -> 현재 정점
        public void postorder(List<Integer> path) {
            if (left != null) left.postorder(path);
            if (right != null) right.postorder(path);
            path.add(index);
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int numInfo = nodeinfo.length;

        // 트리 구성을 위해서 좌표 인덱스를 정렬
        Integer[] indices = new Integer[numInfo];
        for (int i = 0; i < numInfo; i++) {
            indices[i] = i;
        }

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer idx1, Integer idx2) {
                // y 좌표에 대한 내림차순
                if (nodeinfo[idx1][1] != nodeinfo[idx2][1]) {
                    return nodeinfo[idx2][1] - nodeinfo[idx1][1];
                } else {
                    // y 좌표가 같은 경우에는 x 좌표에 대한 오름차순
                    return nodeinfo[idx1][0] - nodeinfo[idx2][0];
                }
            }
        };

        Arrays.sort(indices, comp);

        // 트리 생성
        Node root = new Node(indices[0] + 1, nodeinfo[indices[0]][0]);
        for(int i=1;i<numInfo;i++){
            root.add(indices[i]+1, nodeinfo[indices[i]][0]);
        }

        // 순회
        List<Integer> preorder = new ArrayList<>(numInfo);
        List<Integer> postorder = new ArrayList<>(numInfo);
        root.preorder(preorder);
        root.postorder(postorder);


        int[][] answer = new int[2][numInfo];
        for(int i=0;i<numInfo;i++){
            answer[0][i] = preorder.get(i);
            answer[1][i] = postorder.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob8KakaoFindingPathGame sol = new Prob8KakaoFindingPathGame();

        int[][] nodeInfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] ans = {{7, 4, 6, 9, 1, 8, 5, 2, 3}, {9, 6, 5, 8, 1, 4, 3, 2, 7}};
        int[][] mySol = sol.solution(nodeInfo);

        if (check2dArr(ans, mySol)) {
            System.out.println("PASS");
        } else {
            print2dArr("Result :", mySol);
            print2dArr("Expected :", ans);
        }
    }

    private static boolean check2dArr(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr2.length) return false;

        for (int i = 0; i < arr1.length; i++) {
            if (!Arrays.equals(arr1[i], arr2[i])) return false;
        }

        return true;
    }

    private static void print2dArr(String title, int[][] arr) {
        System.out.println(title);
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
    }
}
