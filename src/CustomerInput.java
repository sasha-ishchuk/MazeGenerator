import javax.swing.*;
import java.awt.*;

public class CustomerInput extends JFrame {

    public CustomerInput() {
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("CUSTOMER INPUT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 450);
        getContentPane().setLayout(null);
    }

    void closeWindow(){
        this.getContentPane().setVisible(false);
        this.dispose();
    }
}
