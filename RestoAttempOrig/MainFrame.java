import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("MyRestaurant");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setContentPane(new MainPanel(this));
        setVisible(true);
    }


    public void returnToMain() {
        setContentPane(new MainPanel(this));
        revalidate();
    }
}

