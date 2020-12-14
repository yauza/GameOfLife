package Classes;

import Enums.*;
import Interfaces.*;

import java.util.Objects;

public class Animal implements MapElement, MapElementObservable, Comparable<Animal>{
    public Vector2d position;
    public Vector2d lastPosition;
    public double energy;
    public Direction direction;
    public Genes genes;
    public ObserverOfMapElements map;
    public int numberOfChildren;

    public Animal(Vector2d position, double energy, Map map){
        this.position = position;
        this.energy = energy;
        this.genes = new Genes();
        this.direction = genes.calculateDirection();
        this.numberOfChildren = 0;
        this.map = map;
    }

    public String toString(){
        return position.toString() + " energy: " + energy;
    }


    @Override
    public void moveElement(Vector2d dir) {
        Vector2d lastPos = new Vector2d(this.position.x, this.position.y);
//        this.lastPosition.x = this.position.x;
//        this.lastPosition.y = this.position.y;
        this.lastPosition = lastPos;
        this.position.x += dir.x;
        this.position.y += dir.y;
        notifyObserver(this.map);
    }


    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public boolean isDead() {return this.energy <= 0;}

    @Override
    public void notifyObserver(ObserverOfMapElements map) {
        map.update(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return energy == animal.energy &&
                Objects.equals(position, animal.position) &&
                direction == animal.direction &&
                Objects.equals(genes, animal.genes) &&
                Objects.equals(map, animal.map);
    }

    public int compareTo(Animal a){
        if(this.energy > a.energy) return -1;
        else if (this.energy == a.energy) return 0;
        else return 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, energy, direction, genes, map);
    }
}
