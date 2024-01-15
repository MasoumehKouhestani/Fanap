package ir.fanap.basecodes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

enum DishType {
    MEAT(1), VEGETABLE(2), FISH(3);

    DishType(int code) {
    }
}

public class Session13Exercises {

    public static void main(String[] args) {
        // Exercise 1
        List<Integer> numbers = List.of(1, 2, 3, 2, 3, 4, 5);
        List<Integer> list = numbers.stream()
                .distinct()
                .toList();
        list.forEach(System.out::println); // Result: 1, 2, 3, 4, 5

        // Exercise 2
        Dish dish1 = new Dish(DishType.MEAT, 3);
        Dish dish2 = new Dish(DishType.VEGETABLE, 2);
        Dish dish3 = new Dish(DishType.MEAT, 4);
        Dish dish4 = new Dish(DishType.FISH, 1);
        Dish dish5 = new Dish(DishType.FISH, 2);

        List<Dish> dishes = List.of(dish1, dish2, dish3, dish4, dish5);
        List<Dish> limitedDished = dishes.stream()
                .sorted(Comparator.comparing(Dish::type))
                .limit(2)
                .toList();

        limitedDished.forEach(d -> System.out.println(d.type()));

        // Exercise 3
        List<Integer> numbers2 = List.of(1, 2, 3, 4, 5);
        List<Integer> squaredNumbers = numbers2.stream()
                .map(n -> n * n)
                .toList();
        squaredNumbers.forEach(System.out::println); // Result: 1, 4, 9, 16, 25

        // Exercise 4
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(3, 4);
        List<Pair> pairs = list1.stream()
                .flatMap(number1 -> list2.stream()
                        .filter(number2 -> (number1 + number2) % 3 == 0)
                        .map(number2 -> new Pair(number1, number2)))
                .toList();

        pairs.forEach(System.out::println);
        //Result
        //Pair[number1=2, number2=4]
        //Pair[number1=3, number2=3]


        // Exercise 5
        Trader trader1 = new Trader("Trader1", "City1");
        Trader trader2 = new Trader("Trader2", "City2");
        Trader trader3 = new Trader("Trader3", "City3");
        Trader trader4 = new Trader("Trader4", "City4");

        Transaction transaction1 = new Transaction(trader1, 150.000, 2020);
        Transaction transaction2 = new Transaction(trader2, 150.500, 2015);
        Transaction transaction3 = new Transaction(trader3, 180.200, 2023);
        Transaction transaction4 = new Transaction(trader4, 120.100, 2019);

        List<Transaction> transactions = List.of(transaction1, transaction2, transaction3, transaction4);
        List<Transaction> transactionAfter2020 = transactions.stream()
                .filter(t -> t.year() >= 2020)
                .sorted(Comparator.comparing(Transaction::year))
                .toList();
        transactionAfter2020.forEach(System.out::println);
        // Result:
        // Transaction[trader=Trader[name=Trader1, city=City1], amount=150.0, year=2020]
        // Transaction[trader=Trader[name=Trader3, city=City3], amount=180.2, year=2023]

        // Exercise 6
        Transaction minTransaction = transactions.stream()
                .sorted(Comparator.comparing(Transaction::amount))
                .limit(1)
                .toList()
                .get(0);
        System.out.println(minTransaction);
        //Result
        //Transaction[trader=Trader[name=Trader4, city=City4], amount=120.1, year=2019]

        // Exercise 7
        List<Integer> sampleList = List.of(1, 2);
        Integer[] array = new Integer[]{1, 2};
        Stream<Integer> stream1 = sampleList.stream();
        Stream<Integer> stream2 = Stream.of(1, 2);
        Stream<Integer> stream3 = Arrays.stream(array);
        Stream<Integer> stream4 = Stream.empty();
        Stream.Builder<Integer> builder = Stream.builder();
        Stream<Integer> stream5 = builder.build();
        Stream<Integer> stream6 = StreamSupport.stream(sampleList.spliterator(), false);
        Stream<Double> stream7 = Stream.generate(Math::random);

    }
}

record Dish(DishType type, int weight) {
}

record Pair(int number1, int number2) {}

record Trader(String name, String city) {}

record Transaction(Trader trader, Double amount, int year) {}
