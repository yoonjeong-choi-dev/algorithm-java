package programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/43164
public class Prob15TravelPath {
    private static final String startCity = "ICN";

    int numTickets;
    private boolean[][] graph;

    public String[] solution(String[][] tickets) {

        // 항공권을 알파벳 순서로 정렬
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] s1, String[] s2) {
                if (s1[0].equals(s2[0])) {
                    return s1[1].compareTo(s2[1]);
                } else {
                    return s1[0].compareTo(s2[0]);
                }
            }
        });

        numTickets = tickets.length;

        // 티켓 사이의 그래프 생성
        graph = new boolean[numTickets][numTickets];
        for (int i = 0; i < numTickets; i++) {
            for (int j = i + 1; j < numTickets; j++) {
                String[] t1 = tickets[i];
                String[] t2 = tickets[j];

                if (t1[1].equals(t2[0])) {
                    graph[i][j] = true;
                }

                if (t2[1].equals(t1[0])) {
                    graph[j][i] = true;
                }
            }
        }

        List<Integer> history = new ArrayList<>(numTickets);
        for (int i = 0; i < numTickets; i++) {
            history.clear();

            // 티켓의 출발점이 ICN
            if (tickets[i][0].equals(startCity)) {
                boolean[] isVisited = new boolean[numTickets];
                isVisited[i] = true;
                history.add(i);

                // 시작 티켓에 대해서 dfs 탐색
                dfs(i, isVisited, history);

                // 모든 티켓을 사용한 경우에 조기 종료
                // 티켓들을 알파벳 순으로 정렬하였기 때문에 조기 종료 가능
                if (history.size() == numTickets) {
                    break;
                }
            }
        }


        String[] answer = new String[numTickets + 1];
        for (int i = 0; i < numTickets; i++) {
            answer[i] = tickets[history.get(i)][0];
        }
        answer[numTickets] = tickets[history.get(numTickets - 1)][1];

        return answer;
    }

    private void dfs(int curIndex, boolean[] isVisited, List<Integer> history) {
        // 모든 티켓을 사용한 경우 완료
        if (history.size() == numTickets) {
            return;
        }

        // 현재 티켓에서 갈 수 있는 티켓으로 이동
        for (int i = 0; i < numTickets; i++) {
            if (graph[curIndex][i] && !isVisited[i]) {
                history.add(i);
                isVisited[i] = true;
                dfs(i, isVisited, history);

                // 모든 티켓 사용이 완료된 경우 조기 종료
                if (history.size() == numTickets) {
                    return;
                }

                // 해당 경로가 틀린 경우 다음 경로 탐색
                isVisited[i] = false;
                history.remove(history.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Prob15TravelPath sol = new Prob15TravelPath();

        String[][][] tickets = {
                {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}},
                {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}
        };

        String[][] ans = {
                {"ICN", "JFK", "HND", "IAD"},
                {"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"}
        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            String[] mySol = sol.solution(tickets[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
