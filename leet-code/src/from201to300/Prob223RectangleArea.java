package from201to300;

// https://leetcode.com/problems/rectangle-area/
public class Prob223RectangleArea {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // total = area(rec1) + area(rec2) - intersect(rec1, rec2)
        int ans = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);

        // intersect => Ref : Problem 836
        int x1 = Math.max(ax1, bx1);
        int y1 = Math.max(ay1, by1);
        int x2 = Math.min(ax2, bx2);
        int y2 = Math.min(ay2, by2);

        int width = x2 - x1;
        int height = y2 - y1;
        if (width > 0 && height > 0) ans -= width * height;

        return ans;
    }
}
