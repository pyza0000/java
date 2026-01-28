package com.example.lab7;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PacjentController {
    @FXML private TextField imieField;
    @FXML private TextField nazwiskoField;
    @FXML private Spinner<Integer> wiekSpinner;
    @FXML private ChoiceBox<String> sortChoice;
    private ArrayList<Pacjent> lista = new ArrayList<>();
    @FXML
    public void initialize() {
        sortChoice.getItems().addAll("ID","Imie", "Nazwisko", "Wiek");
        sortChoice.setValue("Nazwisko");
    }
    @FXML
    public void dodaj() {
        String imie = (imieField.getText() == null) ? "" : imieField.getText().trim();
        String nazwisko = (nazwiskoField.getText() == null) ? "" : nazwiskoField.getText().trim();
        int wiek;
        try {
            wiek = wiekSpinner.getValue();
        } catch (Exception e) {
            System.out.println("{\"error\":\"Nieprawidlowy wiek\"}");
            return;
        }
        if (imie.isEmpty() || nazwisko.isEmpty()) {
            System.out.println("{\"error\":\"Brak imienia lub nazwiska\"}");
            return;
        }
        Pacjent p = new Pacjent(nextId, imie, nazwisko, wiek);
        lista.add(p);
        nextId++;

        imieField.clear();
        nazwiskoField.clear();

        System.out.println(p.toJson());
    }
    @FXML
    public void drukuj() {
        String wybor = sortChoice.getValue();
        if (wybor == null) wybor = "Nazwisko";
        if ("Imie".equals(wybor)) {
            Collections.sort(lista, new Comparator<Pacjent>() {
                @Override
                public int compare(Pacjent a, Pacjent b) {
                    int x = a.getImie().compareToIgnoreCase(b.getImie());
                    if (x != 0) return x;
                    return a.getNazwisko().compareToIgnoreCase(b.getNazwisko());
                }
            });
        } else if ("Nazwisko".equals(wybor)) {
            Collections.sort(lista, new Comparator<Pacjent>() {
                @Override
                public int compare(Pacjent a, Pacjent b) {
                    int x = a.getNazwisko().compareToIgnoreCase(b.getNazwisko());
                    if (x != 0) return x;
                    return a.getImie().compareToIgnoreCase(b.getImie());
                }
            });
        } else { // Wiek
            Collections.sort(lista, new Comparator<Pacjent>() {
                @Override
                public int compare(Pacjent a, Pacjent b) {
                    int x = Integer.compare(a.getWiek(), b.getWiek());
                    if (x != 0) return x;
                    int y = a.getNazwisko().compareToIgnoreCase(b.getNazwisko());
                    if (y != 0) return y;
                    return a.getImie().compareToIgnoreCase(b.getImie());
                }
            });
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toJson());
        }
    }
}
