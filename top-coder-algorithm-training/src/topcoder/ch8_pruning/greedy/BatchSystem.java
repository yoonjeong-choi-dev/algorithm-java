package topcoder.ch8_pruning.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class BatchSystem {
    public int[] schedule(int[] duration, String[] users) {
        Integer[] indices = new Integer[duration.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }

        // 각 유저가 요청한 작업들의 전체 소요 시간 저장
        HashMap<String, Integer> jobs = new HashMap<>();
        for (int i = 0; i < duration.length; i++) {
            if (!jobs.containsKey(users[i])) {
                jobs.put(users[i], 0);
            }
            jobs.put(users[i], jobs.get(users[i]) + duration[i]);
        }

        //System.out.println(jobs);

        // 가장 짧은 작업부터 배치
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer idx1, Integer idx2) {
                // 같은 유저인 경우 인덱스 순서대로
                if (users[idx1].equals(users[idx2])) {
                    return idx1 - idx2;
                }

                // 유저가 다른 경우, 전체 소요시간이 짧은 작업부터
                int time1 = jobs.get(users[idx1]);
                int time2 = jobs.get(users[idx2]);

                if(time1 != time2) {
                    return time1 - time2;
                } else {
                    // 소요 시간이 같은 경우 유저의 이름에 대한 사전 순서
                    return users[idx1].compareTo(users[idx2]);
                }
            }
        };

        Arrays.sort(indices, comparator);

        int[] ans = new int[duration.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = indices[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        BatchSystem sol = new BatchSystem();

        int[][] durations = {
                {400, 100, 100, 100},
                {200, 200, 200},
                {100, 200, 50}
        };
        String[][] users = {
                {"Danny Messer", "Stella Bonasera", "Stella Bonasera", "Mac Taylor"},
                {"Gil", "Sarah", "Warrick"},
                {"Hortio Caine", "hortio caine", "YEAAAAAH"}
        };

        int[][] ans = {
                {3, 1, 2, 0},
                {0, 1, 2},
                {2, 0, 1}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int[] mySol = sol.schedule(durations[i], users[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
