package Classes;

import Enums.*;
import Interfaces.*;

public class Animal implements MapElement{
    public int x;
    public int y;
    public int energy;
    public Direction direction;
    public Genes genes;

    public Animal(int x, int y, int energy){
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.direction = Direction.NORTH;
        this.genes = new Genes();
    }


}
