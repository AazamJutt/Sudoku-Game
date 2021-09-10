package controller;

import model.SudokuBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolveBtnController implements ActionListener {
    SudokuBoard game;
    JTextField[][] board;

    public SolveBtnController(SudokuBoard game,JTextField[][]board) {
        this.game = game;
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i =0 ;i<9;i++){
            for (int j =0;j<9;j++){
                board[i][j].setText(Integer.toString(game.getCorrectValue(j, i)));
                board[i][j].setForeground(Color.GREEN);
            }
        }
    }
}
