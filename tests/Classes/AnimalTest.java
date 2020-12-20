package Classes;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void moveElement() {
        HashMap<Vector2d, List<Animal>> animals = new HashMap<>();
        HashMap<Vector2d, Grass> grass = new HashMap<>();
        Map map = new Map(10, 10, 5, 5, animals, grass, 10,1,5);
        Animal a1 = new Animal(new Vector2d(2, 3), 10, map);
        Animal a2 = new Animal(new Vector2d(0, 0), 10, map);

        a1.moveElement(new Vector2d(0, 1), false);
        a2.moveElement(new Vector2d(9,9), true);

        assertEquals(a1.position, new Vector2d(2,4));
        assertEquals(a2.position, new Vector2d(9, 9));
    }

    @Test
    void isDead() {
        HashMap<Vector2d, List<Animal>> animals = new HashMap<>();
        HashMap<Vector2d, Grass> grass = new HashMap<>();
        Map map = new Map(10, 10, 5, 5, animals, grass, 10,1,5);
        Animal a1 = new Animal(new Vector2d(2, 3), 10, map);
        Animal a2 = new Animal(new Vector2d(0, 0), 0, map);
        Animal a3 = new Animal(new Vector2d(0, 0), -0.2, map);

        assertEquals(a1.isDead(), false);
        assertEquals(a2.isDead(), true);
        assertEquals(a3.isDead(), true);
    }
}