package Visualizer;

import Classes.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import Classes.*;

public class MapAnimation extends JPanel implements MouseListener { //implements ActionListener{
    public Map map;
    public MapVisualizer mapVisualizer;
    public boolean pause;
    public boolean showDG;

    public String DG;

    private Image high;
    private Image mid;
    private Image low;

    private int wst;
    private int hst;


    public MapAnimation(Map map, MapVisualizer mapVisualizer){
        this.map = map;
        this.mapVisualizer = mapVisualizer;
        this.pause = false;
        this.showDG = false;
        addMouseListener(this);


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
        int mWidth = map.width, mHeight = map.length;
        int ws = Math.round(width / map.width);
        int hs = Math.round(height / map.length);
        wst = ws;
        hst = hs;

        // ---------------------------Background-----------------------------------
        g.setColor(new Color(189, 217, 207));
        g.fillRect(0, 0, width, height);

        // ---------------------------Steppe---------------------------------------
        g.setColor(new Color(253, 253, 144));
        g.fillRect(0, 0, mWidth * ws, mHeight * hs);

        // ---------------------------Jungle---------------------------------------
        int jungleX = (mWidth*ws - (map.jungleWidth * ws)) / 2, jungleY = (mHeight*hs - (map.jungleLength * hs)) / 2;;
        g.setColor(new Color(55, 165, 13, 149));
        g.fillRect(jungleX, jungleY, map.jungleWidth * ws, map.jungleLength * hs);

        // ---------------------------Grass----------------------------------------
        for(Grass grass : map.grass.values()){
            g.setColor(new Color(127, 255, 40));
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

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(pause) {
            int x = e.getX();
            int y = e.getY();
            List<Animal> l = map.animals.get(new Vector2d(x/wst, y/hst));
            if(l != null) {
                Collections.sort(l);
                Animal animal = l.get(0);
                JOptionPane.showMessageDialog(this, "Genotype: " + animal.genes.toString(), "Animal details:", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
