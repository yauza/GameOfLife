package Visualizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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

    // menu display - simulation settings
    private JLabel mapWidthLabel;
    private JTextField mapWidthText;

    private JLabel mapHeightLabel;
    private JTextField mapHeightText;

    private JLabel startEnergyLabel;
    private JTextField startEnergyText;

    private JLabel moveEnergyLabel;
    private JTextField moveEnergyText;

    private JLabel plantEnergyLabel;
    private JTextField plantEnergyText;

    private JLabel jungleRatioLabel;
    private JTextField jungleRatioText;

    // start the simulation
    private JButton startButton;

    public JFrame menu;

    public StartMenu(){
        menu = new JFrame("Start menu");
        JPanel panel = new JPanel();
        menu.getContentPane();


        mapWidthLabel = new JLabel("Map width: ");
        Dimension size1 = mapWidthLabel.getPreferredSize();
        mapWidthLabel.setBounds(40, 20, size1.width, size1.height);

        mapWidthText = new JTextField(6);
        Dimension tsize1 = mapWidthText.getPreferredSize();
        mapWidthText.setBounds(140, 20, tsize1.width, tsize1.height);

        // ------------------------------------------------------------

        mapHeightLabel = new JLabel("Map height: ");
        Dimension size2 = mapHeightLabel.getPreferredSize();
        mapHeightLabel.setBounds(40, 80, size2.width, size2.height);

        mapHeightText = new JTextField(6);
        Dimension tsize2 = mapHeightText.getPreferredSize();
        mapHeightText.setBounds(140, 80, tsize2.width, tsize2.height);

        // ------------------------------------------------------------

        startEnergyLabel = new JLabel("Start energy: ");
        Dimension size3 = startEnergyLabel.getPreferredSize();
        startEnergyLabel.setBounds(40, 140, size3.width, size3.height);

        startEnergyText = new JTextField(6);
        Dimension tsize3 = startEnergyText.getPreferredSize();
        startEnergyText.setBounds(140, 140, tsize3.width, tsize3.height);

        // ------------------------------------------------------------

        moveEnergyLabel = new JLabel("Move energy: ");
        Dimension size4 = moveEnergyLabel.getPreferredSize();
        moveEnergyLabel.setBounds(40, 200, size4.width, size4.height);

        moveEnergyText = new JTextField(6);
        Dimension tsize4 = moveEnergyText.getPreferredSize();
        moveEnergyText.setBounds(140, 200, tsize4.width, tsize4.height);

        // ------------------------------------------------------------

        plantEnergyLabel = new JLabel("Plant energy: ");
        Dimension size5 = plantEnergyLabel.getPreferredSize();
        plantEnergyLabel.setBounds(40, 260, size5.width, size5.height);

        plantEnergyText = new JTextField(6);
        Dimension tsize5 = plantEnergyText.getPreferredSize();
        plantEnergyText.setBounds(140, 260, tsize5.width, tsize5.height);

        // ------------------------------------------------------------

        jungleRatioLabel = new JLabel("Jungle ratio: ");
        Dimension size6 = jungleRatioLabel.getPreferredSize();
        jungleRatioLabel.setBounds(40, 320, size6.width, size6.height);

        jungleRatioText = new JTextField(6);
        Dimension tsize6 = jungleRatioText.getPreferredSize();
        jungleRatioText.setBounds(140, 320, tsize6.width, tsize6.height);

        // ------------------------------------------------------------

        startButton = new JButton("Start!");
        Dimension bsize = startButton.getPreferredSize();
        startButton.setBounds(100, 400, bsize.width, bsize.height);
        startButton.addActionListener(this);

        // ------------------------------------------------------------

        panel.setLayout(null);
        panel.add(mapWidthLabel);
        panel.add(mapWidthText);

        panel.add(mapHeightLabel);
        panel.add(mapHeightText);

        panel.add(startEnergyLabel);
        panel.add(startEnergyText);

        panel.add(moveEnergyLabel);
        panel.add(moveEnergyText);

        panel.add(plantEnergyLabel);
        panel.add(plantEnergyText);

        panel.add(jungleRatioLabel);
        panel.add(jungleRatioText);

        panel.add(startButton);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.add(panel);
        menu.setSize(300, 500);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JOptionPane.showMessageDialog(menu, "a");
//        HashMap<Vector2d, java.util.List<Animal>> animals = new HashMap<>();
//        HashMap<Vector2d, Grass> grass = new HashMap<>();

        mapWidth = Integer.parseInt(mapWidthText.getText());
        mapHeight = Integer.parseInt(mapHeightText.getText());
        startEnergy = Integer.parseInt(startEnergyText.getText());
        moveEnergy = Integer.parseInt(moveEnergyText.getText());
        plantEnergy = Integer.parseInt(plantEnergyText.getText());
        jungleRatio = Integer.parseInt(jungleRatioText.getText());

        Map map = new Map(mapWidth, mapHeight, mapWidth/jungleRatio, mapHeight/jungleRatio, startEnergy,moveEnergy, plantEnergy);
//        map.randomlyPlaceAnimals(70);
        MapVisualizer newSimulation = new MapVisualizer(map);
        newSimulation.startTheSimulation();
//        MapAnimation ma = new MapAnimation(map, newSimulation);
//        newSimulation.mapAnimation = ma;
    }

    private boolean checkTheValues(){
        return false;
    }
}
