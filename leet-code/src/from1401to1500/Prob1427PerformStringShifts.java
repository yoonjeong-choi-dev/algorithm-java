package from1401to1500;

// https://leetcode.com/problems/perform-string-shifts/
public class Prob1427PerformStringShifts {
    public String stringShift(String s, int[][] shift) {
        // 양수 => 오른쪽 이동 (i->i+1), 음수 => 왼쪽 이동(i->i-1)
        int totalMove = 0;
        for (int[] move : shift) {
            if (move[0] == 0) {
                totalMove -= move[1];
            } else {
                totalMove += move[1];
            }
        }

        int len = s.length();
        totalMove = totalMove % len;
        if (totalMove < 0) totalMove += len;

        if(totalMove == 0) return s;
        else return s.substring(len - totalMove, len) + s.substring(0, len - totalMove);
    }
}
