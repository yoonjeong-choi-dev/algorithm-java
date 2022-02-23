package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/42862
public class Prob25SportUniform {
    public int solution(int n, int[] lost, int[] reserve) {
        // 각 학생의 운동복 개수
        int[] numUniforms = new int[n];
        for (int i = 0; i < n; i++) numUniforms[i] = 1;

        // 여분의 운동복을 가진 학생에 대해서 도둑맞을 수 있음
        // => 여분의 운동복을 가진 학생 -> 도둑맞은 학생 순서로 정보 업데이트 필요
        for (int i : reserve) numUniforms[i - 1]++;
        for (int i : lost) numUniforms[i - 1]--;


        // 현재 학생이 양 옆의 학생에게 빌림
        // 앞의 번호가 우선 순위 : 뒤 번호는 이후 여유 있는 학생이 빌려줄 것이라고 가정(탐욕법)
        for (int i = 0; i < n; i++) {
            // 운동복이 있으면 pass
            if (numUniforms[i] > 0) continue;

            if (i > 0 && numUniforms[i - 1] == 2) {
                // 앞 번호에게 빌림
                numUniforms[i] = 1;
                numUniforms[i - 1] = 1;
            } else if (i < n - 1 && numUniforms[i + 1] == 2) {
                // 뒷 번호에게 빌림
                numUniforms[i] = 1;
                numUniforms[i + 1] = 1;
            }
        }

        // 운동복 개수가 1이상인 학생들의 인원 수
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (numUniforms[i] > 0) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob25SportUniform sol = new Prob25SportUniform();

        int[] nums = {5, 5, 3};
        int[][] lost = {{2, 4}, {2, 4}, {3}};
        int[][] reserve = {{1, 3, 5}, {3}, {1}};
        int[] ans = {5, 4, 2};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int mySol = sol.solution(nums[i], lost[i], reserve[i]);
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
