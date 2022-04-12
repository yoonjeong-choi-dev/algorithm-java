package from701to800;

// https://leetcode.com/problems/to-lower-case/
public class Prob709ToLowerCase {
    public String toLowerCase(String s) {
        int len = s.length();
        int lowerDiff = 'a' - 'A';

        char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            // 대문자인 경우에만 변환
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                arr[i] = (char) (lowerDiff + s.charAt(i));
            } else {
                arr[i] = s.charAt(i);
            }
        }

        return new String(arr);
    }

    private String useBuiltInFunction(String s) {
        return s.toLowerCase();
    }
}
