import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame screen;
    JTextField[][] fields;
    static int N = 9;

    public GUI() {
        this.screen = new JFrame("Sudoku");
        fields = new JTextField[N][N];
        for (int i =0;i<N;i++){
            for (int j=0;j<N;j++){
                fields[i][j] = new JTextField();
                fields[i][j].setBounds((i*50),(j*50),50,50);
                fields[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                fields[i][j].setBackground(Color.DARK_GRAY);
                fields[i][j].setForeground(Color.GREEN);
                screen.add(fields[i][j]);
            }
        }

        screen.setSize(465,487);//400 width and 500 height
        screen.setLocation(450,150);
        screen.setLayout(null);//using no layout managers
        screen.setVisible(true);//making the frame visible
    }

    public static void main(String[] args) {
       GUI g = new GUI();
    }
}
