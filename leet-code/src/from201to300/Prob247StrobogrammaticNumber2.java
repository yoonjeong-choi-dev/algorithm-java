package from201to300;

import java.util.*;

// https://leetcode.com/problems/strobogrammatic-number-ii/
public class Prob247StrobogrammaticNumber2 {
    static final Map<Character, Character> strobogrammaticMap = new HashMap<Character, Character>() {{
        put('0', '0');
        put('1', '1');
        put('6', '9');
        put('8', '8');
        put('9', '6');
    }};

    static final char[] allPossibleChar = {'0', '1', '6', '8', '9'};
    static final char[] onlyMiddlePossibleChar = {'0', '1', '8'};

    int n, mid;
    char[] arr;
    List<String> ans;

    public List<String> findStrobogrammatic(int n) {
        // 첫번째 숫자가 0인 경우는 불가능 : n==1 인 경우만 엣지케이스
        if (n == 1) {
            ans = new ArrayList<>(onlyMiddlePossibleChar.length);
            for (char c : onlyMiddlePossibleChar) {
                ans.add(String.valueOf(c));
            }
            return ans;
        }


        this.n = n;
        mid = n / 2;
        arr = new char[n];

        int totalSize = (int) Math.pow(allPossibleChar.length, mid);

        if (n % 2 == 0) {
            ans = new ArrayList<>(totalSize);
            evenRecursive(0);
        } else {
            totalSize *= onlyMiddlePossibleChar.length;
            ans = new ArrayList<>(totalSize);
            oddRecursive(0);
        }

        return ans;
    }

    private void evenRecursive(int idx) {
        if (idx == mid) {
            for (int i = 0; i < mid; i++) {
                arr[n - 1 - i] = strobogrammaticMap.get(arr[i]);
            }
            ans.add(new String(arr));
            return;
        }

        for (char c : allPossibleChar) {
            // 첫번째 숫자가 0인 경우는 불가능
            if (idx == 0 && c == '0') continue;

            arr[idx] = c;
            evenRecursive(idx + 1);
        }
    }

    private void oddRecursive(int idx) {
        if (idx == mid) {
            for (int i = 0; i < mid; i++) {
                arr[n - 1 - i] = strobogrammaticMap.get(arr[i]);
            }

            // 가운데 위치에 가능한 문자들 추가
            for (char c : onlyMiddlePossibleChar) {

                arr[mid] = c;
                ans.add(new String(arr));
            }

            return;
        }

        for (char c : allPossibleChar) {
            // 첫번째 숫자가 0인 경우는 불가능
            if (idx == 0 && c == '0') continue;

            arr[idx] = c;
            oddRecursive(idx + 1);
        }
    }

    public static void main(String[] args) {
        Prob247StrobogrammaticNumber2 sol = new Prob247StrobogrammaticNumber2();
        int[] lens = {2, 1, 3};

        String[][] ans = {
                {"11", "69", "88", "96"},
                {"0", "1", "8"},
                {"101", "111", "181", "609", "619", "689", "808", "818", "888", "906", "916", "986"}

        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            String[] mySol = sol.findStrobogrammatic(lens[i]).toArray(new String[0]);
            if (Arrays.deepEquals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.deepToString(mySol));
                System.out.println("Expected : " + Arrays.deepToString(ans[i]));
            }
        }
    }
}
