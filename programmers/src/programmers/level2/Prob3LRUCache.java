package programmers.level2;

import java.util.LinkedList;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/17680

/*
* LRU
* - 캐시에 공간이 부족할 때 가장 오랫동안 사용하지 않은 항목을 제거하고 새로운 데이터를 최신 캐시로 업데이트
* - 가장 최근에 접근한 캐시 데이터는 최신 캐시로 업데이트
* */
public class Prob3LRUCache {

    public int solution(int cacheSize, String[] cities) {
        // Edge Case : 캐시가 없는 경우 => 항상 cache miss
        if (cacheSize == 0) return cities.length * 5;

        int numCity = cities.length;

        // 대소문자 구분 x
        for(int i=0;i<numCity;i++){
            cities[i] = cities[i].toLowerCase();
        }

        // 캐시 : Linked List => head 최신, tail 마지막
        List<String> cache = new LinkedList<>();

        int answer = 0;
        int cacheIdx;
        for(String city : cities) {
            cacheIdx = cache.indexOf(city);

            if(cacheIdx != -1) {
                // 캐시에 있는 경우
                answer += 1;

                // 해당 데이터 업데이트
                cache.remove(cacheIdx);
                cache.add(0, city);
            } else {
                // 캐시에 없는 경우
                answer += 5;

                if(cache.size() == cacheSize) {
                    cache.remove(cacheSize-1);
                }
                cache.add(0, city);
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        Prob3LRUCache sol = new Prob3LRUCache();

        int[] size = {3, 3, 2, 5, 2, 0};
        String[][] cities = {
                {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"},
                {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"},
                {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
                {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
                {"Jeju", "Pangyo", "NewYork", "newyork"},
                {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}
        };
        int[] ans = {50, 21, 60, 52, 16, 25};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d\n", i);
            int mySol = sol.solution(size[i], cities[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }

}
