import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {
    int x1, y1;
    int x2, y2;
    int size = 30;

    Line(int x1, int y1, int x2, int y2){
        this.x1 = x1 * size;
        this.y1 = y1 * size;
        this.x2 = x2 * size;
        this.y2 = y2 * size;
    }

    protected void paintComponent(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.black);
        graphics2D.drawLine(x1, y1, x2, y2);
    }
}