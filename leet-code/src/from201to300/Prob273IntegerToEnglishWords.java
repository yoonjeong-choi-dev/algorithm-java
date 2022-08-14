package from201to300;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/integer-to-english-words/
public class Prob273IntegerToEnglishWords {

    private static final Map<Integer, String> digitToString = new HashMap<Integer, String>() {{
        put(1, "One");
        put(2, "Two");
        put(3, "Three");
        put(4, "Four");
        put(5, "Five");
        put(6, "Six");
        put(7, "Seven");
        put(8, "Eight");
        put(9, "Nine");
        put(10, "Ten");
        put(11, "Eleven");
        put(12, "Twelve");
        put(13, "Thirteen");
        put(14, "Fourteen");
        put(15, "Fifteen");
        put(16, "Sixteen");
        put(17, "Seventeen");
        put(18, "Eighteen");
        put(19, "Nineteen");
    }};

    private static final Map<Integer, String> tenToString = new HashMap<Integer, String>() {{
        put(2, "Twenty");
        put(3, "Thirty");
        put(4, "Forty");
        put(5, "Fifty");
        put(6, "Sixty");
        put(7, "Seventy");
        put(8, "Eighty");
        put(9, "Ninety");
    }};

    // "One Hundred "

    public String numberToWords(int num) {
        // 2 147 483 647
        // Billion, Million, Thousand, ""
        String[] names = new String[]{"", " Thousand", " Million", " Billion"};
        int curIdx = 0;

        // 3자리 단위로 끊어 읽는다
        StringBuilder ans = new StringBuilder();
        StringBuilder curAns;
        int curNum, digit;
        while (num != 0) {
            curNum = num % 1000;

            if (curNum != 0) {
                curAns = new StringBuilder();

                digit = curNum / 100;
                curNum = curNum % 100;
                if (digit != 0) {
                    curAns.append(digitToString.get(digit)).append(" Hundred");
                }

                if (curNum >= 20) {
                    digit = curNum / 10;
                    curNum = curNum % 10;

                    if (curAns.length() != 0) curAns.append(" ");
                    curAns.append(tenToString.get(digit));

                    if (curNum != 0) curAns.append(" ").append(digitToString.get(curNum));
                } else if (curNum > 0) {
                    if (curAns.length() != 0) curAns.append(" ");
                    curAns.append(digitToString.get(curNum));
                }

                curAns.append(names[curIdx]);
                if (ans.length() != 0) curAns.append(" ");
                ans.insert(0, curAns);
            }

            curIdx++;
            num /= 1000;
        }

        // remove first and last white space
        ans.deleteCharAt(ans.length() - 1);
        return ans.toString();
    }
}
