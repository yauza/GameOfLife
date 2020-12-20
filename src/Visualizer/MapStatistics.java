package Visualizer;

import Classes.Map;

import javax.swing.*;
import java.awt.*;
import java.net.CookiePolicy;

public class MapStatistics extends JPanel {
    public Map map;
    public MapVisualizer mapVisualizer;
    boolean pause;
    int x = 1;

    //Timer tm = new Timer(50, mapVisualizer);//this);


    public MapStatistics(Map map, MapVisualizer mapVisualizer){
        this.map = map;
        this.mapVisualizer = mapVisualizer;
    }

    public void paintComponent(Graphics g){
        if(!pause){
            super.paintComponent(g);

            g.setColor(new Color(125, 169, 203));
            g.fillRect(0,800,800,100);

            g.setColor(new Color(0,0,0));
            g.drawString("Era: " + map.era, 20, 20);
            g.drawString("Number of Animals: " + map.numberOfAnimals, 20, 50);
            g.drawString("Number of Grass: " + map.numberOfGrass, 20, 80);
            //x++;

            g.drawString("Average number of children: " + map.averageNumberOfChildren, 300, 20);
            g.drawString("Average energy level: " + map.averageEnergyLevel, 300, 50);
            g.drawString("Dominating genotype: " + map.dominatingGenotype, 300, 80);

            g.drawString("Average longevity: " + map.averageLongevity, 500, 20);

        }
    }
}
