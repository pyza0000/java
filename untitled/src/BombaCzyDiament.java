import javax.swing.*; // Importowanie klas GUI
import java.awt.*; // Importowanie klas do układów graficznych
import java.util.Random; // Importowanie klasy Random do generowania liczb losowych
import java.util.Scanner; // Importowanie klasy Scanner do pobierania danych od użytkownika

public class BombaCzyDiament extends JFrame { // Klasa gry, dziedziczy po JFrame (okno aplikacji)
    private JButton przyciskLewy; // Lewy przycisk
    private JButton przyciskPrawy; // Prawy przycisk
    private JLabel infoLabel; // Etykieta do wyświetlania informacji
    private JTextArea wynikArea; // Obszar tekstowy do wyników gry

    private Random losuj = new Random(); // Obiekt do losowania liczb
    private int proby = 0; // Liczba prób
    private int diamenty = 0; // Liczba zdobytych diamentów
    private String imieGracza; // Imię gracza

    public BombaCzyDiament() { // Konstruktor klasy
        // Pobranie imienia gracza
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj swoje imię: ");
        imieGracza = scanner.nextLine(); // Zapisanie imienia gracza

        // Ustawienie tytułu okna
        setTitle("Bomba czy Diament? Gracz: " + imieGracza + ", 20 prób.");
        setLayout(new GridLayout(2, 2)); // Układ 2x2 dla komponentów

        // Tworzenie komponentów
        przyciskLewy = new JButton("Kliknij tutaj!");
        przyciskPrawy = new JButton("A może tutaj?");
        infoLabel = new JLabel("Spróbuj wygrać diament!", SwingConstants.CENTER);
        wynikArea = new JTextArea("Próba: 0, Diamenty: 0");

        wynikArea.setEditable(false); // Ustawienie pola tekstowego jako nieedytowalnego

        // Dodanie komponentów do okna
        add(przyciskLewy);
        add(przyciskPrawy);
        add(infoLabel);
        add(new JScrollPane(wynikArea)); // ScrollPane umożliwia przewijanie wyników

        // Obsługa zdarzeń kliknięcia na przyciskach
        przyciskLewy.addActionListener(e -> wykonajProbe());
        przyciskPrawy.addActionListener(e -> wykonajProbe());

        // Ustawienia okna
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zamknięcie aplikacji przy zamknięciu okna
        setVisible(true); // Ustawienie okna jako widocznego
    }

    private void wykonajProbe() { // Metoda wykonująca próbę gry
        if (proby >= 20) { // Sprawdzenie, czy liczba prób osiągnęła 20
            infoLabel.setText("Koniec!"); // Koniec gry
            return; // Zakończenie metody
        }

        proby++; // Zwiększenie liczby prób
        int los = losuj.nextInt(2); // Losowanie: 0 (bomba) lub 1 (diament)

        if (los == 1) { // Jeśli wylosowano diament
            diamenty++; // Zwiększenie liczby diamentów
            infoLabel.setText("Diament!"); // Informacja o zdobyciu diamentu
        } else { // Jeśli wylosowano bombę
            infoLabel.setText("Bomba!"); // Informacja o trafieniu w bombę
        }

        // Aktualizacja wyniku na ekranie
        wynikArea.setText("Próba: " + proby + ", Diamenty: " + diamenty);

        if (proby == 20) { // Jeśli to była ostatnia próba
            infoLabel.setText("Koniec! Zdobyłeś diamentów: " + diamenty); // Pokazuje wynik końcowy
            przyciskLewy.setEnabled(false); // Zablokowanie przycisków
            przyciskPrawy.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        // Uruchomienie gry w osobnym wątku
        SwingUtilities.invokeLater(() -> new BombaCzyDiament());
    }
}
