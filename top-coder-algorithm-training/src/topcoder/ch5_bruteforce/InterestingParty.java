package topcoder.ch5_bruteforce;

import java.util.Arrays;
import java.util.HashMap;

class InterestingPartySolution {
    public int bestInvitation(String[] first, String[] second) {
        int totalNum = first.length;

        // 관심사에 대한 인원수
        HashMap<String, Integer> interesting = new HashMap<String, Integer>();
        for (int i = 0; i < totalNum; i++) {
            // 각 사람의 관심사 2개는 서로 다르다고 가정
            addInteresting(interesting, first[i]);
            addInteresting(interesting, second[i]);
        }

        // 관심사에 대한 가장 많은 인원수 초대 가능
        int ret = 0;
        for (Integer val : interesting.values()) {
            ret = Math.max(ret, val);
        }

        return ret;
    }

    private void addInteresting(HashMap<String, Integer> map, String interest) {
        int count = map.containsKey(interest) ? map.get(interest) + 1 : 1;
        map.put(interest, count);
    }
}

public class InterestingParty {
    public static void main(String[] args) {
        InterestingPartySolution sol = new InterestingPartySolution();

        String[][] firsts = {
                {"fishing", "gardening", "swimming", "fishing"},
                {"variety", "diversity", "loquacity", "courtesy"},
                {"snakes", "programming", "cobra", "monty"}
        };
        String[][] seconds = {
                {"hunting", "fishing", "fishing", "biting"},
                {"talking", "speaking", "discussion", "meeting"},
                {"python", "python", "anaconda", "python"}
        };
        int[] ans = {4, 1, 3};

        int numProblems = firsts.length;
        for (int i = 0; i < numProblems; i++) {
            int mySol = sol.bestInvitation(firsts[i], seconds[i]);
            System.out.printf("Problem %d : ", i);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
