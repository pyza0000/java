abstract class UrzadzenieElektroniczne {
    String nazwa;

    public UrzadzenieElektroniczne(String nazwa) {
        this.nazwa = nazwa;
    }

    public abstract void wlacz();
    public abstract void pokazInfo();
}

class Telewizor extends UrzadzenieElektroniczne {
    public Telewizor(String nazwa) {
        super(nazwa);
    }

    @Override
    public void wlacz() {
        System.out.println(nazwa + "tekst 1");
    }

    @Override
    public void pokazInfo() {
        System.out.println(nazwa + " tekst 2");
    }
}

class Telefon extends UrzadzenieElektroniczne {
    public Telefon(String nazwa) {
        super(nazwa);
    }

    @Override
    public void wlacz() {
        System.out.println(nazwa + " tekst 3.");
    }

    @Override
    public void pokazInfo() {
        System.out.println(nazwa + " tekst 4");
    }
}

class Komputer extends UrzadzenieElektroniczne {
    public Komputer(String nazwa) {
        super(nazwa);
    }

    @Override
    public void wlacz() {
        System.out.println(nazwa + " tekst 5");
    }

    @Override
    public void pokazInfo() {
        System.out.println(nazwa + " tekst 6.");
    }
}

public class Main {
    public static void main(String[] args) {
        UrzadzenieElektroniczne telewizor = new Telewizor("Sony Bravia");
        UrzadzenieElektroniczne telefon = new Telefon("iPhone");
        UrzadzenieElektroniczne komputer = new Komputer("Dell XPS");

        UrzadzenieElektroniczne[] urzadzenia = { telewizor, telefon, komputer };

        for (UrzadzenieElektroniczne urzadzenie : urzadzenia) {
            urzadzenie.wlacz();
            urzadzenie.pokazInfo();
        }
    }
}
