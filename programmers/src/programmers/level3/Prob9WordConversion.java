package programmers.level3;

import java.util.ArrayDeque;
import java.util.Queue;

public class Prob9WordConversion {
    public int solution(String begin, String target, String[] words) {
        // 그래프 생성을 위해 맨 앞에 begin 추가
        int start = 0;
        int numWords = words.length + 1;
        String[] origin = words;
        words = new String[numWords];
        words[0] = begin;
        System.arraycopy(origin, 0, words, 1, numWords - 1);

        // 그래프 및 시작/목적지 인덱스 설정
        int end = -1;
        boolean[][] graph = new boolean[numWords][numWords];
        for (int i = 0; i < numWords; i++) {
            // 목적지 인덱스 검사
            if (target.equals(words[i])) end = i;

            // 변환 가능한 경우 두 단어 사이 연결
            for (int j = i + 1; j < numWords; j++) {
                if (canConvert(words[i], words[j])) {
                    graph[i][j] = true;
                    graph[j][i] = true;
                }
            }
        }

        // 변환 단어 리스트(words)에 target이 없는 경우 불가능
        if(end == -1) return 0;

        // bfs 알고리즘을 이용하여 start -> end 최단 거리 검색
        int[] dist = new int[numWords];
        for (int i = 0; i < numWords; i++) {
            dist[i] = -1;
        }
        Queue<Integer> bfs = new ArrayDeque<>(numWords);
        bfs.add(start);
        dist[start] = 0;

        int cur;
        while (!bfs.isEmpty()) {
            cur = bfs.poll();

            // 현재 정점에서 방문 가능한 정점 탐색
            for (int i = 0; i < numWords; i++) {
                if (graph[cur][i] && dist[i] == -1) {
                    if (i == end) return dist[cur] + 1;

                    dist[i] = dist[cur] + 1;
                    bfs.add(i);
                }
            }
        }


        return dist[end];
    }

    // 문자 하나만 바꿔서 str1 -> str2 가 되는지 여부 확인
    private boolean canConvert(String str1, String str2) {
        int numDiff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                numDiff++;

                if (numDiff > 1) return false;
            }
        }

        return numDiff == 1;
    }

    public static void main(String[] args) {
        Prob9WordConversion sol = new Prob9WordConversion();

        String[] begins = {"hit", "hit"};
        String[] targets = {"cog", "cog"};
        String[][] words = {
                {"hot", "dot", "dog", "lot", "log", "cog"},
                {"hot", "dot", "dog", "lot", "log"}
        };

        int[] ans = {4, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(begins[i], targets[i], words[i]);
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
