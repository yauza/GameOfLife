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
    public boolean pause;
    public boolean showDG;

    public String DG;

    //Timer tm = new Timer(50, mapVisualizer);//this);


    public MapAnimation(Map map, MapVisualizer mapVisualizer){
        this.map = map;
        this.mapVisualizer = mapVisualizer;
        this.pause = false;
        this.showDG = false;
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Random generator = new Random();
        int width = 800, height = 800;
        int ws = width / map.width;
        int hs = height / map.length;

        // ---------------------------Background-----------------------------------
        g.setColor(new Color(220, 189, 51));
        g.fillRect(0, 0, width, height);

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

        if(showDG) {
            DG = map.dominatingGenotype;
            for(List<Animal> l : map.animals.values()){
                for(Animal animal : l){
                    if(animal.genes.toString().equals(DG)) {
                        g.setColor(new Color(131, 16, 191));
                        g.fillRect(animal.position.x * ws, animal.position.y * hs, ws, hs);
                    }
                }
            }
        }



            //g.setColor(new Color(0,0,0));
            //g.drawString("sa " + map.numberOfAnimals, 500, 500);
            //g.drawString(" a " + map.numberOfGrass, 500, 550);
            //g.drawString(""+pause, 500, 570);
            //map.newEra();
            //tm.start();

    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }

}
