import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    SudokuBoard game;

    public GameController(SudokuBoard game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
