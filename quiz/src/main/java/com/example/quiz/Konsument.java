package com.example.quiz;

import java.util.concurrent.BlockingQueue;

public class Konsument implements Runnable {
    private BlockingQueue<Produkt> kolejka;
    private QuizServer server;

    public Konsument(BlockingQueue<Produkt> kolejka, QuizServer server) {
        this.kolejka = kolejka;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            while (!server.isGameOver()) {
                // Pobranie z kolejki (blokuje wątek aż pojawi się Produkt)
                Produkt produkt = kolejka.take();

                if (server.isGameOver()) break;

                Question currentQ = server.getCurrentQuestion();

                if (currentQ.answer.equalsIgnoreCase(produkt.getAnswer().trim())) {
                    // Logika poprawnej odpowiedzi
                    server.appendToDisplay(produkt.getNick() + " (" + produkt.getIp() + ") odpowiedział poprawnie :)\n");

                    kolejka.clear(); // Czyścimy kolejkę z nieaktualnych odpowiedzi

                    server.nextQuestion();
                } else {
                    // Logika błędnej odpowiedzi
                    server.appendToDisplay("Nadeszła odpowiedź błędna :(\n");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}