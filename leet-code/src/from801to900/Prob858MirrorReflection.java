package from801to900;

// https://leetcode.com/problems/mirror-reflection/
public class Prob858MirrorReflection {
    public int mirrorReflection(int p, int q) {
        // square : (0,0) -> (p,p), start : (0,0), destination : (p,q)
        // (0,0) -> (p,q) -> (0, 2q) -> (p, 3q) -> (0, 4q) -> ...
        // => k*q 가 p로 나누어 떨어지는 시점이 정답
        // Case 1 : (0, k*q) => ans == 2
        // Case 2 : (p, k*q)
        // - k*q = (2*n)*p => ans == 0
        // - k*q = (2*n+1)*p => ans == 1

        // totalHeight = k*q
        int totalHeight = q;
        int currentX = p;
        while (totalHeight % p != 0) {
            totalHeight += q;
            currentX = p - currentX;
        }

        if (currentX == 0) return 2;
        else {
            int n = totalHeight / p;
            return n % 2;
        }
    }
}
