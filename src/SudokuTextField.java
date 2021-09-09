import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class SudokuTextField extends JTextField {
    public SudokuTextField() {
        super();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.DARK_GRAY);
        setForeground(Color.GREEN);
        setFont(new Font("Courier",Font.BOLD,20));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected Document createDefaultModel() {
        return new LimitDocument();
    }


    private class LimitDocument extends PlainDocument {

        @Override
        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= 1 && str.charAt(0)>'0' && str.charAt(0)<='9') {
                super.insertString(offset, str, attr);
            }
        }

    }
}
