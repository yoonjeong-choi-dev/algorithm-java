package topcoder.ch5_bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class FriendScoreSolution {
    /*
     * 친구 정의
     * 1) 서로 친구면 친구
     * 2) 서로 공통 친구가 있으면 친구
     * 답안
     * 0) 각 사람의 친구 리스트 계산
     * 1) 친구에 해당하는 인덱스를 HashSet 자료구조를 이용하여 중복되지 않게 저장
     * */
    public int highestScore(String[] friends) {
        int numPeople = friends.length;

        // 친구로 정의되는 사람들의 인덱스를 중복되지 않게 저장
        ArrayList<HashSet<Integer>> friendList = new ArrayList<>(numPeople);

        // 각 사람의 직접적인 친구(1번 정의) 인덱스 저장
        ArrayList<ArrayList<Integer>> directFriendList = new ArrayList<>(numPeople);

        // 각 자료구조 초기화
        for(int i=0;i<numPeople;i++){
            friendList.add(new HashSet<>());
            directFriendList.add(new ArrayList<>());
        }

        for(int i=0;i<numPeople;i++){
            for(int j=0;j<i;j++){
                // 친구인 경우
                if(friends[i].charAt(j) == 'Y') {
                    // 직접적인 친구 정보 저장
                    friendList.get(i).add(j);
                    friendList.get(j).add(i);

                    directFriendList.get(i).add(j);
                    directFriendList.get(j).add(i);
                }
            }
        }

        int maxScore = 0;
        int totalScore;

        for(int i=0;i<numPeople;i++){
            HashSet<Integer> totalFriend = friendList.get(i);
            ArrayList<Integer> friend = directFriendList.get(i);

            // 직접적이지 않은 친구들(2번 정의) 정보 저장
            for(int idx : friend) {
                ArrayList<Integer> indirect = directFriendList.get(idx);

                for(int add : indirect) {
                    if(add != i) totalFriend.add(add);
                }
            }
            totalScore = totalFriend.size();
            if(maxScore < totalScore) {
                maxScore = totalScore;
            }
        }

        return maxScore;
    }
}

public class FriendScore {
    public static void main(String[] args) {
        FriendScoreSolution sol = new FriendScoreSolution();

        String[][] friends = {
                {"NNN", "NNN", "NNN"},
                {"NYY", "YNY", "YYN"},
                {"NYNNN", "YNYNN", "NYNYN", "NNYNY", "NNNYN"},
                        {"NNNNYNNNNN",
                        "NNNNYNYYNN",
                        "NNNYYYNNNN",
                        "NNYNNNNNNN",
                        "YYYNNNNNNY",
                        "NNYNNNNNYN",
                        "NYNNNNNYNN",
                        "NYNNNNYNNN",
                        "NNNNNYNNNN",
                        "NNNNYNNNNN"},
                        {"NNNNNNNNNNNNNNY",
                        "NNNNNNNNNNNNNNN",
                        "NNNNNNNYNNNNNNN",
                        "NNNNNNNYNNNNNNY",
                        "NNNNNNNNNNNNNNY",
                        "NNNNNNNNYNNNNNN",
                        "NNNNNNNNNNNNNNN",
                        "NNYYNNNNNNNNNNN",
                        "NNNNNYNNNNNYNNN",
                        "NNNNNNNNNNNNNNY",
                        "NNNNNNNNNNNNNNN",
                        "NNNNNNNNYNNNNNN",
                        "NNNNNNNNNNNNNNN",
                        "NNNNNNNNNNNNNNN",
                        "YNNYYNNNNYNNNNN"}
        };

        int[] ans = {0, 2, 4, 8, 6};

        int numProblems = friends.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int mySol = sol.highestScore(friends[i]);
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
