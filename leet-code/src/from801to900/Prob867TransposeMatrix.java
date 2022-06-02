package from801to900;

// https://leetcode.com/problems/transpose-matrix/
public class Prob867TransposeMatrix {
    public int[][] transpose(int[][] matrix) {
        int m=matrix.length, n=matrix[0].length;
        int[][] ret = new int[n][m];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                ret[j][i] = matrix[i][j];
            }
        }
        return ret;
    }
}
