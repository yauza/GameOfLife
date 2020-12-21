package Visualizer;
import Classes.Animal;
import Classes.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class MapVisualizer extends JPanel implements ActionListener {
    public Map map;
    public int startAnimals;
    //public MapAnimation mapAnimation;

    Timer tm = new Timer(0, this);
    int x = 0;

    public JFrame simulation;
    public JPanel panel;

    public MapAnimation ma;
    public MapStatistics ms;

    // buttons
    private JButton pauseButton;
    private JButton resumeButton;
    private JButton showDGButton;
    private JButton writeToFileButton;

    private int averageNumberOfAnimals = 0;
    private int averageNumberOfGrass = 0;
    private String dominatingGenotype = "";
    private int averageEnergyLevel = 0;
    private int averageLongevity = 0;
    private int averageNumberOfChildren = 0;
    private HashMap<String, Integer> genotypes = new HashMap<>();


    public MapVisualizer(Map map, int startAnimals){
        this.map = map;
        this.startAnimals = startAnimals;
        FileCreator fileCreator = new FileCreator();
        String [] data = new String[7];


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
        pauseButton.setBounds(30, 920, pbsize.width, pbsize.height);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ma.pause = true;
            }
        });

        resumeButton = new JButton("Resume");
        Dimension rbsize = resumeButton.getPreferredSize();
        resumeButton.setBounds(180, 920, rbsize.width, rbsize.height);
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ma.showDG = false;
                ma.pause = false;
            }
        });


        showDGButton = new JButton("Dominating Genotype");
        Dimension sbsize = showDGButton.getPreferredSize();
        showDGButton.setBounds(330, 920, sbsize.width,sbsize.height);
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



        writeToFileButton = new JButton("Write to file");
        Dimension wtfsize = writeToFileButton.getPreferredSize();
        writeToFileButton.setBounds(580, 920, wtfsize.width, wtfsize.height);
        writeToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ma.pause){
                    data[0] = Integer.toString(map.era);
                    data[1] = Integer.toString(averageNumberOfAnimals / map.era);
                    data[2] = Integer.toString(averageNumberOfGrass / map.era);
                    data[3] = findAverageDominatingGenotype();
                    data[4] = Integer.toString(averageEnergyLevel / map.era);
                    data[5] = Integer.toString(averageLongevity / map.era);
                    data[6] = Integer.toString(averageNumberOfChildren / map.era);
                    fileCreator.setData(data);
                    try {
                        fileCreator.createFileWithStats();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

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
        panel.add(writeToFileButton);
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
            getDataFromOneEra();
            map.newEra();
        }


    }

    private void getDataFromOneEra(){
        this.averageNumberOfAnimals += map.numberOfAnimals;
        this.averageNumberOfGrass += map.numberOfGrass;
        this.averageEnergyLevel += map.averageEnergyLevel;
        this.averageLongevity += map.averageLongevity;
        this.averageNumberOfChildren += map.averageNumberOfChildren;
        if(genotypes.containsKey(map.dominatingGenotype)) genotypes.replace(map.dominatingGenotype, genotypes.get(map.dominatingGenotype) + 1);
        else genotypes.put(map.dominatingGenotype, 1);
    }

    private String findAverageDominatingGenotype(){
        int counter = 0;
        String res = "";

        for(String s : genotypes.keySet()){
            if(genotypes.get(s) > counter){
                counter = genotypes.get(s);
                res = s;
            }
        }

        return res;
    }

}
