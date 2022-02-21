package book.ch03ArrayList.exercise;

public class Example {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for(int i=0;i<10;i++){
            myLinkedList.add(i+1);
        }
        System.out.println(myLinkedList);


        System.out.println("5 idx : " + myLinkedList.indexOf(5));

        MyLinkedList<Integer> sub = (MyLinkedList<Integer>) myLinkedList.subList(2,5);
        System.out.println(sub);
    }
}
