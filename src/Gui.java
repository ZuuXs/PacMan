import javax.swing.*;
import java.awt.*;

public class Gui {
    Gui() {
        JFrame frame = new JFrame("PacMan");
        Game game= new Game();
        frame.setContentPane(game);
        frame.setSize(516,549);
        frame.setBackground(Color.BLACK);
        frame.setLocation(700, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
