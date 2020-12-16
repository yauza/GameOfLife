package Interfaces;

import Classes.Vector2d;

public interface MapElement {

    void moveElement(Vector2d dir, boolean outOfBounds);

    Vector2d getPosition();
}
