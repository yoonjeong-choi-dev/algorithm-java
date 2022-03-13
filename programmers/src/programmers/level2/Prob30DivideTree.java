package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/86971
public class Prob30DivideTree {
    public int solution(int n, int[][] wires) {
        boolean[][] graphs = new boolean[n][n];
        for (int[] wire : wires) {
            graphs[wire[0] - 1][wire[1] - 1] = true;
            graphs[wire[1] - 1][wire[0] - 1] = true;
        }

        // 0번 노드를 root로 하는 트리에서 각 정점의 하위 노드들 개수
        int[] numChild = new int[n];

        dfs(graphs, numChild, 0);

        // numChild 요소 중 n/2와 제일 가까운 수를 찾는다
        int answer = n;
        for (int child : numChild) {
            if (answer > Math.abs(2 * child - n)) {
                answer = Math.abs(2 * child - n);
            }
        }
        return answer;
    }

    private int dfs(boolean[][] graphs, int[] numChild, int idx) {
        if (numChild[idx] != 0) return numChild[idx];

        // 현재 정점 방문 : 자식이 없는 경우에는 1
        numChild[idx] = 1;

        // 연결된 노드들에 대해서 dfs
        for (int i = 0; i < graphs[idx].length; i++) {
            // dfs 이므로, 방문하지 않은 노드들이 현재 노드에 대한 자식 노드
            if (graphs[idx][i] && numChild[i] == 0) {
                numChild[idx] += dfs(graphs, numChild, i);
            }
        }

        return numChild[idx];
    }

    public static void main(String[] args) {
        Prob30DivideTree sol = new Prob30DivideTree();

        int[] n = {9, 4, 7};
        int[][][] wires = {
                {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}},
                {{1, 2}, {2, 3}, {3, 4}},
                {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}
        };

        int[] ans = {3, 0, 1};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(n[i], wires[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
