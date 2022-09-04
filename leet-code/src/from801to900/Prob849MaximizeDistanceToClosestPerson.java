package from801to900;

// https://leetcode.com/problems/maximize-distance-to-closest-person/
public class Prob849MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        int numSeats = seats.length;
        int left = 0;
        while (left < numSeats && seats[left] == 0) left++;

        int ans = left;
        int right;
        while (left < numSeats) {
            right = left + 1;
            while (right < numSeats && seats[right] == 0) right++;

            if (right != numSeats) {
                ans = Math.max(ans, (right - left) / 2);
            } else {
                ans = Math.max(ans, right - left - 1);
            }
            left = right;
        }

        return ans;
    }
}
