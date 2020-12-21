package Interfaces;

import Classes.*;

public interface IMap {

    // placing an object on a map
    boolean place(MapElement animal);

    // checks whether the position is occupied by other objects
    // at one position can be up to 1 grass and up to 4 animals
    boolean isOccupied(Vector2d position);

}
