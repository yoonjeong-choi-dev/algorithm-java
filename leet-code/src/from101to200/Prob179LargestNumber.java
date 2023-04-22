package from101to200;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/largest-number/
public class Prob179LargestNumber {
    public String largestNumber(int[] nums) {
        int numberLen = nums.length;

        // 우선 숫자들을 문자열로 변경
        String[] strings = new String[numberLen];
        for (int i = 0; i < numberLen; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        // s1 < s2 <=> s1+s2 > s1+s2
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s2.compareTo(s1);
            }
        });


        StringBuilder ans = new StringBuilder();
        for (String string : strings) ans.append(string);

        // MSB에는 0이 들어가면 안된다
        while (ans.length() > 0 && ans.charAt(0) == '0') ans.deleteCharAt(0);

        // 비어 있는 경우 0으로
        if (ans.length() == 0) ans.append('0');

        return ans.toString();
    }
}
