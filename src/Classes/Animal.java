package Classes;

import Enums.*;
import Interfaces.*;

import java.util.Objects;

public class Animal implements MapElement, MapElementObservable{
    public Vector2d position;
    public Vector2d lastPosition;
    public int energy;
    public Direction direction;
    public Genes genes;
    public ObserverOfMapElements map;

    public Animal(int x, int y, int energy){
        this.position.x = x;
        this.position.y = y;
        this.energy = energy;
        this.genes = new Genes();
        this.direction = genes.calculateDirection();
    }


    @Override
    public void moveElement(Vector2d dir) {

        this.lastPosition.x = this.position.x;
        this.lastPosition.y = this.position.y;
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

    @Override
    public int hashCode() {
        return Objects.hash(position, energy, direction, genes, map);
    }
}
