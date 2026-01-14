
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
//_____________________________________________
interface funkcje {
    void SwitchOn();
}

class Telefonik implements funkcje {
    @Override
    public void SwitchOn() {
        System.out.println("Telefon został włączony");
    }
}

class Komputerek implements funkcje {
    @Override
    public void SwitchOn() {
        System.out.println("Komputer został uruchomiony");
    }
}

class Tabletek implements funkcje {
    @Override
    public void SwitchOn() {
        System.out.println("Tablet został aktywowany");
    }
}
public class Main {
    public static void main(String[] args) {
        Telefon telefon = new Telefon();
        Komputer komputer = new Komputer();
        Tablet tablet = new Tablet();
        Urzedzania[] urzadzenia = { telefon, komputer, tablet };
        for (Urzedzania u : urzadzenia) {
            u.urzadzenie();
        }
        Telefonik telefonik = new Telefonik();
        Komputerek komputerek = new Komputerek();
        Tabletek tabletek = new Tabletek();
        funkcje[] functions = {telefonik,komputerek,tabletek};
        for (funkcje f : functions) {
            f.SwitchOn();
        }
    }
}
