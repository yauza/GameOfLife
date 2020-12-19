package Visualizer;

import Classes.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.List;
import Classes.*;

public class MapAnimation extends JPanel{ //implements ActionListener{
    public Map map;
    public MapVisualizer mapVisualizer;
    boolean pause;

    //Timer tm = new Timer(50, mapVisualizer);//this);


    public MapAnimation(Map map, MapVisualizer mapVisualizer){
        this.map = map;
        this.mapVisualizer = mapVisualizer;
    }

    public void paintComponent(Graphics g){
        if(!pause) {
            super.paintComponent(g);
            Random generator = new Random();
            int width = 600, height = 600;
            int ws = width / map.width;
            int hs = height / map.length;

            // ---------------------------Background-----------------------------------
            g.setColor(new Color(220, 189, 51));
            g.fillRect(0, 0, 600, 600);

            // ---------------------------Jungle---------------------------------------
            int jungleX = (width - map.jungleWidth * ws) / 2, jungleY = (width - map.jungleLength * hs) / 2;;
            g.setColor(new Color(66, 168, 34));
            g.fillRect(jungleX, jungleY, map.jungleWidth * ws, map.jungleLength * hs);

            // ---------------------------Grass----------------------------------------
            for(Grass grass : map.grass.values()){
                g.setColor(new Color(85, 172, 165));
                g.fillRect(grass.position.x * ws, grass.position.y * hs, ws, hs);
            }

            // ---------------------------Animals--------------------------------------
            for(List<Animal> l : map.animals.values()){
                for(Animal animal : l){
                    g.setColor(new Color(255, 0, 58));
                    g.fillRect(animal.position.x * ws, animal.position.y * hs, ws, hs);
                }
            }

            g.setColor(new Color(0,0,0));
            g.drawString("sa " + map.numberOfAnimals, 500, 500);
            g.drawString(" a " + map.numberOfGrass, 500, 550);
            g.drawString(""+pause, 500, 570);
            //map.newEra();
            //tm.start();
        }else{
            g.drawString(""+pause, 500, 570);
        }
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }

}
