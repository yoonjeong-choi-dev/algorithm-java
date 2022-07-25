package from1001to1100;

// https://leetcode.com/problems/car-pooling/
public class Prob1094CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        // 0 <= from < to <= 1000
        // passengers[i] : i 시간에 승객 변화량
        int[] passengers = new int[1001];
        for(int[] trip : trips) {
            passengers[trip[1]] += trip[0];
            passengers[trip[2]] -= trip[0];
        }

        int curNum = 0;
        for(int diff : passengers) {
            curNum += diff;
            if(curNum > capacity) return false;
        }

        return true;
    }
}
