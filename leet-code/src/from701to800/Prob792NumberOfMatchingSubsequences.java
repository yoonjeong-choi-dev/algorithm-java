package from701to800;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/number-of-matching-subsequences/
public class Prob792NumberOfMatchingSubsequences {

    class Node {
        int index;
        String word;

        Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }

    public int numMatchingSubseq(String s, String[] words) {
        // s and words[i] consist of only lowercase English letters.
        List<List<Node>> heads = new ArrayList<>();
        for (int i = 0; i < 26; i++) heads.add(new ArrayList<>());

        // 단어의 시작 문자를 기준으로 저장
        for (String word : words) {
            heads.get(word.charAt(0) - 'a').add(new Node(word, 0));
        }

        int ans = 0;

        // 문자열의 각 문자에 대해서 탐색
        List<Node> curHead;
        int curIdx;
        for (char c : s.toCharArray()) {
            // 문자 삭제할지 여부 체크
            curIdx = c - 'a';

            // 현재 문자 이후의 문자열에 대한 새로운 문제로 변경
            // i.e 현재 문자 이후에 대해서 정답 후보들만 다시 heads 에 저장
            curHead = heads.get(curIdx);
            heads.set(curIdx, new ArrayList<>());

            // 현재 문자로 시작하는 단어들 탐색
            for (Node node : curHead) {
                // 현재 문자 다음으로 이동하기 위해 단어의 현재 위치 +1
                node.index++;

                // 끝까지 온 경우 => 문자열 s의 뒷부분을 삭제하면 되므로 끝 => 정답 +1
                if (node.index == node.word.length()) ans++;
                else {
                    // 끝까지 오지 않은 경우 : 단어의 현재 위치에 있는 문자 이용해서 head 업데이트
                    heads.get(node.word.charAt(node.index) - 'a').add(node);
                }
            }

            // GC
            curHead.clear();
        }

        return ans;
    }
}
