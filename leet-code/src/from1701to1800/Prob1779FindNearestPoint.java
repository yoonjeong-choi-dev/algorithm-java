package from1701to1800;

// https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
public class Prob1779FindNearestPoint {
    public int nearestValidPoint(int x, int y, int[][] points) {

        int ans = -1;
        int minDist = Integer.MAX_VALUE, curDist;
        for(int i=0;i<points.length;i++){
            // Valid Point 인 경우에만 계산
            if(points[i][0] == x) {
                curDist = Math.abs(y - points[i][1]);
                if(minDist > curDist) {
                    minDist = curDist;
                    ans = i;
                }
            } else if ( points[i][1] == y) {
                curDist = Math.abs(x - points[i][0]);
                if(minDist > curDist) {
                    minDist = curDist;
                    ans = i;
                }
            }
        }

        return ans;
    }
}
