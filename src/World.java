import Classes.*;
import Visualizer.MapVisualizer;
import Visualizer.StartMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {
    public static void main(String [] args){

        try{
            HashMap<Vector2d, List<Animal>> animals = new HashMap<>();
            HashMap<Vector2d, Grass> grass = new HashMap<>();
            Map map = new Map(10, 10, 5, 5, animals, grass, 10,1, 5);
            map.randomlyPlaceAnimals(5);

            int era = 10;

//            while(era > 0){
//                System.out.println(map.animals.keySet());
//                System.out.println(map.animals.values());
//                map.fillTheMap();
//                System.out.println(map.toString());
//                map.newEra();
//                era--;
//                System.out.println("---------------------------------------------------------------------------------------------");
//            }

            StartMenu menu = new StartMenu();



        }catch (IllegalArgumentException exception){
            System.out.println(exception);
            System.exit(1);
        }

    }
}
