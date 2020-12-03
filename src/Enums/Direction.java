package Enums;

public enum Direction {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH,
    SOUTHWEST, WEST, NORTHWEST;

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
