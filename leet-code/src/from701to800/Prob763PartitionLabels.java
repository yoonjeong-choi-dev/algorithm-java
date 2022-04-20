package from701to800;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/partition-labels/
public class Prob763PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        int len = s.length();

        // 문자가 마지막으로 나온 위치 저장
        int[] lastIdx = new int[26];
        for (int i = 0; i < len; i++) {
            lastIdx[s.charAt(i) - 'a'] = i;
        }

        List<Integer> ans = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < len; i++) {
            // [start:end] 파티션을 위해 현재 문자가 포함된 파티션의 끝 인덱스 탐색
            end = Math.max(end, lastIdx[s.charAt(i) - 'a']);

            // 현재 문자가 마지막으로 나온 경우 => 파티션 가능
            if (end == i) {
                ans.add(end - start + 1);
                start = end + 1;
            }
        }

        return ans;
    }
}
