package Classes;

import Interfaces.MapElement;

public class Grass implements MapElement {
    public Vector2d position;
    public int energy;

    public Grass(int x, int y, int energy){
        this.position.x = x;
        this.position.y = y;
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
