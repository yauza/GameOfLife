package Visualizer;
import Classes.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapVisualizer extends JPanel implements ActionListener {
    public Map map;
    public int startAnimals;
    //public MapAnimation mapAnimation;

    Timer tm = new Timer(5, this);
    int x = 0;

    public JFrame simulation;
    public JPanel panel;

    public MapAnimation ma;
    public MapStatistics ms;

    // buttons
    private JButton pauseButton;
    private JButton resumeButton;
    private JButton showDGButton;

    private int numberOfAnimals;
    private int numberOfGrass;

    // starting the simulation with data from json file
//    public MapVisualizer(ParametersLoader loader){
//        Map map = new Map(loader.mapWidth, loader.mapHeight, loader.mapWidth / loader.jungleRatio, loader.mapHeight / loader.jungleRatio, loader.startEnergy, loader.moveEnergy, loader.plantEnergy);
//        this.map = map;
//        MapVisualizer sim = new MapVisualizer(map, loader.startAnimals);
//        sim.startTheSimulation();
//    }

    public MapVisualizer(Map map, int startAnimals){
        this.map = map;
        this.startAnimals = startAnimals;

        simulation = new JFrame("Simulation");
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

        panel = new JPanel();
        panel.setBackground(new Color(125, 169, 203));
        panel.setBounds(0, 900, 800, 100);

        ma = new MapAnimation(map, this);
        ma.setSize(new Dimension(800, 800));

        ms = new MapStatistics(map, this);
        ms.setBounds(0,800,800,100);

        // ---------------------------------------------------------

        pauseButton = new JButton("Pause");
        Dimension pbsize = pauseButton.getPreferredSize();
        pauseButton.setBounds(100, 920, pbsize.width, pbsize.height);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ma.pause = true;
            }
        });

        resumeButton = new JButton("Resume");
        Dimension rbsize = resumeButton.getPreferredSize();
        resumeButton.setBounds(296, 920, rbsize.width, rbsize.height);
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ma.showDG = false;
                ma.pause = false;
            }
        });


        showDGButton = new JButton("Dominating Genotype");
        Dimension sbsize = showDGButton.getPreferredSize();
        showDGButton.setBounds(500, 920, sbsize.width,sbsize.height);
        showDGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ma.pause){
                    ma.showDG = true;
                    ma.repaint();
                }else{
                    JOptionPane.showMessageDialog(simulation,"The simulation is not paused!");
                }
            }
        });

        // ---------------------------------------------------------

        panel.setLayout(null);
        panel.add(pauseButton);
        panel.add(resumeButton);
        panel.add(showDGButton);
        simulation.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        simulation.add(ma);
        simulation.add(ms);
        simulation.add(panel);
        simulation.setSize(800, 1000);
        simulation.setPreferredSize(new Dimension(800, 1000));
        simulation.setVisible(true);

    }

    public void startTheSimulation(){
        map.randomlyPlaceAnimals(startAnimals);
        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!ma.pause) {
            ma.repaint();
            ms.repaint();
            map.newEra();
        }


    }
}
