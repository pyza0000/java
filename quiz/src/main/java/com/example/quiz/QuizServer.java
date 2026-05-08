package com.example.quiz;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QuizServer extends JFrame {
    private JTextArea displayArea;
    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private volatile boolean gameOver = false;

    // Zmiana na kolejkę Produktów
    private BlockingQueue<Produkt> kolejka = new LinkedBlockingQueue<>();

    public QuizServer() {
        setTitle("QUIZ SERVER");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(new JScrollPane(displayArea));

        loadQuestions("pytania.txt");

        if (!questions.isEmpty()) {
            showQuestion();
            // Uruchomienie Producenta i Konsumenta z kolejką Produktów
            new Thread(new Producent(kolejka, 5000, this)).start();
            new Thread(new Konsument(kolejka, this)).start();
        }
    }

    private void loadQuestions(String filename) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) questions.add(new Question(parts[0], parts[1]));
            }
        } catch (IOException e) { displayArea.append("Błąd pliku pytania.txt\n"); }
    }

    private void showQuestion() {
        if (currentQuestionIndex < questions.size()) {
            displayArea.append("\nNr " + (currentQuestionIndex + 1) + ") " + questions.get(currentQuestionIndex).text + "\n");
        } else {
            displayArea.append("\nOdpowiedziano już na wszystkie pytania. Koniec zabawy! (8)\n");
            gameOver = true;
        }
    }

    public void nextQuestion() { currentQuestionIndex++; showQuestion(); }
    public boolean isGameOver() { return gameOver; }
    public Question getCurrentQuestion() { return questions.get(currentQuestionIndex); }
    public void appendToDisplay(String text) { SwingUtilities.invokeLater(() -> displayArea.append(text)); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizServer().setVisible(true));
    }
}