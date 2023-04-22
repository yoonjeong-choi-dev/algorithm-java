package from1601to1700;

import java.util.*;

// https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/
public class Prob1606FindServersThatHandledMostNumberOfRequests {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // 서버가 처리한 요청 개수
        int[] numHandled = new int[k];

        // 현재 사용 가능한 서버
        TreeSet<Integer> availableServer = new TreeSet<>();
        for (int i = 0; i < k; i++) availableServer.add(i);

        // 현재 처리 중인 서버 : 빨리 끝나는 것부터 반환하는 우선순위 큐
        // server[0] : 인덱스, server[1] : 끝나는 시간
        PriorityQueue<int[]> busyServer = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int numRequests = arrival.length;
        int start, end;
        for (int i = 0; i < numRequests; i++) {
            start = arrival[i];
            end = start + load[i];

            // 현재 요청 시간 이전에 처리된 서버들 반환
            while (!busyServer.isEmpty() && busyServer.peek()[1] <= start) {
                availableServer.add(busyServer.poll()[0]);
            }

            // 모든 서버가 처리중이면 무시
            if (availableServer.isEmpty()) continue;

            // 현재 요청 처리할 서버 찾기
            Integer curServer = availableServer.ceiling(i % k);
            if (curServer == null) {
                // i % k 보다 큰 유효 서버가 없는 경우 => 가장 낮은 숫자의 서버
                curServer = availableServer.first();
            }

            numHandled[curServer]++;
            availableServer.remove(curServer);
            busyServer.add(new int[]{curServer, end});
        }

        int max = numHandled[0];
        for (int i = 1; i < k; i++) max = Math.max(max, numHandled[i]);

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (numHandled[i] == max) ans.add(i);
        }

        return ans;
    }
}
