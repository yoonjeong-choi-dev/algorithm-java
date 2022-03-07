package programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

// https://programmers.co.kr/learn/courses/30/lessons/42583
public class Prob13TrucksOnBridge {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int numTrucks = truck_weights.length;
        int curBridgeWeight = 0;

        // 다리 위에 있는 트럭 정보
        // 배열 정보 - 0 : 트럭 무게, 1: 트럭이 다리에 올라간 시간
        Queue<int[]> trucks = new ArrayDeque<>(numTrucks);
        int[] truck;

        int time = 0;
        for(int i=0;i<numTrucks;i++){
            // 기본적으로 현재 트럭이 올라가려면 시간이 1틱 지나야 함
            time++;

            // 다리 위의 트럭이 도착한 경우 큐에서 빼주어야 함
            if(trucks.peek()!= null && trucks.peek()[1] + bridge_length == time) {
                curBridgeWeight -= trucks.poll()[0];
            }

            // 현재 트럭(i)가 트럭에 올라갈 때까지 대기 => time 업데이트
            while(trucks.peek()!= null && curBridgeWeight + truck_weights[i] > weight) {
                truck = trucks.poll();
                time = truck[1] + bridge_length;
                curBridgeWeight -= truck[0];
            }

            // 현재 트럭 진입
            trucks.add(new int[] {truck_weights[i], time});
            curBridgeWeight += truck_weights[i];
        }

        // 다리 위에 남아 있는 트럭들을 이용해서 마지막 시간 계산
        while (trucks.peek()!=null) {
            truck = trucks.poll();
            time = truck[1] + bridge_length;
        }

        return time;
    }

    public static void main(String[] args) {
        Prob13TrucksOnBridge sol = new Prob13TrucksOnBridge();

        int[] lengths = {2, 100, 100, 5, 5, 3, 5, 7};
        int[] weights = {10, 100, 100, 5, 5, 1, 5, 7};
        int[][] trucks = {
                {7, 4, 5, 6},
                {10},
                {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                {2, 2, 2, 2, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 2, 2},
                {1, 1, 1},
                {1, 1, 1, 1, 1, 2, 2},
                {1, 1, 1, 1, 1, 3, 3}
        };

        int[] ans = {8, 101, 110, 19, 19, 10, 14, 18};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int mySol = sol.solution(lengths[i], weights[i], trucks[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
