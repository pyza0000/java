abstract class Urzadzenia {
    abstract void urzadzenie();
}

class Telefon extends Urzedzania {
    @Override
    void urzadzenie() {
        System.out.println("telefonieren");
    }
}
class Komputer extends Urzedzania {
    @Override
    void urzadzenie() {
        System.out.println("komputerieren");
    }
}
class Tablet extends Urzedzania {
    @Override
    void urzadzenie() {
        System.out.println("tableten");
    }