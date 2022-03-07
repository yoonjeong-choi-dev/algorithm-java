package programmers.level2;

import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/92341
public class Prob11KakaoParkingFee {
    public int[] solution(int[] fees, String[] records) {
        // parkingInfo[차량번호] : 짝수 인덱스는 입차 시간, 홀수 인덱스는 출차 시간
        HashMap<String, List<Integer>> parkingInfo = new HashMap<>(records.length);

        // 기록들을 이용하여 주차 정보 업데이트
        for (String record : records) {
            String[] info = record.split(" ");
            String[] timeInfo = info[0].split(":");
            int time = Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);

            List<Integer> parking = parkingInfo.get(info[1]);
            if(parking == null) parking = new ArrayList<>();

            parking.add(time);
            parkingInfo.put(info[1], parking);
        }

        // 마지막 출차 정보가 없는 차량들에 대해서 1439(=23*60+59) 추가
        for(List<Integer> time : parkingInfo.values()) {
            if(time.size() %2 == 1) {
                time.add(1439);
            }
        }

        // 주차 정보를 이용하여 요금 계산
        HashMap<String, Integer> feeInfo = new HashMap<>(parkingInfo.size());
        for(String car : parkingInfo.keySet()) {
            List<Integer> parking = parkingInfo.get(car);
            int totalTime = 0;
            for(int i=0;i<parking.size();i+=2) {
                totalTime += parking.get(i+1) - parking.get(i);
            }

            int totalFee = fees[1];

            // 기본 시간 초과인 경우
            if(totalTime > fees[0]) {
                totalFee += (int)(Math.ceil((double) (totalTime - fees[0]) / fees[2])) * fees[3];
            }

            feeInfo.put(car, totalFee);
        }

        // 차량 번호 정렬을 위한 Comparator 객체
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.parseInt(s1) < Integer.parseInt(s2) ? -1 : 1;
            }
        };
        String[] carNames = parkingInfo.keySet().toArray(new String[0]);
        Arrays.sort(carNames, comp);

        int[] answer = new int[carNames.length];
        for(int i=0;i<answer.length;i++){
            answer[i] = feeInfo.get(carNames[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Prob11KakaoParkingFee sol = new Prob11KakaoParkingFee();

        int[][] fees = {
                {180, 5000, 10, 600}, {120, 0, 60, 591}, {1, 461, 1, 10}
        };
        String[][] records = {
                {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"},
                {"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"},
                {"00:00 1234 IN"}
        };

        int[][] ans = {
                {14600, 34400, 5000},
                {0, 591},
                {14841}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int[] mySol = sol.solution(fees[i], records[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
