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

                Produkt produkt = kolejka.take();

                if (server.isGameOver()) break;

                Question currentQ = server.getCurrentQuestion();
                if (currentQ.answer.equalsIgnoreCase(produkt.getAnswer().trim())) {

                    server.appendToDisplay(produkt.getNick() + " (" + produkt.getIp() + ") odpowiedział poprawnie :)\n");

                    kolejka.clear();

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    server.nextQuestion();

                } else {

                    server.appendToDisplay("Nadeszła odpowiedź błędna :(\n");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}