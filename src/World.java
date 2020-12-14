import Classes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {
    public static void main(String [] args){

        try{
            HashMap<Vector2d, List<Animal>> animals = new HashMap<>();
            HashMap<Vector2d, Grass> grass = new HashMap<>();
            Map map = new Map(10, 10, 5, 5, animals, grass, 10,2);
            map.randomlyPlaceAnimals(6);

            int era = 10;

            while(era > 0){
                System.out.println(map.animals.keySet());
                System.out.println(map.animals.values());
                map.fillTheMap();
                System.out.println(map.toString());
                map.NewEra();
                era--;
                System.out.println("---------------------------------------------------------------------------------------------");
            }


        }catch (IllegalArgumentException exception){
            System.out.println(exception);
            System.exit(1);
        }

    }
}
