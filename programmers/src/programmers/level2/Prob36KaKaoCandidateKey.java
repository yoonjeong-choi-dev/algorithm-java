package programmers.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/42890
public class Prob36KaKaoCandidateKey {
    private List<List<Integer>> uniqueCandidates;
    private String[][] relation;
    int rows, cols;

    public int solution(String[][] relation) {
        this.relation = relation;
        rows = relation.length;
        cols = relation[0].length;

        // 유일성만을 고려한 후보키 : 최대 2^8 = 256
        uniqueCandidates = new ArrayList<>((int) Math.pow(2, cols));

        List<Integer> candidate = new ArrayList<>(cols);
        findUniqueCandidates(0, candidate);

        int answer = 0;
        for (int i = 0; i < uniqueCandidates.size(); i++) {
            if (hasMinimality(i)) {
                answer++;
            }
        }
        return answer;
    }

    private void findUniqueCandidates(int curCol, List<Integer> candidate) {
        if (curCol == cols) {
            if (!candidate.isEmpty() && hasUniqueness(candidate)) {
                uniqueCandidates.add(new LinkedList<>(candidate));


            }
            return;
        }

        // 현재 필드 추가하지 않고 검색
        findUniqueCandidates(curCol + 1, candidate);

        // 현재 필드를 포함하여 이후 필드 검색
        candidate.add(curCol);
        findUniqueCandidates(curCol + 1, candidate);
        candidate.remove(candidate.size() - 1);

    }

    private boolean hasUniqueness(List<Integer> candidate) {
        boolean isSame;
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < rows; j++) {
                isSame = true;

                for (int idx : candidate) {
                    if (!relation[i][idx].equals(relation[j][idx])) {
                        isSame = false;
                        break;
                    }
                }

                if (isSame) return false;
            }
        }
        return true;
    }

    private boolean hasMinimality(int idx) {
        List<Integer> curCandidate = uniqueCandidates.get(idx);
        boolean isMinimal = true;

        int removed;
        for (int i = 0; i < curCandidate.size(); i++) {
            // 최소성 검사 : 구성하는 키 중 하나를 제거 했을 때도 유일성이 유지되면 최소성 X
            removed = curCandidate.remove(i);
            if (hasUniqueness(curCandidate)) {
                isMinimal = false;
                curCandidate.add(i, removed);
                break;
            }
            curCandidate.add(i, removed);
        }

        return isMinimal;
    }

    public static void main(String[] args) {
        Prob36KaKaoCandidateKey sol = new Prob36KaKaoCandidateKey();

        String[][][] relation = {
                {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}},
                {{"a", "1", "aaa", "c", "ng"}, {"a", "1", "bbb", "e", "g"}, {"c", "1", "aaa", "d", "ng"}, {"d", "2", "bbb", "d", "ng"}}
        };

        int[] ans = {2, 5};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(relation[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
