public class x {
    abstract class Urzedzania {
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
    }
}
