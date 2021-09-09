import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {
    JFrame screen;
    SudokuBoard game;
    SudokuTextField[][] fields;
    GameController controller;
    static int N = 9;

    public GUI(SudokuBoard g) {
        this.game = g;
        controller = new GameController(game);
        initGUI();
    }

    public void initGUI() {
        this.screen = new JFrame("Sudoku");
        fields = new SudokuTextField[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fields[i][j] = new SudokuTextField();
                fields[i][j].setBounds((i * 50), (j * 50), 50, 50);
                screen.add(fields[i][j]);
            }
        }
        setupBoard();
        screen.setSize(465, 487);//400 width and 500 height
        screen.setLocation(450, 150);
        screen.setLayout(null);//using no layout managers
        screen.setVisible(true);//making the frame visible
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(game.board[i][j]!=0)
                    fields[i][j].setEnabled(false);
                fields[i][j].setText(Integer.toString(game.board[i][j]));
            }
        }
    }

    public static void main(String[] args) {
        SudokuBoard s = new SudokuBoard(9, 20);
        GUI g = new GUI(s);
    }
}
