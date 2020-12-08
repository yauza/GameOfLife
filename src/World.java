import Classes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {
    public static void main(String [] args){
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal(2, 3, 10));
        animals.add(new Animal(4, 4, 10));
        animals.add(new Animal(0, 1, 10));

        List<Grass> grass = new ArrayList<>();
        grass.add(new Grass(2,4,10));
        grass.add(new Grass(1,1,10));
        grass.add(new Grass(2,1,10));

        HashMap<Vector2d, List<Animal>> A;

       // Map map = new Map(5, 5,1,1, animals, grass);
        //System.out.println(map);

    }
}
