package from901to1000;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/reorder-data-in-log-files/
public class Prob937ReorderDataInLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        int numLogs = logs.length;

        // 정렬에 필요한 정보들
        Integer[] index = new Integer[numLogs];
        String[][] logTokens = new String[numLogs][];
        boolean[] isDigitLog = new boolean[numLogs];

        for (int i = 0; i < numLogs; i++) {
            index[i] = i;
            logTokens[i] = logs[i].split(" ");
            isDigitLog[i] = Character.isDigit(logTokens[i][1].charAt(0));
        }

        Arrays.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (isDigitLog[o1] && isDigitLog[o2]) {
                    // The digit-logs maintain their relative ordering.
                    return 0;
                } else if (isDigitLog[o1] ^ isDigitLog[o2]) {
                    // The letter-logs come before all digit-logs.
                    return isDigitLog[o1] ? 1 : -1;
                } else {
                    // The letter-logs are sorted lexicographically by their contents
                    int minLen = Math.min(logTokens[o1].length, logTokens[o2].length);
                    int ret;
                    for (int i = 1; i < minLen; i++) {
                        ret = logTokens[o1][i].compareTo(logTokens[o2][i]);
                        if (ret != 0) return ret;
                    }

                    // If their contents are the same, then sort them lexicographically by their identifiers
                    return (logTokens[o1].length == logTokens[o2].length) ?
                            logTokens[o1][0].compareTo(logTokens[o2][0]) :
                            logTokens[o1].length - logTokens[o2].length;
                }
            }
        });

        String[] ans = new String[numLogs];
        for (int i = 0; i < numLogs; i++) ans[i] = logs[index[i]];
        return ans;
    }
}
