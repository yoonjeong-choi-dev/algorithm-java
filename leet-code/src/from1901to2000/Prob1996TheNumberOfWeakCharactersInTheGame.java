package from1901to2000;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/
public class Prob1996TheNumberOfWeakCharactersInTheGame {
    public int numberOfWeakCharacters(int[][] properties) {
        // Sort : 공격에 대해서 내림차순. 공격력이 같은 경우는 방어력에 대해서 오름차순
        // => attack[i] >= attack[i+1], defense[i] <= defense[i+1] when attack[i] == attack[i+1]
        Arrays.sort(properties, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]) return o2[0] - o1[0];
                else return o1[1] - o2[1];
            }
        });

        // 공격에 대해서 내림차순으로 정렬하였으므로 이전 사람들의 공격력은 자기 자신보다 크거나 같음
        // 1) 공격력이 큰 경우 : 방어력만 비교하면 됨
        // 2) 공격력이 같은 경우 : 방어력에 대해 오름차순으로 정렬하였으므로, 공격력이 같은 사람과 비교되지 않음
        // => curDefense 은 자신과 같은 공격력을 가진 사람의 방어력 이상 값을 가짐
        int ans = 0;

        // 지금까지 탐색한 방어력들 중 가장 큰 값
        int curDefense = 0;
        for(int[] prop : properties) {
            if(prop[1] < curDefense) ans++;

            curDefense = Math.max(curDefense, prop[1]);
        }
        return ans;
    }
}
