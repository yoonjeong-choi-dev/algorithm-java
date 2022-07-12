package from801to900;

// https://leetcode.com/problems/rectangle-overlap/
public class Prob836RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // 겹치는 직사각형 계산
        int x1 = Math.max(rec1[0], rec2[0]);
        int y1 = Math.max(rec1[1], rec2[1]);
        int x2 = Math.min(rec1[2], rec2[2]);
        int y2 = Math.min(rec1[3], rec2[3]);

        // 계산된 직사각형이 실제 직사각형을 구성하는지 확인
        return x1 < x2 && y1 < y2;
    }
}
