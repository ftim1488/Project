import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainPanel extends JPanel {
    public MainPanel(MainFrame frame) {
   
        setLayout(null);
        JLabel welc = new JLabel("WELCOME");
        welc.setBounds(195,30,250,100);
        welc.setFont(new Font("Arial", Font.BOLD, 30));
       
       
     
        JButton customerBtn = new JButton("Start");
        customerBtn.setBounds(400,200,70,80);
       
     
       customerBtn.addActionListener(e -> {
            frame.setContentPane(new TableSelectionPanel(frame));
            frame.revalidate();
            frame.repaint();
        });


       
       
        add(welc);
        add(customerBtn);
     
    }
}

