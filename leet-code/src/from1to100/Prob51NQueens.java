package from1to100;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/n-queens/
public class Prob51NQueens {

    private List<List<Integer>> rowIndices;
    private int boardSize;

    public List<List<String>> solveNQueens(int n) {
        boardSize = n;
        rowIndices = new ArrayList<>();

        List<Integer> rowInfo = new ArrayList<>(boardSize);
        recursive(0, rowInfo);

        List<List<String>> ans = new ArrayList<>(rowIndices.size());

        StringBuilder base = new StringBuilder(boardSize);
        for (int i = 0; i < boardSize; i++) base.append(".");


        for (List<Integer> info : rowIndices) {
            List<String> curAns = new ArrayList<>(boardSize);
            for(int queenIdx : info){
                base.setCharAt(queenIdx, 'Q');
                curAns.add(base.toString());
                base.setCharAt(queenIdx, '.');
            }
            ans.add(curAns);
        }

        return ans;
    }

    private void recursive(int curRow, List<Integer> rowInfo) {
        if (curRow == boardSize) {
            rowIndices.add(new ArrayList<>(rowInfo));
            return;
        }

        // 현재 줄에 배치 가능한 위치 탐색 및 재귀 호출
        boolean canAssign;
        int prevCol;
        for (int col = 0; col < boardSize; col++) {
            canAssign = true;

            // [0, curRow-1] 까지 배치된 rowInfo 이용해서 배치 가능한지 확인
            for (int prevRow = 0; prevRow < curRow; prevRow++) {
                prevCol = rowInfo.get(prevRow);

                // 같은 열이면 불가능
                if (col == prevCol) {
                    canAssign = false;
                    break;
                }

                // 대각선 열 불가능
                // (prevRow, prevCol) 기준으로 curRow 위치의 열 위치에 대해서 좌하향 및 우하향 대각선 검색
                if (col == prevCol - (prevRow - curRow) || col == prevCol + (prevRow - curRow)) {
                    canAssign = false;
                    break;
                }
            }

            if (canAssign) {
                rowInfo.add(col);
                recursive(curRow + 1, rowInfo);
                rowInfo.remove(curRow);
            }
        }
    }
}
