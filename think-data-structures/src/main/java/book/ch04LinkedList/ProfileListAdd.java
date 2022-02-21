package book.ch04LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import book.utils.Profiler;
import org.jfree.data.xy.XYSeries;

import book.utils.Profiler.Timeable;

public class ProfileListAdd {
    public static void main(String[] args) {
        //profileArrayListAddEnd();
        //profileArrayListAddBeginning();
        //profileLinkedListAddBeginning();
        profileLinkedListAddEnd();
    }

    public static void profileArrayListAddEnd() {
        Timeable timeable = new Timeable() {
            List<String> list;
            @Override
            public void setup(int n) {
                list = new ArrayList<>();
            }

            @Override
            public void timeMe(int n) {
                for(int i=0;i<n;i++){
                    list.add("a string");
                }
            }
        };

        String title = "ArrayList add end";
        Profiler profiler = new Profiler(title, timeable);

        int startN = 4000;
        int endMillis = 1000;

        XYSeries xySeries = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(xySeries);
    }

    // Exercise 1. profileArrayListAddBeginning
    public static void profileArrayListAddBeginning() {
        Timeable timeable = new Timeable() {
            List<String> list;
            @Override
            public void setup(int n) {
                list = new ArrayList<>();
            }

            @Override
            public void timeMe(int n) {
                for(int i=0;i<n;i++){
                    list.add(0, "a string");
                }
            }
        };

        String title = "Exercise 1. ArrayList add beginning";
        Profiler profiler = new Profiler(title, timeable);

        int startN = 4000;
        int endMillis = 10000;

        XYSeries xySeries = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(xySeries);
    }

    // Exercise 2. profileLinkedListAddBeginning
    public static void profileLinkedListAddBeginning() {
        Timeable timeable = new Timeable() {
            List<String> list;
            @Override
            public void setup(int n) {
                list = new LinkedList<>();
            }

            @Override
            public void timeMe(int n) {
                for(int i=0;i<n;i++){
                    list.add(0, "a string");
                }
            }
        };

        String title = "Exercise 2. LinkedList add beginning";
        Profiler profiler = new Profiler(title, timeable);

        int startN = 10000;
        int endMillis = 10000;

        XYSeries xySeries = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(xySeries);
    }

    // Exercise 3. profileLinkedListAddEnd
    public static void profileLinkedListAddEnd() {
        Timeable timeable = new Timeable() {
            List<String> list;
            @Override
            public void setup(int n) {
                list = new LinkedList<>();
            }

            @Override
            public void timeMe(int n) {
                for(int i=0;i<n;i++){
                    list.add("a string");
                }
            }
        };

        String title = "Exercise 3. LinkedList add end";
        Profiler profiler = new Profiler(title, timeable);

        int startN = 3000;
        int endMillis = 10000;

        XYSeries xySeries = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(xySeries);
    }
}
