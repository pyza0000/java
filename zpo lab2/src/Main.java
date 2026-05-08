import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("Zadanie 3 - min()");
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(4);
        numbers.add(25);
        numbers.add(1);
        numbers.add(18);

        Optional<Integer> minValue = numbers.stream().min(Integer::compare);
        minValue.ifPresent(min -> System.out.println("Najmniejsza liczba: " + min));


        System.out.println("\nZadanie 4 - filter()");
        ArrayList<Integer> numbers2 = new ArrayList<>();
        numbers2.add(3);
        numbers2.add(8);
        numbers2.add(11);
        numbers2.add(14);
        numbers2.add(20);

        System.out.println("Liczby parzyste:");
        numbers2.stream()
                .filter(x -> x % 2 == 0)
                .forEach(x -> System.out.println(x));


        System.out.println("\nZadanie 5 - sorted()");
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Kuba", 22));
        people.add(new Person("Adam", 19));
        people.add(new Person("Kuba", 18));
        people.add(new Person("Bartek", 25));

        System.out.println("Osoby posortowane:");
        people.stream()
                .sorted(Comparator.comparing(Person::getNick).thenComparing(Person::getAge))
                .forEach(System.out::println);


        System.out.println("\nZadanie 6 - map()");
        ArrayList<PunktXYZ> points3D = new ArrayList<>();
        points3D.add(new PunktXYZ(8, 15, 3));
        points3D.add(new PunktXYZ(1, 9, 14));
        points3D.add(new PunktXYZ(6, 2, 11));
        points3D.add(new PunktXYZ(13, 7, 5));

        List<PunktXY> points2D = points3D.stream()
                .map(p -> new PunktXY(p.getX(), p.getY()))
                .collect(Collectors.toList());

        for (PunktXY p : points2D) {
            System.out.println("x = " + p.getX() + ", y = " + p.getY());
        }


        System.out.println("\nZadanie 7 - flatMap()");
        List<Person> eaglesMembers = Arrays.asList(
                new Person("Karolina"),
                new Person("Monika"),
                new Person("Julia"),
                new Person("Sandra")
        );

        List<Person> bikersMembers = Arrays.asList(
                new Person("Marek"),
                new Person("Wojtek"),
                new Person("Igor"),
                new Person("Patryk")
        );

        Group eagles = new Group("Eagles", eaglesMembers);
        Group bikers = new Group("Bikers", bikersMembers);

        List<Group> groups = Arrays.asList(eagles, bikers);

        List<Person> allMembers = groups.stream()
                .flatMap(group -> group.getMembers().stream())
                .collect(Collectors.toList());

        allMembers.forEach(System.out::println);


        System.out.println("\nZadanie 8 - reduce()");
        ArrayList<Integer> liczby = new ArrayList<>();
        liczby.add(7);
        liczby.add(3);
        liczby.add(2);
        liczby.add(2);

        int suma = liczby.stream()
                .reduce(0, (a, b) -> a + b);

        int iloczyn = liczby.stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println("Suma: " + suma);
        System.out.println("Iloczyn: " + iloczyn);


        System.out.println("\nZadanie 9 - parallelStream()");
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 1_000_000; i++) {
            list.add(UUID.randomUUID().toString());
        }

        long start = System.currentTimeMillis();
        list.stream().sorted().collect(Collectors.toList());
        long stop = System.currentTimeMillis();
        System.out.println("Czas sekwencyjny: " + (stop - start) + " ms");

        start = System.currentTimeMillis();
        list.parallelStream().sorted().collect(Collectors.toList());
        stop = System.currentTimeMillis();
        System.out.println("Czas równoległy: " + (stop - start) + " ms");
    }
}


class Person {
    private String nick;
    private int age;

    public Person(String nick, int age) {
        this.nick = nick;
        this.age = age;
    }

    public Person(String nick) {
        this.nick = nick;
        this.age = 0;
    }

    public String getNick() {
        return nick;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        if (age == 0) {
            return "Person{nick='" + nick + "'}";
        }
        return "Person{nick='" + nick + "', age=" + age + "}";
    }
}


class Group {
    private String groupName;
    private List<Person> members;

    public Group(String groupName, List<Person> members) {
        this.groupName = groupName;
        this.members = members;
    }

    public List<Person> getMembers() {
        return members;
    }
}


class PunktXY {
    private int x;
    private int y;

    public PunktXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


class PunktXYZ {
    private int x;
    private int y;
    private int z;

    public PunktXYZ(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}