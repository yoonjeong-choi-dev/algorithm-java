package from1101to1200;

import java.util.*;

// https://leetcode.com/problems/analyze-user-website-visit-pattern/
public class Prob1152AnalyzeUserWebsiteVisitPattern {
    class Pattern implements Comparable<Pattern> {
        String[] websites;

        Pattern(String a, String b, String c) {
            websites = new String[]{a, b, c};
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pattern that = (Pattern) o;
            return Arrays.equals(websites, that.websites);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(websites);
        }

        @Override
        public String toString() {
            return Arrays.toString(websites);
        }

        @Override
        public int compareTo(Pattern o) {
            int curComp;
            for (int i = 0; i < websites.length; i++) {
                curComp = websites[i].compareTo(o.websites[i]);
                if (curComp != 0) return curComp;
            }
            return 0;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int numLogs = username.length;
        Integer[] index = new Integer[numLogs];
        for (int i = 0; i < numLogs; i++) index[i] = i;
        Arrays.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(timestamp[o1], timestamp[o2]);
            }
        });

        Map<String, List<Integer>> userToVisit = new HashMap<>();
        for (int idx : index) {
            if (!userToVisit.containsKey(username[idx])) userToVisit.put(username[idx], new ArrayList<>());
            userToVisit.get(username[idx]).add(idx);
        }

        // Get all possible pattern
        Map<Pattern, Integer> counters = new HashMap<>();
        Pattern bestPattern = null;
        int bestNum = 0, curNum;
        for (String user : userToVisit.keySet()) {
            List<Integer> visits = userToVisit.get(user);
            int numVisit = visits.size();
            if (numVisit < 3) continue;

            Set<Pattern> curPattern = new HashSet<>();

            for (int i = 0; i < numVisit - 2; i++) {
                for (int j = i + 1; j < numVisit - 1; j++) {
                    for (int k = j + 1; k < numVisit; k++) {
                        curPattern.add(new Pattern(website[visits.get(i)], website[visits.get(j)], website[visits.get(k)]));
                    }
                }
            }

            for (Pattern cur : curPattern) {
                counters.put(cur, counters.getOrDefault(cur, 0) + 1);

                curNum = counters.get(cur);
                if (bestNum < curNum) {
                    bestNum = curNum;
                    bestPattern = cur;
                } else if(bestNum == curNum && cur.compareTo(bestPattern) < 0) {
                    bestPattern = cur;
                }
            }

            System.out.printf("%s - %s\n", user, curPattern);
        }

        List<String> ans = new ArrayList<>();
        for (String web : bestPattern.websites) ans.add(web);
        return ans;
    }
}
