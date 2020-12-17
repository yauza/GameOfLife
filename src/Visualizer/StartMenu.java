package Visualizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Classes.*;
import Enums.*;
import Interfaces.*;


public class StartMenu extends JPanel implements ActionListener{
    // data needed to start the simulation
    public int mapWidth;
    public int mapHeight;
    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public int jungleRatio;

    // menu display
    private JLabel mapWidthLabel;
    private JLabel mapHeightLabel;
    private JLabel startEnergyLabel;
    private JLabel moveEnergyLabel;
    private JLabel plantEnergyLabel;
    private JLabel jungleRatioLabel;

    public JFrame menu;

    public StartMenu(){
        menu = new JFrame("Start menu");
        menu.setSize(500, 500);

        mapWidthLabel = new JLabel("Map width: ");
        menu.add(mapWidthLabel);
        mapWidthLabel.setLocation(100, 100);

        //menu.setLayout(null);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
