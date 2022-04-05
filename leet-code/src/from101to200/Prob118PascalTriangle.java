package from101to200;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/pascals-triangle/
public class Prob118PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>(numRows);

        // 첫번째 행 추가
        List<Integer> curRow = new ArrayList<>(1);
        curRow.add(1);
        ans.add(curRow);

        List<Integer> prevRow = curRow;
        for (int i = 1; i < numRows; i++) {
            curRow = new ArrayList<>(i + 1);

            curRow.add(1);

            for (int j = 1; j < i; j++) {
                curRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            curRow.add(1);
            ans.add(curRow);
            prevRow = curRow;
        }

        return ans;
    }
}
