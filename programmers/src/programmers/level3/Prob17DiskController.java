package programmers.level3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://programmers.co.kr/learn/courses/30/lessons/42627
public class Prob17DiskController {
    public int solution(int[][] jobs) {

        // 요청들을 요청 시간 순서대로 정렬
        // 요청 시간이 같은 경우, 처리 시간이 짧은 순서대로 정렬
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }

            }
        });

        int numJobs = jobs.length;

        // 하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리
        // => 현재 처리하는 작업이 끝날 때까지 들어온 요청을 우선순위 큐에 넣는다
        // : 이때 우선 순위 큐에는 작업이 빨리 끝나는 순서대로 넣는다 => 기다리는 시간을 최소화 하기 위해
        // : 요청 시간 순선대로 정렬하였으므로, 처리 시간이 짧은 순서대로 힙에 저장
        PriorityQueue<int[]> pq = new PriorityQueue<>(numJobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int answer = 0;
        int[] curJob;
        int start;
        int curTime = 0;

        int curIdx = 0;
        while (curIdx < numJobs) {
            if (pq.isEmpty()) {
                // 큐가 비어있는 경우 추가
                pq.add(jobs[curIdx++]);
            } else {
                // 큐에 처리해야 하는 작업이 있는 경우 해당 작업 처리
                curJob = pq.poll();

                // 해당 작업 처리 후 시간 업데이트
                start = Math.max(curTime, curJob[0]);
                curTime = start + curJob[1];

                // 해당 작업 처리 시간
                answer += curTime - curJob[0];

                // 현재 작업이 처리해야 하는 작업이 끝날 때까지 들어온 요청을 넣는다
                while (curIdx < numJobs && jobs[curIdx][0] < curTime) {
                    pq.add(jobs[curIdx++]);
                }
            }
        }

        // 나머지 작업들 처리
        while (!pq.isEmpty()) {
            curJob = pq.poll();

            // 해당 작업 처리 후 시간 업데이트
            start = Math.max(curTime, curJob[0]);
            curTime = start + curJob[1];

            // 해당 작업 처리 시간
            answer += curTime - curJob[0];
        }

        // 평균값 반환
        return answer / numJobs;
    }

    public static void main(String[] args) {
        Prob17DiskController sol = new Prob17DiskController();
        int[][][] jobs = {
                {{0, 3}, {1, 9}, {2, 6}},
                {{0, 3}, {1, 5}, {2, 4}},
                {{0, 5}, {1, 2}, {5, 5}},
                {{0, 9}, {0, 4}, {0, 5}, {0, 7}, {0, 3}},
                {{1, 9}, {1, 4}, {1, 5}, {1, 7}, {1, 3}}
        };

        int[] ans = {9, 6, 6, 13, 13};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(jobs[i]);
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
