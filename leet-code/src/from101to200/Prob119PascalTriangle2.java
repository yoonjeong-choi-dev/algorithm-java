package from101to200;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/pascals-triangle-ii/
public class Prob119PascalTriangle2 {
    public List<Integer> getRow(int rowIndex) {
        int totalSize = rowIndex+1;

        // 우선 모든 열을 1로 초기화한다
        List<Integer> ans = new ArrayList<>(totalSize);
        for(int i=0;i<totalSize;i++) {
            ans.add(1);
        }

        // bottom-up 방식으로 계산
        int prev, temp;
        for(int i=2;i<totalSize;i++){
            prev = 1;
            for(int j=1;j<i;j++){
                // update ans[i][j] <- ans[i-1][j-1] + ans[i-1][j]
                temp = prev + ans.get(j);
                prev = ans.get(j);
                ans.set(j, temp);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Prob119PascalTriangle2 sol = new Prob119PascalTriangle2();
        for(int i=0;i<6;i++){
            System.out.printf("%d : %s\n", i, sol.getRow(i));
        }
    }
}
