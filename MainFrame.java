package CW_gol;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
    public MainFrame(Component[] components, String title) {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    for (Component comp_object : components ){
        add(comp_object.getCore(), comp_object.getPosition());
    }
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

    }
}
