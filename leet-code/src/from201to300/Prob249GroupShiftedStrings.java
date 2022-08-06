package from201to300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/group-shifted-strings/
public class Prob249GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        int numStrings = strings.length;

        // 각 문자를 쉬프트해서 첫글자를 0으로 만들어서 키값으로 사용
        Map<String, List<Integer>> map = new HashMap<>();

        // 1 <= strings[i].length <= 50
        int[] diff = new int[50];

        int curLen;
        String curString;
        StringBuilder key;
        String keyStr;
        for (int i = 0; i < numStrings; i++) {
            curString = strings[i];
            curLen = curString.length();

            for (int j = 0; j < curLen; j++) {
                diff[j] = curString.charAt(j) - 'a';
            }

            key = new StringBuilder();
            for (int j = 1; j < curLen; j++) {
                key.append((diff[j] - diff[0] + 26) % 26).append(',');
            }

            keyStr = key.toString();
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(i);
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> curAns;
        for(String k : map.keySet()) {
            curAns = new ArrayList<>();
            for(int i : map.get(k)) {
                curAns.add(strings[i]);
            }
            ans.add(curAns);
        }

        return ans;
    }
}
