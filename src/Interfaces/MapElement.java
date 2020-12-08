package Interfaces;

import Classes.Vector2d;

public interface MapElement {

    void moveElement(Vector2d dir);

    Vector2d getPosition();
}
