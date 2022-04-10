package from101to200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/triangle/
public class Prob120Triangle {
    private int minimumTotal(List<List<Integer>> triangle) {
        // bottom up dp
        int min;
        for (int row = triangle.size() - 1; row > 0; row--) {
            for (int col = 0; col < row; col++) {
                min = Math.min(triangle.get(row).get(col), triangle.get(row).get(col + 1));
                triangle.get(row - 1).set(col, min + triangle.get(row - 1).get(col));
            }
        }

        System.out.println(triangle);
        return triangle.get(0).get(0);
    }


    public static void main(String[] args) {
        Prob120Triangle sol = new Prob120Triangle();

        List<List<Integer>> test = new ArrayList<>(4);
        test.add(new ArrayList<Integer>(Arrays.asList(2)));
        test.add(new ArrayList<Integer>(Arrays.asList(3,4)));
        test.add(new ArrayList<Integer>(Arrays.asList(6,5,7)));
        test.add(new ArrayList<Integer>(Arrays.asList(4,1,8,3)));

        System.out.println(test);
        int an = sol.minimumTotal(test);

    }
}
