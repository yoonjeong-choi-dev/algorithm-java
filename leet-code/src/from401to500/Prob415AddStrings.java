package from401to500;

// https://leetcode.com/problems/add-strings/
public class Prob415AddStrings {
    // Condition 1 : You must solve the problem without using any built-in library for handling large integers
    // Condition 2 : You must also not convert the inputs to integers directly
    public String addStrings(String num1, String num2) {
        // Assumption : num1.length() >= num2.length();
        if (num1.length() < num2.length()) return addStrings(num2, num1);

        int len1 = num1.length(), len2 = num2.length();
        StringBuilder ans = new StringBuilder(len1 + 1);

        boolean isCarry = false;
        int sum;

        // sum with LSB digit
        for (int i = 1; i <= len2; i++) {
            sum = num1.charAt(len1 - i) - '0' + num2.charAt(len2 - i) - '0';
            if (isCarry) sum += 1;

            if (sum > 9) {
                sum = sum - 10;
                isCarry = true;
            } else {
                isCarry = false;
            }
            ans.insert(0, (char) (sum + '0'));
        }


        // 나머지 저장
        for (int i = len2 + 1; i <= len1; i++) {
            sum = num1.charAt(len1 - i) - '0';
            if (isCarry) sum += 1;

            if (sum > 9) {
                sum = sum - 10;
                isCarry = true;
            } else {
                isCarry = false;
            }
            ans.insert(0, (char) (sum + '0'));
        }

        if (isCarry) ans.insert(0, '1');

        return ans.toString();
    }

    public static void main(String[] args) {
        Prob415AddStrings sol = new Prob415AddStrings();

        String[] nums1 = {"11", "456", "0", "999"};
        String[] nums2 = {"123", "77", "0", "1"};

        String[] ans = {"134", "533", "0", "1000"};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            String mySol = sol.addStrings(nums1[i], nums2[i]);
            if (mySol.equals(ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }

    }
}
