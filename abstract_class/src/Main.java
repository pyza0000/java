// Klasa abstrakcyjna Zwierze
abstract class Zwierze {
    String nazwa;

    // Konstruktor
    public Zwierze(String nazwa) {
        this.nazwa = nazwa;
    }

    // Metoda abstrakcyjna
    public abstract void wydajDzwiek();
}

// Klasa Pies, dziedziczy po Zwierze
class Pies extends Zwierze {

    public Pies(String nazwa) {
        super(nazwa);
    }

    @Override
    public void wydajDzwiek() {
        System.out.println(nazwa + " mówi: Hau hau!");
    }
}

// Klasa Kot, dziedziczy po Zwierze
class Kot extends Zwierze {

    public Kot(String nazwa) {
        super(nazwa);
    }

    @Override
    public void wydajDzwiek() {
        System.out.println(nazwa + " mówi: Miau miau!");
    }
}

public class Main {
    public static void main(String[] args) {
        // Tworzymy instancje klas potomnych
        Zwierze pies = new Pies("Burek");
        Zwierze kot = new Kot("Mruczek");

        // Polimorfizm - obiekt rzutowany na klasę bazową
        Zwierze[] zwierzaki = { pies, kot };

        // Iteracja po tablicy i wywołanie metody polimorficznej
        for (Zwierze zwierze : zwierzaki) {
            zwierze.wydajDzwiek();  // Polimorfizm, zależnie od obiektu wywoła odpowiednią metodę
        }
    }
}
void main() {

}
