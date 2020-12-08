package Interfaces;

import Classes.*;

public interface IMap {

    // placing an object on a map
    boolean place(MapElement animal);

    // checking the if the move is possible
    boolean canMoveTo(Vector2d position);

    // checks whether the position is occupied by other objects
    // at one position can be up to 1 grass and up to 4 animals
    boolean isOccupied(Vector2d position);

    // returns object at the given position
    Object objectAt(Vector2d position);
}
