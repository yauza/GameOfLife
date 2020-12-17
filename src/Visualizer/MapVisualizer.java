package Visualizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Classes.*;
import Enums.*;
import Interfaces.*;

public class MapVisualizer extends JPanel implements ActionListener {
    public Map map;
    Timer tm = new Timer(5, this);
    int x = 0;

    public MapVisualizer(Map map){
        this.map = map;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.orange);
        g.fillRect(100, 30, 50, 30);

        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x++;
        repaint();
    }
}
