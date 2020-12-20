package Visualizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Classes.*;
import Enums.*;
import Interfaces.*;

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

    public MapVisualizer(Map map, int startAnimals){
        this.map = map;
        this.startAnimals = startAnimals;

        simulation = new JFrame("Simulation");
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

        panel = new JPanel();
        //panel.setPreferredSize(new Dimension(500, 100));
        panel.setBackground(new Color(0x00FF00FF));
        panel.setBounds(0, 900, 800, 100);
        //simulation.getContentPane();

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
        simulation.setVisible(true);

    }

    public void startTheSimulation(){
        map.randomlyPlaceAnimals(startAnimals);
        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == pauseButton) ma.pause = true;
//        else if(e.getSource() == resumeButton) ma.pause = false;
        if(!ma.pause) {
            ma.repaint();
            ms.repaint();
            map.newEra();
        }


        //x++;
        //repaint();
    }
}
