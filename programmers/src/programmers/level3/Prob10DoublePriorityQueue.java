package programmers.level3;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Prob10DoublePriorityQueue {
    public int[] solution(String[] operations) {
        int numOps = operations.length;

        // 최대힙 및 최소힙
        PriorityQueue<Integer> max = new PriorityQueue<>(numOps, Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>(numOps);

        // 현재 큐에 남아있는 숫자 갯수 == Min(max.size(), min.size())
        int curNum = 0;

        for (String operation : operations) {
            String[] parsed = operation.split(" ");
            String command = parsed[0];
            int number = Integer.parseInt(parsed[1]);

            if (command.equals("I")) {
                // 추가해야 하는 경우 양쪽 모두 추가
                max.add(number);
                min.add(number);
                curNum++;
            } else {
                // 삭제해야 하는 경우
                // 삭제할 요소가 없는 경우 무시
                if (curNum == 0) continue;

                // curNum == Min(max.size(), min.size()) > 0 이므로 힙의 크기 비교 필요 X
                if (number < 0) {
                    // 최소값 삭제
                    min.poll();
                } else {
                    // 최대값 삭제
                    max.poll();
                }
                curNum--;
            }
        }

        // 모든 연산 처리 후, 비어 있는 큐인 경우
        if (curNum == 0) return new int[]{0, 0};

        // 최소 힙과 최대 힙 동기화 : 남아 있는 숫자들을 내림차순을 정렬
       int[] arr = new int[curNum];
        for (int i = 0; i < curNum; i++) {
            arr[i] = max.poll();
        }

        return new int[]{arr[0], arr[curNum - 1]};
    }

    public static void main(String[] args) {
        Prob10DoublePriorityQueue sol = new Prob10DoublePriorityQueue();

        String[][] ops = {
                {"I 16", "D 1"},
                {"I 7", "I 5", "I -5", "D -1"},
                {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},
                {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"},
                {"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"}
        };

        int[][] ans = {{0, 0}, {7, 5}, {0, 0}, {333, -45}, {6, 5}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int[] mySol = sol.solution(ops[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
