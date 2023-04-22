package programmers.level2;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/68936
public class Prob39QuadCompression {

    private int[][] arr;

    public int[] solution(int[][] arr) {
        this.arr = arr;
        return recursive(0, 0, arr.length);
    }

    private int[] recursive(int curRow, int curCol, int size) {
        if (size == 1 || canCompress(curRow, curCol, size)) {
            int numZero = arr[curRow][curCol] == 1 ? 0 : 1;
            return new int[]{numZero, 1 - numZero};
        }

        int[] ans = new int[2];
        int[][] subAns = new int[4][];

        // 네 개의 영역에 대해서 재귀 호출
        int curIdx = 0;
        int nextSize = size / 2;
        subAns[curIdx++] = recursive(curRow, curCol, nextSize);
        subAns[curIdx++] = recursive(curRow, curCol + nextSize, nextSize);
        subAns[curIdx++] = recursive(curRow + nextSize, curCol, nextSize);
        subAns[curIdx++] = recursive(curRow + nextSize, curCol + nextSize, nextSize);

        for (int i = 0; i < curIdx; i++) {
            ans[0] += subAns[i][0];
            ans[1] += subAns[i][1];
        }

        return ans;
    }

    private boolean canCompress(int curRow, int curCol, int size) {
        int target = arr[curRow][curCol];
        for (int i = curRow + size - 1; i >= curRow; i--) {
            for (int j = curCol + size - 1; j >= curCol; j--) {
                if (arr[i][j] != target) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Prob39QuadCompression sol = new Prob39QuadCompression();
        int[][][] arrays = {
                {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}},
                {{1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 1, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 1, 1}}
        };

        int[][] ans = {{4, 9}, {10, 15}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int[] mySol = sol.solution(arrays[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
