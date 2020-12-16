package Classes;

import Interfaces.MapElement;

import java.util.Objects;

public class Grass implements MapElement {
    public Vector2d position;
    public double energy;

    public Grass(Vector2d position, double energy){
        this.position = position;
        this.energy = energy;
    }

    public String toString(){
        return this.position.toString() + " energy: " + this.energy;
    }

    @Override
    public void moveElement(Vector2d dir, boolean outOfBounds) {

    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grass)) return false;
        Grass grass = (Grass) o;
        return Double.compare(grass.energy, energy) == 0 &&
                Objects.equals(position, grass.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, energy);
    }
}
