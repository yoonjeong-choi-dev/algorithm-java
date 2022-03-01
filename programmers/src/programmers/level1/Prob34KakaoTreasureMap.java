package programmers.level1;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/17681
public class Prob34KakaoTreasureMap {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] map1 = new String[n];
        String[] map2 = new String[n];

        for(int i=0;i<n;i++){
            map1[i] = decryptInt(n, arr1[i]);
            map2[i] = decryptInt(n, arr2[i]);
        }

        String[] answer = new String[n];
        for(int i=0;i<n;i++){
            StringBuilder str = new StringBuilder(n);
            for(int j=0;j<n;j++){
                if(map1[i].charAt(j) == ' ' && map2[i].charAt(j) == ' ') {
                    str.append(' ');
                } else {
                    str.append('#');
                }
            }

            answer[i] = str.toString();
        }
        return answer;
    }

    private String decryptInt(int size, int number) {
        StringBuilder ans = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            if(number%2==1) {
                ans.insert(0, '#');
            } else {
                ans.insert(0, ' ');
            }

            number /= 2;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Prob34KakaoTreasureMap sol = new Prob34KakaoTreasureMap();

        int[] n = {5, 6};
        int[][] arr1 = {{9, 20, 28, 18, 11}, {46, 33, 33, 22, 31, 50}};
        int[][] arr2 = {{30, 1, 21, 17, 28}, {27, 56, 19, 14, 14, 10}};

        String[][] ans = {{"#####", "# # #", "### #", "#  ##", "#####"}, {"######", "###  #", "##  ##", " #### ", " #####", "### # "}};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d\n", i);
            String[] mySol = sol.solution(n[i], arr1[i], arr2[i]);
            if (Arrays.deepEquals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
