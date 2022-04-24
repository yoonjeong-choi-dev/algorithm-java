package from1301to1400;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/design-underground-system/
public class Prob1396DesignUndergroundSystem {
    class UndergroundSystem {

        // id -> (station, check-in time)
        private final Map<Integer, Pair<String, Integer>> checkInTable = new HashMap<>();

        // station:station -> (total time, count)
        private final Map<String, Pair<Integer, Integer>> stationsTable = new HashMap<>();

        public UndergroundSystem() {
        }

        public void checkIn(int id, String stationName, int t) {
            checkInTable.put(id, new Pair<>(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            Pair<String, Integer> checkin = checkInTable.get(id);
            String from = checkin.getKey();
            Integer start = checkin.getValue();

            String path = getStationPath(from, stationName);
            Pair<Integer, Integer> pathInfo = stationsTable.getOrDefault(path, new Pair<>(0, 0));
            Integer totalTime = pathInfo.getKey();
            Integer totalCount = pathInfo.getValue();

            stationsTable.put(path, new Pair<>(totalTime + t - start, totalCount + 1));

            checkInTable.remove(id);
        }

        public double getAverageTime(String startStation, String endStation) {
            String path = getStationPath(startStation, endStation);
            Pair<Integer, Integer> pathInfo = stationsTable.get(path);

            Integer totalTime = pathInfo.getKey();
            Integer totalCount = pathInfo.getValue();
            return (double) totalTime / totalCount;
        }

        private String getStationPath(String from, String to) {
            return from + ":" + to;
        }
    }
}
