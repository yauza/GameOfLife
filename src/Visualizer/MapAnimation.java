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

    private JFrame test;

    private JPanel popupPanel;
    private JLabel genotypeLabel;
    private JLabel animalInfoLabel;
    private JButton showWhenDeadButton;
    private JButton followButton;
    private JLabel typeNLabel;
    private JTextField typeNText;



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
            if(l != null && l.size() != 0) {
                Collections.sort(l);
                Animal animal = l.get(0);

                test = new JFrame("Animal options");
                test.getContentPane();
                popupPanel = new JPanel();
                popupPanel.setSize(new Dimension(400, 300));

                genotypeLabel = new JLabel("Genotype: " + animal.genes.toString());
                genotypeLabel.setBounds(50,10, genotypeLabel.getPreferredSize().width, genotypeLabel.getPreferredSize().height);

                animalInfoLabel = new JLabel("Position: " + animal.position.toString());
                animalInfoLabel.setBounds(50, 50, animalInfoLabel.getPreferredSize().width, animalInfoLabel.getPreferredSize().height);

                typeNLabel = new JLabel("Type n: ");
                typeNLabel.setBounds(50,100, typeNLabel.getPreferredSize().width, typeNLabel.getPreferredSize().height);

                typeNText = new JTextField(6);
                typeNText.setBounds(150,100, typeNText.getPreferredSize().width, typeNText.getPreferredSize().height);

                // ---------------------------------------------------------------------

                showWhenDeadButton = new JButton("Show when the Animal is dead");
                showWhenDeadButton.setBounds(50,150,showWhenDeadButton.getPreferredSize().width, showWhenDeadButton.getPreferredSize().height);
                showWhenDeadButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        animal.showWhenDead = true;
                        showWhenDeadButton.setText("Wait for update!");
                    }
                });


                followButton = new JButton("Follow Animal for n-days");
                followButton.setBounds(50,200,followButton.getPreferredSize().width, followButton.getPreferredSize().height);
                followButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        animal.isFollowed = true;
                        animal.whenFollowingStarted = map.era;
                        int temp;
                        if(typeNText.getText().equals("")) temp = 0;
                        else temp = Integer.parseInt(typeNText.getText());
                        animal.n = temp;
                        followButton.setText("Started following!");
                    }
                });

                // ---------------------------------------------------------------------
                popupPanel.setLayout(null);
                popupPanel.add(genotypeLabel);
                popupPanel.add(animalInfoLabel);
                popupPanel.add(showWhenDeadButton);
                popupPanel.add(typeNLabel);
                popupPanel.add(typeNText);
                popupPanel.add(followButton);
                test.add(popupPanel);
                Object [] options = {"elo", "puap", "a"};

                //JOptionPane.showMessageDialog(null, popupPanel);
                test.setSize(400, 300);
                test.setVisible(true);
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
