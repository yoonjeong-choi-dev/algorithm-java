package from1301to1400;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/cinema-seat-allocation/
public class Prob1386CinemaSeatAllocation {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // 한 줄에 예약 가능한 위치 : (2,3,4,5), (4,5,6,7), (6,7,8,9)
        // => 예약 상태를 비트로 표현 : 가능하면 1, 불가능하면 0 => 000 ~ 111
        Map<Integer, Integer> rowInfo = new HashMap<>();
        int row, col;
        for (int[] reserve : reservedSeats) {
            row = reserve[0];
            col = reserve[1];
            // 양 끝은 무시해도 됨
            if (col == 1 || col == 10) continue;

            if (!rowInfo.containsKey(row)) rowInfo.put(row, 7);

            if (col >= 2 && col <= 5) rowInfo.put(row, rowInfo.get(row) & 6);
            if (col >= 4 && col <= 7) rowInfo.put(row, rowInfo.get(row) & 5);
            if (col >= 6) rowInfo.put(row, rowInfo.get(row) & 3);
        }

        // 예약이 되어 있지 않은 줄은 최대 2가족 까지 배치 가능
        int ans = 2* (n - rowInfo.size());
        for(int info : rowInfo.values()) ans += info == 0 ? 0 : 1;

        return ans;
    }
}
