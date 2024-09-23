import javax.swing.*;
import java.awt.*;

public class Main {
    public static CheckOptionsPanel frame;
    public static JTextArea ta;

    public static void main(String[] args) {
        frame = new CheckOptionsPanel("Bank System Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ta = new JTextArea(10, 50);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12));

        frame.getContentPane().add(ta);
        frame.pack();
        frame.setVisible(true);
    }
}
