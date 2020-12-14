package Classes;

import Interfaces.MapElement;

public class Grass implements MapElement {
    public Vector2d position;
    public double energy;

    public Grass(Vector2d position, double energy){
        this.position = position;
        this.energy = energy;
    }

    @Override
    public void moveElement(Vector2d dir) {

    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }
}
