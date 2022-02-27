package book.ch11HashMap;

import book.utils.Profiler;
import org.jfree.data.xy.XYSeries;

import book.utils.Profiler.Timeable;

import java.util.HashMap;
import java.util.Map;

public class ProfileMapPut {
    public static void main(String[] args) {
        //profileHashMapPut();
        //profileMyHashMapPut();
        profileMyImprovedHashMapPut();
    }

    public static void profileHashMapPut() {
        Timeable timeable = new Timeable() {
            Map<String, Integer> map;

            public void setup(int n) {
                map = new HashMap<>();
            }

            public void timeMe(int n) {
                for (int i=0; i<n; i++) {
                    map.put(String.format("%10d", i), i);
                }
            }
        };
        int startN = 8000;
        int endMillis = 1000;

        String title = "HashMap put end";
        Profiler profiler = new Profiler(title, timeable);
        XYSeries xySeries = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(xySeries);
    }

    public static void profileMyHashMapPut() {
        Timeable timeable = new Timeable() {
            Map<String, Integer> map;

            public void setup(int n) {
                map = new MyHashMap<>();
            }

            public void timeMe(int n) {
                for (int i=0; i<n; i++) {
                    map.put(String.format("%10d", i), i);
                }
            }
        };
        int startN = 1000;
        int endMillis = 2000;

        String title = "MyBetterMap put end";
        Profiler profiler = new Profiler(title, timeable);
        XYSeries xySeries = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(xySeries);
    }

    public static void profileMyImprovedHashMapPut() {
        Timeable timeable = new Timeable() {
            Map<String, Integer> map;

            public void setup(int n) {
                map = new MyImprovedHashMap<>();
            }

            public void timeMe(int n) {
                for (int i=0; i<n; i++) {
                    map.put(String.format("%10d", i), i);
                }
            }
        };
        int startN = 1000;
        int endMillis = 2000;

        String title = "MyImprovedHashMap put end";
        Profiler profiler = new Profiler(title, timeable);
        XYSeries xySeries = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(xySeries);
    }
}
