package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/49994
public class Prob34VisitedLength {
    public int solution(String dirs) {
        // from <-> to 에 대해서 지나간 여부
        boolean[][] isVisited = new boolean[121][121];

        int prevX = 5, prevY = 5;
        int nextX = 0, nextY = 0;
        int prev, next;

        int answer = 0;
        for (int i = 0; i < dirs.length(); i++) {
            switch (dirs.charAt(i)) {
                case 'U':
                    nextY = prevY - 1;
                    nextX = prevX;
                    break;
                case 'D':
                    nextY = prevY + 1;
                    nextX = prevX;
                    break;
                case 'L':
                    nextX = prevX - 1;
                    nextY = prevY;
                    break;
                case 'R':
                    nextX = prevX + 1;
                    nextY = prevY;
                    break;
            }

            // 범우 밖인 경우 무시
            if (nextX < 0 || nextX > 10 || nextY < 0 || nextY > 10) {
                continue;
            }

            prev = getTotalIndex(prevX, prevY);
            next = getTotalIndex(nextX, nextY);

            // 지나가지 않은 경우 업데이트
            if(!isVisited[prev][next]) {
                // from -> to 및 to -> from 모두 업데이트필요
                isVisited[prev][next] = true;
                isVisited[next][prev] = true;

                answer++;
            }


            prevX = nextX;
            prevY = nextY;
        }

        return answer;
    }

    private int getTotalIndex(int x, int y) {
        return y * 11 + x;
    }

    public static void main(String[] args) {
        Prob34VisitedLength sol = new Prob34VisitedLength();
        String[] dirs = {"ULURRDLLU", "LULLLLLLU"};

        int[] ans = {7, 7};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(dirs[i]);
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
