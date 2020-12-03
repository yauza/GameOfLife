package Interfaces;

import Classes.*;

public interface IMap {

    boolean place(MapElement animal);

    boolean canMoveTo(Vector2d position);

    boolean isOccupied(Vector2d position);
}
