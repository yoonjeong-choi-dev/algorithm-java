package from1501to1600;

import java.util.Arrays;

// https://leetcode.com/problems/put-boxes-into-the-warehouse-i/
public class Prob1564PutBoxesIntoWarehouse {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        // 작은 상자부터 고려
        Arrays.sort(boxes);

        int numBox = boxes.length;
        int numWarehouse = warehouse.length;

        // 현재 위치에 올 수 있는 높이
        int[] possibleHeight = new int[numWarehouse];
        possibleHeight[0] = warehouse[0];
        for (int i = 1; i < numWarehouse; i++) {
            possibleHeight[i] = Math.min(possibleHeight[i - 1], warehouse[i]);
        }

        int ans = 0;

        int boxIdx = 0, warehouseIdx = numWarehouse - 1;
        int curBox;
        while (boxIdx < numBox && warehouseIdx >= 0) {
            curBox = boxes[boxIdx++];

            // 현재 박스를 적재할 수 있는 창고 위치 계산
            while (warehouseIdx >= 0 && (curBox > warehouse[warehouseIdx] || curBox > possibleHeight[warehouseIdx])) {
                warehouseIdx--;
            }
            // 현재 박스를 적재할 수 없는 경우
            if (warehouseIdx < 0) break;

            // warehouseIdx 위치에 적재
            ans++;

            warehouseIdx--;
        }

        return ans;
    }
}
