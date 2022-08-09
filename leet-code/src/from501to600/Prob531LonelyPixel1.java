package from501to600;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/lonely-pixel-i/
public class Prob531LonelyPixel1 {
    public int findLonelyPixel(char[][] picture) {
        int rowSize = picture.length, colSize = picture[0].length;

        // 각 행 및 열에 있는 B 개수
        int[] rowWise = new int[rowSize];
        int[] colWise = new int[colSize];

        // B 인덱스 정보
        List<int[]> bIndex = new ArrayList<>();

        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                if(picture[i][j] == 'B') {
                    bIndex.add(new int[]{i,j});
                    rowWise[i]++;
                    colWise[j]++;
                }
            }
        }

        int ans = 0;
        for(int[] index : bIndex) {
            if(rowWise[index[0]] == 1 && colWise[index[1]] == 1) ans++;
        }

        return ans;
    }
}
