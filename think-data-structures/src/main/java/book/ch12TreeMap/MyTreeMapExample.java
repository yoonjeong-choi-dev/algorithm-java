package book.ch12TreeMap;

import java.sql.SQLOutput;

public class MyTreeMapExample {
    public static void main(String[] args) {
        MyTreeMap<String, Integer> map= new MyTreeMap<>();
        MyTreeMap<String, Integer>.Node node08 = map.makeNode("08", 8);

        MyTreeMap<String, Integer>.Node node03 = map.makeNode("03", 3);
        MyTreeMap<String, Integer>.Node node10 = map.makeNode("10", 10);
        node08.left = node03;
        node08.right = node10;

        MyTreeMap<String, Integer>.Node node01 = map.makeNode("01", 1);
        MyTreeMap<String, Integer>.Node node06 = map.makeNode("06", 6);
        MyTreeMap<String, Integer>.Node node14 = map.makeNode("14", 14);
        node03.left = node01;
        node03.right = node06;
        node10.right = node14;

        MyTreeMap<String, Integer>.Node node04 = map.makeNode("04", 4);
        MyTreeMap<String, Integer>.Node node07 = map.makeNode("07", 7);
        MyTreeMap<String, Integer>.Node node13 = map.makeNode("13", 13);
        node06.left = node04;
        node06.right = node07;
        node14.left = node13;

        map.setTree(node08, 9);

        System.out.println("Before Removed");
        map.printLevelOrder();

        System.out.println("After remove 13");
        map.remove("13");
        map.printLevelOrder();

        System.out.println("After remove 10");
        map.remove("10");
        map.printLevelOrder();

        System.out.println("After remove 3");
        map.remove("03");
        map.printLevelOrder();
    }
}
