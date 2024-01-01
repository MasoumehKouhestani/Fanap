package ir.fanap.e3;

import java.util.ArrayList;
import java.util.List;

public class Q2 {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        list2.add(5);
        list2.add(6);

        adder(list1, list2);
        /*
         * result:
         * 6
         * 8
         * 3
         * 4
         * */
    }

    public static void adder(List<Integer> list1, List<Integer> list2) {
        int minSize = Math.min(list1.size(), list2.size());
        List<Integer> sumList = new ArrayList<>();
        for (int i = 0; i < minSize; i++) {
            sumList.add(list1.get(i) + list2.get(i));
        }
        if (list1.size() > list2.size()) sumList.addAll(list1.subList(minSize, list1.size()));
        else sumList.addAll(list2.subList(minSize - 1, list2.size() - 1));
        sumList.forEach(System.out::println);
    }
}
