package from701to800;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// https://leetcode.com/problems/number-of-corner-rectangles/
public class Prob750NumberOfCornerRectangles {

    class Pair {
        int i, j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i && j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public int countCornerRectangles(int[][] grid) {
        // 각 행의 열 정보 (i,j) where grid[r][i] = 1 && grid[r][j] = 1 && i < j 개수 추적
        // 현재 행이 r 인 경우, grid[k][i] = 1 && grid[k][j] = 1 && i < j && k < r 개수 저장
        // => 행을 아래로 내려가면서 탐색하다가 (i,j) 를 만나는 경우, 해당 행 위에 있는 (i,j) 개수로 사각형 만들기 가능
        // i.e 현재 행을 사각형의 아랫변으로 함
        Map<Pair, Integer> numPairs = new HashMap<>();
        int ans = 0;

        Pair pair;
        int curPossibleRows;
        for (int[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != 1) continue;

                for (int j = i + 1; j < row.length; j++) {
                    if (row[j] != 1) continue;

                    pair = new Pair(i, j);
                    curPossibleRows = numPairs.getOrDefault(pair, 0);
                    ans += curPossibleRows;
                    numPairs.put(pair, curPossibleRows + 1);
                }
            }
        }
        return ans;
    }
}
