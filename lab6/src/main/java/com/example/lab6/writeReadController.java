package com.example.lab6;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class writeReadController {

    @FXML
    private TextArea textField;

    private Path filePath = Paths.get(System.getProperty("user.home") + "\\tekst.txt");

    @FXML
    public void writeToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(textField.getText());
        } catch (IOException e) {

        }
    }

    @FXML
    public void readFromFile() {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            textField.setText(sb.toString());
        } catch (IOException e) {
        }
    }
}
