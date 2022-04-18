package from1to100;

// https://leetcode.com/problems/add-binary/
public class Prob67AddBinary {
    public String addBinary(String a, String b) {
        // Assumption : a.len >= b.len
        if (a.length() < b.length()) return addBinary(b, a);

        int len1 = a.length(), len2 = b.length();

        StringBuilder ans = new StringBuilder(len1);
        char c1, c2;
        int carry = 0, sum;
        for (int i = 0; i < len2; i++) {
            c1 = a.charAt(len1 - 1 - i);
            c2 = b.charAt(len2 - 1 - i);

            sum = carry;
            if (c1 == c2) {
                sum += c1 == '1' ? 2 : 0;
            } else {
                sum += 1;
            }

            if (sum > 1) {
                sum -= 2;
                carry = 1;
            } else {
                carry = 0;
            }

            ans.insert(0, sum == 1 ? '1' : '0');
        }

        // 나머지 저장
        for (int i = len2 + 1; i <= len1; i++) {
            sum = carry;
            sum += a.charAt(len1 - i) == '1' ? 1 : 0;


            if (sum > 1) {
                sum -= 2;
                carry = 1;
            } else {
                carry = 0;
            }

            ans.insert(0, sum == 1 ? '1' : '0');
        }

        if (carry == 1) {
            ans.insert(0, '1');
        }

        return ans.toString();
    }
}
