package com.example.lab2;//pakiet projektu
import javafx.fxml.FXML;//import FXML
import javafx.scene.Node;//import Node
import javafx.scene.control.Button;//import Button
import javafx.scene.control.Label;//import Label
import javafx.scene.layout.GridPane;//import GridPane
public class GameBoardController{//kontroler gry

    @FXML
    private GridPane gameGrid;//plansza
    @FXML
    private Label turnLabel;//etykieta
    @FXML
    private Button restart;//restart

    private final int X_VALUE=1;//wartość X
    private final int O_VALUE=2;//wartość O

    private int boardData[]=new int[9];//plansza 1D
    private boolean xTurnActive=true;//czy X ma ruch
    private int filledFields=0;//liczba ruchów

    private final int[][] WIN_PATTERNS={//układy wygranej
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    private int resolveIndex(Button btn){//wyznaczenie indeksu
        Integer c=GridPane.getColumnIndex(btn);//kolumna
        Integer r=GridPane.getRowIndex(btn);//wiersz
        if(c==null)c=0;//domyślna kolumna
        if(r==null)r=0;//domyślny wiersz
        return r*3+c;//indeks
    }

    private boolean registerMove(Button btn){//rejestracja ruchu
        int idx=resolveIndex(btn);//indeks pola
        if(boardData[idx]!=0)return false;//pole zajęte

        if(xTurnActive){//ruch X
            btn.setText("X");//ustaw X
            boardData[idx]=X_VALUE;//zapis
        }else{//ruch O
            btn.setText("O");//ustaw O
            boardData[idx]=O_VALUE;//zapis
        }

        btn.setDisable(true);//blokada
        filledFields++;//inkrementacja
        return true;//ruch poprawny
    }

    private boolean checkWinFor(int value){//sprawdzenie dla symbolu
        for(int[] p:WIN_PATTERNS){//iteracja wzorców
            if(boardData[p[0]]==value
                    &&boardData[p[1]]==value
                    &&boardData[p[2]]==value)return true;//wygrana
        }
        return false;//brak
    }

    private void lockGrid(){//blokada planszy
        for(Node n:gameGrid.getChildren())((Button)n).setDisable(true);//blokada
    }

    private void updateLabelAfterMove(boolean win){//aktualizacja etykiety
        if(win){//jeśli wygrana
            turnLabel.setText(xTurnActive?"Wygrywa X!":"Wygrywa O!");//komunikat
        }else if(filledFields==9){//jeśli remis
            turnLabel.setText("Remis!");//komunikat
        }else{//normalna gra
            turnLabel.setText(xTurnActive?"Ruch: X":"Ruch: O");//tura
        }
    }

    public void GameButtonClicked(javafx.event.ActionEvent e){//klik pola
        Button clicked=(Button)e.getSource();//kliknięty przycisk

        if(!registerMove(clicked))return;//jeśli pole zajęte

        int currentValue=xTurnActive?X_VALUE:O_VALUE;//aktualny symbol
        boolean win=checkWinFor(currentValue);//sprawdzenie wygranej

        if(win||filledFields==9){//koniec gry
            lockGrid();//blokada
            restart.setDisable(false);//restart
        }else{
            xTurnActive=!xTurnActive;//zmiana gracza
        }

        updateLabelAfterMove(win);//aktualizacja etykiety
    }
    public void OnRestartClicked(javafx.event.ActionEvent e){//restart gry
        xTurnActive=true;//ustawienie tury X
        filledFields=0;//wyzerowanie liczby ruchów
        for(int i=0;i<boardData.length;i++){//czyszczenie tablicy
            boardData[i]=0;//ustawienie pola na puste
        }
        for(Node node:gameGrid.getChildren()){//przejście po przyciskach
            Button btn=(Button)node;//rzutowanie na Button
            btn.setText("");//usunięcie znaku
            btn.setDisable(false);//odblokowanie przycisku
        }
        restart.setDisable(true);//zablokowanie restartu
        turnLabel.setText("Ruch: X");//ustawienie tekstu początkowego
    }

}
