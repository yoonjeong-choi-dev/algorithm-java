package programmers.level2;

import java.util.ArrayList;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/17683
public class Prob14KakaoPrevSong {

    // 문자열 비교를 쉽게 하기 위해 플랫 곡조를 문자로 변경
    static final String[] flats = {"C#", "D#", "E#", "F#", "G#", "A#"};
    static final String[] converted = {"c", "d", "e", "f", "g", "a"};

    public String solution(String m, String[] musicinfos) {
        // 플랫 변형
        m = covert(m);

        // 곡 정보 저장
        // 0 : 제목, 1 : 재생된 음
        List<String[]> songs = new ArrayList<>(musicinfos.length);

        for (String info : musicinfos) {
            String[] parsed = info.split(",");
            String title = parsed[2];
            String score = covert(parsed[3]);

            // 재생 시간 계산
            String[] startTime = parsed[0].split(":");
            String[] endTime = parsed[1].split(":");

            int time = (60 * Integer.parseInt(endTime[0]) + Integer.parseInt(endTime[1]))
                    - (60 * Integer.parseInt(startTime[0]) + Integer.parseInt(startTime[1]));

            // 재생된 음에 대한 문자열
            StringBuilder played = new StringBuilder(time);
            int scoreLen = score.length();
            for (int i = 0; i < time; i++) {
                played.append(score.charAt(i % scoreLen));
            }

            songs.add(new String[]{title, played.toString()});
        }

        String answer = "";
        int songTime = 0;
        for (String[] song : songs) {
            // 기억한 멜로디를 포함한 경우
            if (song[1].contains(m)) {
                // 재생 시간이 더 긴 음악 선택
                if (songTime < song[1].length()) {
                    answer = song[0];
                    songTime = song[1].length();
                }
            }
        }

        return answer.isEmpty() ? "(None)" : answer;
    }

    private String covert(String s) {
        String ret = s;
        for (int i = 0; i < flats.length; i++) {
            ret = ret.replaceAll(flats[i], converted[i]);
        }
        return ret;
    }

    public static void main(String[] args) {
        Prob14KakaoPrevSong sol = new Prob14KakaoPrevSong();

        String[] musics = {"ABCDEFG", "CC#BCC#BCC#BCC#B", "ABC"};
        String[][] info = {
                {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"},
                {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"},
                {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}
        };

        String[] ans = {"HELLO", "FOO", "WORLD"};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            String mySol = sol.solution(musics[i], info[i]);
            if (ans[i].equals(mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
