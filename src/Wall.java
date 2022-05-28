import javax.swing.*;
import java.awt.*;

public class Wall extends JPanel {
    int x, y;
    int row, col;
    private final int size = 30;


    Wall(int row, int col){
        this.row = row;
        this.col = col;
        x = row * size;
        y = col * size;
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.red);
        graphics2D.fillRect(x, y, size, size);
    }
}