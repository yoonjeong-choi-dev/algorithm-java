package from101to200;

import java.util.*;

// https://leetcode.com/problems/repeated-dna-sequences/
public class Prob187RepeatedDNASequence {

    // 해시키(문자열->숫자)를 만들기 위한 숫자 저장
    // 해시키는 문자열을 4진수로 만들어서 저장 : 길이가 10이므로 4^10 = 1048576 이하로 표현 가능
    static final Map<Character, Integer> nucleotides = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();

        if (len < 10) return new ArrayList<>();

        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = nucleotides.get(s.charAt(i));
        }

        // (해시키, 문자열) 및 (해시키, 빈도수) 저장
        HashMap<Integer, String> hashKey = new HashMap<>();
        HashMap<Integer, Integer> occurMap = new HashMap<>();

        // 처음 시퀀스 저장
        int curKey = 0, curBit = 1;
        for (int i = 0; i < 10; i++) {
            curKey += nums[i] * curBit;
            curBit *= 4;
        }

        hashKey.put(curKey, s.substring(0, 10));
        occurMap.put(curKey, 1);

        curBit = (int) Math.pow(4, 9);
        for (int i = 1; i <= s.length() - 10; i++) {
            curKey = curKey / 4 + nums[i + 9] * curBit;
            if (!hashKey.containsKey(curKey)) hashKey.put(curKey, s.substring(i, i + 10));
            occurMap.put(curKey, occurMap.getOrDefault(curKey, 0) + 1);
        }

        List<String> ans = new LinkedList<>();
        for (Integer key : hashKey.keySet()) {
            if (occurMap.get(key) > 1) {
                ans.add(hashKey.get(key));
            }
        }
        return ans;
    }
}
