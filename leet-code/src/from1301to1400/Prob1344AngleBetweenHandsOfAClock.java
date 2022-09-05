package from1301to1400;

// https://leetcode.com/problems/angle-between-hands-of-a-clock/
public class Prob1344AngleBetweenHandsOfAClock {
    public double angleClock(int hour, int minutes) {
        // 1분은 360/60 = 6도
        double minuteAngle = 6 * minutes;

        // 1시간은 360/12 = 30도
        // 1분당 30/60 = 0.5도 이동
        // hour * 30 + minute * (30/60)
        hour = hour % 12;
        double hourAngle = 30 * hour + minutes / 2.0;

        double diff = Math.abs(hourAngle - minuteAngle);
        // return the smaller angle (in degrees)
        return Math.min(diff, 360.0 - diff);
    }
}
