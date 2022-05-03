package from101to200;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// https://leetcode.com/problems/max-points-on-a-line/
public class Prob149MaxPointsOnLine {

    // line : a*x+b*y+c = 0 => [a,b,c] 를 키값으로 저장
    // 단 이때, (a,b,c) 는 서로소이고, a는 음수가 아님
    class Line {
        int a, b, c;

        public Line(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return a == line.a && b == line.b && c == line.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }

        @Override
        public String toString() {
            return String.format("[%d %d %d]", a, b, c);
        }
    }

    public int maxPoints(int[][] points) {
        int numPoint = points.length;

        // 점이 2개 이하인 경우
        if (numPoint < 3) return numPoint;

        // ax+by+c = 0  <=> y = -a/b * x + c/b
        Map<Line, Integer> counters = new HashMap<>();
        int a, b, c, gcd;
        for (int i = numPoint - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                b = -points[i][0] + points[j][0];
                a = points[i][1] - points[j][1];

                Line curLine;
                if (b == 0) {
                    // y 축과 평행한 직선
                    curLine = new Line(0, 1, points[i][0]);
                } else if (a == 0) {
                    // x 축과 평행한 직선
                    curLine = new Line(1, 0, points[i][1]);
                } else {
                    // 기울기 및 절편 계산
                    gcd = getGCD(a, b);
                    a /= gcd;
                    b /= gcd;
                    if (a < 0) {
                        a = -a;
                        b = -b;
                    }

                    c = -a * points[i][0] - b * points[i][1];
                    curLine = new Line(a, b, c);
                }

                counters.put(curLine, counters.getOrDefault(curLine, 0) + 1);
            }
        }

        int maxCount = 0;
        for (Integer count : counters.values()) maxCount = Math.max(maxCount, count);


        // n 개의 점이 같은 직선 상에 있는 경우 두 점마다 카운트가 1씩 증가
        // 카운트가 m 이면, m = n(n-1)/2 <=> n^2 - n +2m = 0
        // n = (1 + sqrt(1 + 8m))/2
        return (1 + (int) Math.sqrt(1 + 8 * maxCount)) / 2;
    }


    private int getGCD(int n, int m) {
        int temp;
        while (m != 0) {
            temp = n % m;
            n = m;
            m = temp;
        }

        return n;
    }

    public static void main(String[] args) {
        Prob149MaxPointsOnLine sol = new Prob149MaxPointsOnLine();

        int[][][] points = {
                {{1, 1}, {2, 2}, {3, 3}},
                {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}},
                {{-6, -1}, {3, 1}, {12, 3}},
                {{2, 3}, {3, 3}, {-5, 3}},
                {{0, 0}}
        };

        int[] ans = {3, 4, 3, 3, 1};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.maxPoints(points[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
