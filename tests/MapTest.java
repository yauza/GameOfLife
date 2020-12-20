import Classes.Animal;
import Classes.Grass;
import Classes.Map;
import Classes.Vector2d;
import Enums.Direction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {


    @Test
    void fillTheMap() {
        HashMap<Vector2d, List<Animal>> animals = new HashMap<>();
        HashMap<Vector2d, Grass> grass = new HashMap<>();
        Map map = new Map(10, 10, 5, 5, animals, grass, 10,1, 5);

        map.place(new Animal(new Vector2d(2, 3), 10, map));
        map.place(new Grass(new Vector2d(4, 7), 10));

        map.fillTheMap();
        assertEquals(map.area[2][3], "10.0");
        assertEquals(map.area[4][7], "*");
    }

    @Test
    void place() {
        HashMap<Vector2d, List<Animal>> animals = new HashMap<>();
        HashMap<Vector2d, Grass> grass = new HashMap<>();
        Map map = new Map(10, 10, 5, 5, animals, grass, 10,1, 5);
        Animal a1 = new Animal(new Vector2d(2, 3), 10, map);
        Animal a2 = new Animal(new Vector2d(2, 5), 10, map);
        map.place(a1);
        map.place(a2);
        assertEquals(map.isOccupied(new Vector2d(2, 3)), true);
        assertEquals(map.isOccupied(new Vector2d(4, 7)), false);
        assertEquals(map.isOccupied(new Vector2d(2, 4)), false);
    }

    @Test
    void newEra(){
        HashMap<Vector2d, List<Animal>> animals = new HashMap<>();
        HashMap<Vector2d, Grass> grass = new HashMap<>();
        Map map = new Map(10, 10, 5, 5, animals, grass, 10,1, 5);
        Animal a1 = new Animal(new Vector2d(2, 3), 10, map);
        Animal a2 = new Animal(new Vector2d(2, 5), 10, map);

        map.place(a1);
        map.place(a2);

        map.newEra();

        int animalCounter = 0;


        for(List<Animal> l : animals.values()){
            for(Animal a : l) {
                System.out.println(a);
                animalCounter++;
            }
        }


    }
}