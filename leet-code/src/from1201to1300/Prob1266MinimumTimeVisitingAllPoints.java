package from1201to1300;

// https://leetcode.com/problems/minimum-time-visiting-all-points/
public class Prob1266MinimumTimeVisitingAllPoints {
    public int minTimeToVisitAllPoints(int[][] points) {
        // Idea : (0,0) -> (x,y) where x > y 일 때 3 가지 경우
        // 1. x+y : (0,0) -> (x,0) -> (x,y)
        // 2. y+(x-y) = x : (0,0) -> (y,y) -> (x,y)
        // 3. x+(x-y) = 2x-y : (0,0) -> (x,x) -> (x,y)
        // => x>y 조건을 이용하면 이 중 최소 값은 x.
        // 비슷하게 x<y 인 경우 최소 경로 시간은 y
        int ans = 0;
        int x, y;
        for (int i = 1; i < points.length; i++) {
            x = Math.abs(points[i][0] - points[i - 1][0]);
            y = Math.abs(points[i][1] - points[i - 1][1]);

            ans += Math.max(x, y);
        }
        return ans;
    }
}
