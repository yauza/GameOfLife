import Classes.*;
import Visualizer.FileCreator;
import Visualizer.MapVisualizer;
import Visualizer.ParametersLoader;
import Visualizer.StartMenu;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {
    public static void main(String [] args){

        try{

            ParametersLoader loader = new ParametersLoader();
            loader.loadParameters();
            StartMenu menu = new StartMenu();
            menu.setParameters(loader);                       // <- comment this line to use StartMenu without parameters from JSON file



        }catch (IllegalArgumentException exception){
            System.out.println(exception);
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
