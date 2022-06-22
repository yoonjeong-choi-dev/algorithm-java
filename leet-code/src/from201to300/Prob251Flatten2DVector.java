package from201to300;

// https://leetcode.com/problems/flatten-2d-vector/
public class Prob251Flatten2DVector {
    class Vector2D {

        private int[][] vec;
        private int rowSize;
        private int row, col;

        public Vector2D(int[][] vec) {
            this.vec = vec;
            rowSize = vec.length;
            row = 0;
            while (row < rowSize && vec[row].length == 0) row++;
            col = 0;
        }

        public int next() {
            int ret = vec[row][col++];

            // 행을 바꿔야 하는 경우
            if (col == vec[row].length) {
                row++;
                while (row < rowSize && vec[row].length == 0) row++;
                col = 0;
            }

            return ret;
        }

        public boolean hasNext() {
            return row != rowSize;
        }
    }
}
