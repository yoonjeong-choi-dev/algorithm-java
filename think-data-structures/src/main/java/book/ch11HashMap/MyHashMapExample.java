package book.ch11HashMap;

public class MyHashMapExample {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        for(int i=0;i<10;i++){
            map.put("Key"+i, i);
            System.out.printf("Current step : %d\n", i);
            System.out.println(map);
        }
    }
}
