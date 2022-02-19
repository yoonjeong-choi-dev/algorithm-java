package book.ch01Interface.exercise;

import java.util.ArrayList;
import java.util.List;

// book.ch01Interface.ListClientExample 에서 리스트 구현체를 바꾸는 실습
public class ListClientExample {
    private List<Integer> list;

    public ListClientExample() {
        list = new ArrayList<>();
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
