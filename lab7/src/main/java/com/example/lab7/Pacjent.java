package com.example.lab7;

public class Pacjent {
    private final int id;
    private String imie;
    private String nazwisko;
    private int wiek;

    public Pacjent(int id, String imie, String nazwisko, int wiek) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
    }

    public int getId() { return id; }

    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }

    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }

    public int getWiek() { return wiek; }
    public void setWiek(int wiek) { this.wiek = wiek; }

    public String toJson() {
        return "{"
                + "\"id\":" + id + ","
                + "\"imie\":\"" + esc(imie) + "\","
                + "\"nazwisko\":\"" + esc(nazwisko) + "\","
                + "\"wiek\":" + wiek
                + "}";
    }

    private String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
