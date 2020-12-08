package Enums;

public enum Direction {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH,
    SOUTHWEST, WEST, NORTHWEST;

    public Direction fromNumber(int num){
        switch (num) {
            case 0:
                return NORTH;
            case 1:
                return NORTHEAST;
            case 2:
                return EAST;
            case 3:
                return SOUTHEAST;
            case 4:
                return SOUTH;
            case 5:
                return SOUTHWEST;
            case 6:
                return WEST;
            case 7:
                return NORTHWEST;
        }
        return NORTH;
    }

    public int toNumber(Direction dir){
        switch (this) {
            case NORTH:
                return 0;
            case NORTHEAST:
                return 1;
            case EAST:
                return 2;
            case SOUTHEAST:
                return 3;
            case SOUTH:
                return 4;
            case SOUTHWEST:
                return 5;
            case WEST:
                return 6;
            case NORTHWEST:
                return 7;
        }
        return -1;
    }

    public Direction next() {
        switch (this) {
            case NORTH:
                return NORTHEAST;
            case SOUTH:
                return SOUTHWEST;
            case WEST:
                return NORTHWEST;
            case EAST:
                return SOUTHEAST;
            case NORTHWEST:
                return NORTH;
            case SOUTHWEST:
                return WEST;
            case NORTHEAST:
                return EAST;
            case SOUTHEAST:
                return SOUTH;
        }
        return null;
    }

    public Direction previous() {
        switch (this) {
            case NORTH:
                return NORTHWEST;
            case SOUTH:
                return SOUTHEAST;
            case WEST:
                return SOUTHWEST;
            case EAST:
                return NORTHEAST;
            case NORTHWEST:
                return WEST;
            case SOUTHWEST:
                return SOUTH;
            case NORTHEAST:
                return NORTH;
            case SOUTHEAST:
                return EAST;
        }
        return null;
    }
}
