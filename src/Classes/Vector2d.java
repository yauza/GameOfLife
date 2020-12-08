package Classes;

import Enums.Direction;

public class Vector2d {
    public int x;
    public int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2d toVector(Direction dir){
        return switch(dir){
            case NORTH -> new Vector2d(0, 1);
            case NORTHEAST -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case SOUTHEAST -> new Vector2d(1,-1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTHWEST -> new Vector2d(-1,-1);
            case WEST -> new Vector2d(-1,0);
            case NORTHWEST -> new Vector2d(-1,1);
            default -> new Vector2d(0,0);
        };
    }
}
