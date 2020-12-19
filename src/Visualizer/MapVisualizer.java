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
    public MapAnimation mapAnimation;

    Timer tm = new Timer(5, this);
    int x = 0;

    public JFrame simulation;
    public JPanel panel;

    public MapAnimation ma;

    // buttons
    private JButton pauseButton;
    private JButton resumeButton;

    private JLabel animalsText;
    private JLabel grassText;

    private JLabel animalsNumber;
    private JLabel grassNumber;

    private int numberOfAnimals;
    private int numberOfGrass;

    public MapVisualizer(Map map){
        this.map = map;

        simulation = new JFrame("Simulation");
        //GridLayout layout = new GridLayout(2, 1);
        //FlowLayout layout = new FlowLayout();
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        //simulation.setLayout(layout);

        panel = new JPanel();
        //panel.setPreferredSize(new Dimension(500, 100));
        panel.setBackground(new Color(0x00FF00FF));
        //panel.setBounds(0, 0, 500, 100);
        //simulation.getContentPane();

        ma = new MapAnimation(map, this);
        ma.setSize(new Dimension(600, 600));


        // ---------------------------------------------------------

        pauseButton = new JButton("Pause");
        Dimension pbsize = pauseButton.getPreferredSize();
        pauseButton.setBounds(100, 650, pbsize.width, pbsize.height);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ma.pause = true;
            }
        });

        resumeButton = new JButton("Resume");
        Dimension rbsize = resumeButton.getPreferredSize();
        resumeButton.setBounds(96, 700, rbsize.width, rbsize.height);
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ma.pause = false;
            }
        });

        // ---------------------------------------------------------

        animalsText = new JLabel("Number of animals: ");
        Dimension atsize = animalsText.getPreferredSize();
        animalsText.setBounds(300, 650, atsize.width, atsize.height);

        animalsNumber = new JLabel(Integer.toString(this.numberOfAnimals));
        Dimension ansize = animalsNumber.getPreferredSize();
        animalsNumber.setBounds(320 + atsize.width, 650, ansize.width, ansize.height);

        // ---------------------------------------------------------

        panel.setLayout(null);
        panel.add(pauseButton);
        panel.add(resumeButton);
        panel.add(animalsText);
        panel.add(animalsNumber);
        simulation.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        simulation.add(ma);
        simulation.add(panel);
        simulation.setSize(600, 800);
        simulation.setVisible(true);

    }

    public void startTheSimulation(){
        map.randomlyPlaceAnimals(10);
        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == pauseButton) ma.pause = true;
//        else if(e.getSource() == resumeButton) ma.pause = false;
        if(!ma.pause) {
            ma.repaint();
            map.newEra();
        }

        //x++;
        //repaint();
    }
}
