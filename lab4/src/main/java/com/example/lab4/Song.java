package com.example.lab4;

public class Song {

    private String title;
    private int tempo;
    private String rhythm;
    private String album;
    private String performer;
    private String text;

    public Song() {

        title = "Tyle Słońca w całym mieście";
        tempo = 100;
        rhythm = "4/4";
        album = "Tyle Słońca w całym mieście";
        performer = "Anna Jantar";

        text = """
                Tyle słońca w całym mieście
                Nie widziałeś tego jeszcze
                Popatrz, o popatrz!
                """;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getRhythm() {
        return rhythm;
    }

    public void setRhythm(String rhythm) {
        this.rhythm = rhythm;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}