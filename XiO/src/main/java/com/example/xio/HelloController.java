package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class TicTacToeController {

    @FXML private Button b00,b01,b02,b10,b11,b12,b20,b21,b22;
    @FXML private Text statusText;

    private boolean xTurn = true;
    private int moveCount = 0;
    private boolean gameOver = false;

    @FXML
    private void handleMove(javafx.event.ActionEvent event) {
        if (gameOver) return;

        Button btn = (Button) event.getSource();
        if (!btn.getText().isEmpty()) return;

        btn.setText(xTurn ? "X" : "O");
        moveCount++;

        if (checkWin()) {
            statusText.setText("Wygrywa: " + (xTurn ? "X" : "O"));
            gameOver = true;
            return;
        }

        if (moveCount == 9) {
            statusText.setText("Remis!");
            gameOver = true;
            return;
        }

        xTurn = !xTurn;
        statusText.setText("Tura: " + (xTurn ? "X" : "O"));
    }

    private boolean checkWin() {
        return check(b00,b01,b02) || check(b10,b11,b12) || check(b20,b21,b22) ||
                check(b00,b10,b20) || check(b01,b11,b21) || check(b02,b12,b22) ||
                check(b00,b11,b22) || check(b02,b11,b20);
    }

    private boolean check(Button a, Button b, Button c) {
        return !a.getText().isEmpty() &&
                a.getText().equals(b.getText()) &&
                b.getText().equals(c.getText());
    }

    @FXML
    private void resetGame() {
        b00.setText(""); b01.setText(""); b02.setText("");
        b10.setText(""); b11.setText(""); b12.setText("");
        b20.setText(""); b21.setText(""); b22.setText("");

        xTurn = true;
        moveCount = 0;
        gameOver = false;
        statusText.setText("Tura: X");
    }
}
