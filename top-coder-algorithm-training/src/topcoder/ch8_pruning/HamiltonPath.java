package topcoder.ch8_pruning;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


// TODO : 다시 한번 풀어보기 (p320)
public class HamiltonPath {
    private static final int mod = 1000000007;

    public int countPaths(String[] roads) {
        int numCity = roads.length;

        // 한 도시에서 지나가야 하는 도로가 3개 이상이면 불가능
        for (String road : roads) {
            int deg = 0;
            for (int j = 0; j < numCity; j++) {
                if (road.charAt(j) == 'Y') {
                    if (deg == 2) return 0;
                    else deg++;
                }
            }
        }

        // 연결된 도시들의 개수 저장
        // 여기서 연결된 도시는 반드시 지나야 하는 도로에 의해서 연결된 도시를 의미
        List<Integer> connected = new ArrayList<>(numCity);

        // 연결이 되지 않은 도시들의 개수
        int freeCities = 0;

        // dfs 탐색을 통해 연결된 도시들의 집합 계산
        // dfs 탐색 도중 이미 방문한 도시를 발견한 경우 cycle 존재 => 경로 존재 X
        boolean[] isVisited = new boolean[numCity];
        Stack<Integer> dfs = new Stack<>();
        for (int i = 0; i < numCity; i++) {
            if (isVisited[i]) continue;

            int cityNum = 0;

            // 현재 도시 넣는다
            dfs.push(i);

            while (!dfs.isEmpty()) {
                int curCity = dfs.pop();

                // 이미 방문한 노드였다면 cycle 존재
                // 경로가 존재하는 그래프의 경우, 각 component 는 일자 형태의 트리 형태야 함
                // dfs 탐색 시, 스택에 쌓일 일이 없음
                if (isVisited[curCity]) return 0;

                isVisited[curCity] = true;
                cityNum++;

                for (int j = 0; j < numCity; j++) {
                    if (roads[curCity].charAt(j) == 'Y' && !isVisited[j]) {
                        dfs.push(j);
                    }
                }
            }

            if (cityNum != 1) connected.add(cityNum);
            else freeCities++;
        }

        // 지나야할 도로가 있는 도시들의 집합 개수
        int numComponent = connected.size();

        // 전체 경우의 수 : factorial(freeCities + numComponent) * pow(2, numComponent)
        // factorial(freeCities + numComponent) = 도시들의 집합과 freeCity들에 대한 순열
        // pow(2, numComponent) = 각 도시 집합에 대해서 경로는 2가지
        int ans = 1;
        for (int i = 1; i <= freeCities + numComponent; i++) {
            ans = (int) ((long) ans * i) % mod;
        }
        for (int i = 0; i < numComponent; i++) {
            ans = (int) ((long) ans * 2) % mod;
        }

        return ans;
    }

    public static void main(String[] args) {
        HamiltonPath sol = new HamiltonPath();

        String[][] roads = {
                {"NYN", "YNN", "NNN"},
                {"NYYY", "YNNN", "YNNN", "YNNN"},
                {"NYY", "YNY", "YYN"},
                {"NNNNNY", "NNNNYN", "NNNNYN", "NNNNNN", "NYYNNN", "YNNNNN"}
        };

        int[] ans = {4, 0, 0, 24};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.countPaths(roads[i]);
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
