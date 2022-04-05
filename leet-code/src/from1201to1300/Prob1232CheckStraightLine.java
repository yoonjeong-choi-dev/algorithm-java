package from1201to1300;

// https://leetcode.com/problems/check-if-it-is-a-straight-line/
public class Prob1232CheckStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {

        // 처음 두 점을 이용하여 직선 방정식 계산 : ax + by = c
        // (x0,y0) ~ (x1,y1) 인 직선에 대해서
        // a=y0-y1, b=x1-x0, c=(y0-y1)x0+(x1-x0)y0=a*x0 + b*y0
        int a = coordinates[0][1] - coordinates[1][1];
        int b = coordinates[1][0] - coordinates[0][0];
        int c = a * coordinates[0][0] + b * coordinates[0][1];

        for (int i = 2; i < coordinates.length; i++) {
            if (a * coordinates[i][0] + b * coordinates[i][1] != c) return false;
        }

        return true;
    }
}
