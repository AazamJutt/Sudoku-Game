import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener{
    SudokuBoard game;

    public GameController(SudokuBoard game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String[] indexes = e.getComponent().getName().split(",");
        int i = Integer.parseInt(indexes[0]);
        int j = Integer.parseInt(indexes[1]);
        JTextField textField = (JTextField) e.getSource();
        String text = textField.getText();
        if(e.getKeyCode()>=49 && e.getKeyCode()<=57){
            try {
                Thread.sleep(100);
            }catch (Exception ex){
            }
            int val = Integer.parseInt(text);
            if(game.isCorrectValue(i,j,val)){
                textField.setForeground(Color.GREEN);
            }
            else {
                textField.setForeground(Color.RED);
            }
        }
        if (e.getKeyCode()==32){

            try {
                Thread.sleep(100);
            }catch (Exception ex){
            }
            textField.setText(Integer.toString(game.getCorrectValue(i,j)));

            textField.setForeground(Color.GREEN);
        }
    }
}
