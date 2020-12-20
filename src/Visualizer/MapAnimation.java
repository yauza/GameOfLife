package Visualizer;

import Classes.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.List;
import Classes.*;

public class MapAnimation extends JPanel{ //implements ActionListener{
    public Map map;
    public MapVisualizer mapVisualizer;
    public boolean pause;
    public boolean showDG;

    public String DG;

    private Image high;
    private Image mid;
    private Image low;

    //Timer tm = new Timer(50, mapVisualizer);//this);


    public MapAnimation(Map map, MapVisualizer mapVisualizer){
        this.map = map;
        this.mapVisualizer = mapVisualizer;
        this.pause = false;
        this.showDG = false;


        try {
            high = ImageIO.read(getClass().getResource("/Images/highEnergy.png"));
            mid = ImageIO.read(getClass().getResource("/Images/midEnergy.png"));
            low = ImageIO.read(getClass().getResource("/Images/lowEnergy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Random generator = new Random();
        int width = 800, height = 800;
        int ws = width / map.width;
        int hs = height / map.length;

        // ---------------------------Background-----------------------------------
        g.setColor(new Color(253, 253, 144));
        g.fillRect(0, 0, width, height);

        // ---------------------------Jungle---------------------------------------
        int jungleX = (width - map.jungleWidth * ws) / 2, jungleY = (width - map.jungleLength * hs) / 2;;
        g.setColor(new Color(55, 165, 13, 149));
        g.fillRect(jungleX, jungleY, map.jungleWidth * ws, map.jungleLength * hs);

        // ---------------------------Grass----------------------------------------
        for(Grass grass : map.grass.values()){
            g.setColor(new Color(85, 172, 165));
            g.fillRect(grass.position.x * ws, grass.position.y * hs, ws, hs);
        }

        // ---------------------------Animals--------------------------------------
        for(List<Animal> l : map.animals.values()){
            for(Animal animal : l){
                //g.setColor(new Color(255, 0, 58));
                //g.fillRect(animal.position.x * ws, animal.position.y * hs, ws, hs);
                int type = animal.getEnergyLevel(map.startEnergy);
                if(type == 1) g.drawImage(high, animal.position.x * ws, animal.position.y * hs, ws, hs, this);
                else if(type == 0) g.drawImage(mid, animal.position.x * ws, animal.position.y * hs, ws, hs, this);
                else g.drawImage(low, animal.position.x * ws, animal.position.y * hs, ws, hs, this);
                System.out.println(map.startEnergy);
                System.out.println(animal.energy);
                System.out.println(animal.getEnergyLevel(map.startEnergy));
                //g.drawImage(high, animal.position.x * ws, animal.position.y * hs, ws, hs, this);

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
