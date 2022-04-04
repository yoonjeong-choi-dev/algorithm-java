package programmers.level3;


// https://programmers.co.kr/learn/courses/30/lessons/17676
public class Prob18KakaoThanksgivingTraffic {
    public int solution(String[] lines) {
        int numRequests = lines.length;

        // requestInfo[i] : i번째 요청의 시작 및 완료 시간
        int[][] requestInfo = new int[numRequests][2];


        for (int i = 0; i < numRequests; i++) {
            String[] parsed = lines[i].split(" ");

            // 날짜 데이터는 필요가 없음
            String start = parsed[1];
            String duration = parsed[2];

            // 시작 시간 파싱 : hh:mm:ss.sss 형태
            int endTime = 0;
            String[] startParsed = start.split(":");
            endTime += Integer.parseInt(startParsed[0]) * 60 * 60 * 1000;
            endTime += Integer.parseInt(startParsed[1]) * 60 * 1000;
            endTime += (int) (Double.parseDouble(startParsed[2]) * 1000);

            // 완료 시간 파싱
            int startTime = endTime - (int) (Double.parseDouble(duration.substring(0, duration.length() - 1)) * 1000) + 1;
            requestInfo[i][0] = startTime;
            requestInfo[i][1] = endTime;
        }


        // 전체 3000ms, 요청 수는 2000 => 전체 탐색 가능
        int answer = 0;

        int start, end, numHandled;
        for(int i=0;i<numRequests;i++){
            // i번째 요청을 처음으로 처리하는 경우
            start = requestInfo[i][1];
            end = start + 999;
            numHandled = 0;

            // i번째 요청부터만 탐색하면 됨
            for(int j=i;j<numRequests;j++){
                // 현재 구간 오른쪽에 있는 경우 i.e 구간 이후에 요청하는 경우 => 제외
                if(requestInfo[j][0] > end) continue;

                numHandled++;
            }

            answer = Math.max(answer, numHandled);
        }

        return answer;
    }

    public static void main(String[] args) {
        Prob18KakaoThanksgivingTraffic sol = new Prob18KakaoThanksgivingTraffic();

        String[][] lines = {
                {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"},
                {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"},
                {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"}
        };

        int[] ans = {1, 2, 7};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(lines[i]);
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
