package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/87946
public class Prob27FatigueLevel {

    private int[][] dungeons;

    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;

        boolean[] isVisited = new boolean[dungeons.length];

        int answer = 0;
        for (int i = 0; i < dungeons.length; i++) {
            answer = Math.max(answer, recursive(i, k, isVisited, 0));
        }

        return answer;
    }

    // 던전의 최대 개수는 8개 => 전체 탐색 가능(8!)
    private int recursive(int idx, int curFatigue, boolean[] isVisited, int numVisited) {

        // 현재 던전 입장 불가능한 경우 => 현재까지 방문한 던전 개수
        if (curFatigue < dungeons[idx][0]) {
            return numVisited;
        }

        // 현재 던전 방문
        isVisited[idx] = true;
        int ans = numVisited+1;

        // 입장 가능한 던전에 대해서 탐색
        for (int i = 0; i < dungeons.length; i++) {
            if (!isVisited[i]) {
                ans = Math.max(ans, recursive(i, curFatigue - dungeons[idx][1], isVisited, numVisited+1));
            }
        }

        isVisited[idx] = false;
        return ans;
    }

    public static void main(String[] args) {
        Prob27FatigueLevel sol = new Prob27FatigueLevel();

        int k = 80;
        int[][] d = {{80, 20}, {50, 40}, {30, 10}};

        System.out.println(sol.solution(k, d));
    }
}
