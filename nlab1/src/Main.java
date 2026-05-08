import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        Consumer<String> c1 = tekst -> System.out.println("Tekst: " + tekst);
        c1.accept("Hello");

        Function<Integer, Integer> f1 = x -> {
            int wynik = x * x;
            return wynik;
        };
        System.out.println("Kwadrat liczby 5: " + f1.apply(5));

        Function<String, Integer> f2 = Integer::parseInt;
        System.out.println("Konwersja \"123\" na int: " + f2.apply("123"));

        Printer printer = new Printer();
        Consumer<String> c2 = printer::drukuj;
        c2.accept("Test");

        Supplier<Person> s1 = Person::new;
        Person p = s1.get();
    }

    static class Printer {
        public void drukuj(String tekst) {
            System.out.println("Drukuję: " + tekst);
        }
    }

    static class Person {
        public Person() {
            System.out.println("Tworzenie obiektu Person");
        }
    }
}