import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JFrameL extends JFrame
{
    /** Creates a new instance of JFrameL */
    public JFrameL(String title) {
        super(title);
        FrameListener listener = new FrameListener();
        addWindowListener(listener);
    }
    private class FrameListener extends WindowAdapter
    {

        public void windowClosing(WindowEvent e) {
            int confirm;
            if (HelperMethods.saved == false)
            {
                String  message = "The data in the application is not saved.\n"+
                        "Would you like to save it before exiting the application?";
                confirm = JOptionPane.showConfirmDialog (null, message);
                if (confirm == JOptionPane.YES_OPTION)
                    HelperMethods.writeElements();
            }
            System.exit(0);
        }
    }
}