package ir.fanap.basecodes;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class S14E {

    @SneakyThrows
    public static void main(String[] args) {
        e1();
        e2();
        e3();
        e4();
        e5();
    }

    private static void e1() {
        Dish dish1 = new Dish(DishType.MEAT, 3);
        Dish dish2 = new Dish(DishType.VEGETABLE, 2);
        Dish dish3 = new Dish(DishType.MEAT, 4);
        Dish dish4 = new Dish(DishType.FISH, 1);
        Dish dish5 = new Dish(DishType.FISH, 2);
        Dish dish6 = new Dish(DishType.VEGETABLE, 10);
        Dish dish7 = new Dish(DishType.VEGETABLE, 10);

        List<Dish> dishes = List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7);
        Map<DishType, Integer> map = dishes.stream().collect(Collectors.groupingBy(Dish::type, Collectors.summingInt(Dish::weight)));
        map.entrySet().forEach(System.out::println);
        // Result
        //VEGETABLE=22
        //FISH=3
        //MEAT=7
    }

    private static void e2() {
        Dish dish1 = new Dish(DishType.MEAT, 800);
        Dish dish2 = new Dish(DishType.VEGETABLE, 200);
        Dish dish3 = new Dish(DishType.MEAT, 900);
        Dish dish4 = new Dish(DishType.FISH, 500);
        Dish dish5 = new Dish(DishType.FISH, 600);
        Dish dish6 = new Dish(DishType.VEGETABLE, 100);
        Dish dish7 = new Dish(DishType.VEGETABLE, 100);
        Dish dish8 = new Dish(DishType.MEAT, 100);

        List<Dish> dishes = List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8);
        Map<DishType, Map<String, List<Dish>>> map = dishes.stream().collect(Collectors.groupingBy(Dish::type, Collectors.groupingBy(dish -> {
            if (dish.weight() <= 400) return "Diet";
            else if (dish.weight() <= 700) return "Normal";
            else return "Fat";
        })));
        map.entrySet().forEach(System.out::println);
        //Result
        // VEGETABLE={Diet=[Dish[type=VEGETABLE, weight=200], Dish[type=VEGETABLE, weight=100], Dish[type=VEGETABLE, weight=100]]}
        // MEAT={Diet=[Dish[type=MEAT, weight=100]], Fat=[Dish[type=MEAT, weight=800], Dish[type=MEAT, weight=900]]}
        // FISH={Normal=[Dish[type=FISH, weight=500], Dish[type=FISH, weight=600]]}
    }

    private static void e3() {
        List<Integer> list = List.of(1, 2, 3, 4);
        String str = list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        System.out.println(str);
        // Result: 1,2,3,4
    }

    private static void e4() {
        Employee e1 = new Employee("name1", 3321L);
        Employee e2 = new Employee("name2", 3323L);
        Employee e3 = new Employee("name3", 3324L);
        Employee e4 = new Employee("name4", 3326L);
        Employee e5 = new Employee("name5", 3327L);
        Employee e6 = new Employee("name6", 3328L);

        Employee e7 = new Employee("name7", 1121L);
        Employee e8 = new Employee("name8", 1122L);
        Employee e9 = new Employee("name9", 1124L);
        Employee e10 = new Employee("name10", 1126L);
        Employee e11 = new Employee("name11", 1127L);
        Employee e12 = new Employee("name12", 1128L);

        Employee e13 = new Employee("name13", 2224L);
        Employee e14 = new Employee("name14", 2223L);
        Employee e15 = new Employee("name15", 2232L);

        List<Employee> employees = List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15);

        Map<Long, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(employee -> employee.getPostalCode() / 100))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        map.entrySet().forEach(System.out::println);
        //Result
//        33=[Employee{name='name1', postalCode=3321}, Employee{name='name2', postalCode=3323}, Employee{name='name3', postalCode=3324}, Employee{name='name4', postalCode=3326}, Employee{name='name5', postalCode=3327}, Employee{name='name6', postalCode=3328}]
//        11=[Employee{name='name7', postalCode=1121}, Employee{name='name8', postalCode=1122}, Employee{name='name9', postalCode=1124}, Employee{name='name10', postalCode=1126}, Employee{name='name11', postalCode=1127}, Employee{name='name12', postalCode=1128}]
    }

    private static void e5() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        SampleClass sampleClass = new SampleClass();

        Method publicMethod2 = sampleClass.getClass().getDeclaredMethod("publicMethod2", int.class);
        System.out.println(publicMethod2.getName());
        System.out.println(publicMethod2.invoke(sampleClass, 4));

        Method privateMethod1 = sampleClass.getClass().getDeclaredMethod("privateMethod1", int.class);
        privateMethod1.setAccessible(true);
        System.out.println(privateMethod1.getName());
        System.out.println(privateMethod1.invoke(sampleClass, 4));

        Field privateField2 = sampleClass.getClass().getDeclaredField("privateField2");
        privateField2.setAccessible(true);
        System.out.println(privateField2.getName());
        System.out.println(privateField2.getInt(sampleClass));
        privateField2.set(sampleClass, 10);
        System.out.println(privateField2.getInt(sampleClass));
    }
}

class SampleClass {

    public int publicField1 = 2;
    public int publicField2 = 3;
    private int privateField1 = 5;
    private int privateField2 = 7;

    public int publicMethod1(int a) {
        return a * a;
    }

    public int publicMethod2(int a) {
        return a + 10;
    }

    private int privateMethod1(int b) {
        return b * b * b;
    }

    private int privateMethod2(int b) {
        return 20 + b;
    }
}

class Employee {
    private String name;
    private Long postalCode;

    public Employee(String name, Long postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
