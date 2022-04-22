package from1to100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/group-anagrams/
public class Prob49GroupAnagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        // hash(string) : 각 문자의 빈도수와 구분자(.)를 이용하여 문자열로 열거
        final char sep = '.';
        HashMap<String, List<String>> map = new HashMap<>();
        StringBuilder hash;
        String key;
        int[] occurs = new int[26];

        for (String str : strs) {
            // 빈도수를 저장하는 배열 초기화
            for (int i = 0; i < 26; i++) occurs[i] = 0;

            for (int i = 0; i < str.length(); i++) {
                occurs[str.charAt(i) - 'a']++;
            }

            // 해시키 생성
            hash = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                hash.append(occurs[i]);
                hash.append(sep);
            }
            key = hash.toString();

            if (!map.containsKey(key)) {
                map.put(key, new LinkedList<>());
            }
            map.get(key).add(str);
        }

        List<List<String>> ans = new ArrayList<>(map.size());
        ans.addAll(map.values());

        return ans;
    }
}
