package book.ch01Interface;

import java.util.LinkedList;
import java.util.List;

public class ListClientExample {
    private List<Integer> list;

    public ListClientExample() {
        list = new LinkedList<>();
    }

    public List<Integer> getList() {
        return list;
    }

    public static void main(String[] args) {
        ListClientExample ex = new ListClientExample();
        List<Integer> list = ex.getList();
        System.out.println(list);
    }
}
