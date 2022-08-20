package from801to900;

import java.util.Collections;
import java.util.PriorityQueue;

// https://leetcode.com/problems/minimum-number-of-refueling-stops/
public class Prob871MinimumNumberOfRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int ans = 0;

        // 지나간 주유소의 주유량을 내림차 순으로 저장 => 부족할 때마다 가장 큰 충전량을 사용하여 정답을 최소화
        PriorityQueue<Integer> stores = new PriorityQueue<>(Collections.reverseOrder());

        int prevLocation = 0, curLocation, curCapacity;
        for (int[] s : stations) {
            curLocation = s[0];
            curCapacity = s[1];

            // 현재 주유소 도착 : 기름량 업데이트
            startFuel -= (curLocation - prevLocation);

            // 기름이 부족한 경우 지나친 주유소에서 충전
            while (!stores.isEmpty() && startFuel < 0) {
                startFuel += stores.poll();
                ans++;
            }

            // 불가능한 경우
            if (startFuel < 0) return -1;

            // 현재 주유소 저장
            stores.add(curCapacity);
            prevLocation = curLocation;
        }

        // 마지막 주유소에서 목적지까지 이동
        startFuel -= (target - prevLocation);

        // 기름이 부족한 경우 지나친 주유소에서 충전
        while (!stores.isEmpty() && startFuel < 0) {
            startFuel += stores.poll();
            ans++;
        }

        // 불가능한 경우
        if (startFuel < 0) return -1;

        return ans;
    }
}
