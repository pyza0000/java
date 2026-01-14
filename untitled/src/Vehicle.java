// Klasa abstrakcyjna — wspólny typ dla wszystkich pojazdów
abstract class Vehicle {
    protected String name;

    public Vehicle(String name) {
        this.name = name;
    }

    // Metoda abstrakcyjna — każdy pojazd ma inny sposób jazdy
    public abstract void move();
}

// Klasa dziedzicząca 1
class Car extends Vehicle {
    public Car(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println(name + " jedzie po drodze używając silnika spalinowego.");
    }
}

// Klasa dziedzicząca 2
class Bicycle extends Vehicle {
    public Bicycle(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println(name + " jedzie napędzany siłą nóg rowerzysty.");
    }
}

// Klasa dziedzicząca 3
class Scooter extends Vehicle {
    public Scooter(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println(name + " porusza się elektrycznie po ścieżce miejskiej.");
    }
}
