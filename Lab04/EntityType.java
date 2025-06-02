public enum EntityType {
    EMPTY(0, '0'),
    ROBOT(1, 'B'),
    GENERIC_OBSTACLE(2, 'G'),
    WATER(2, 'W'),
    ROCK(2, 'R'),
    MOUNTAIN(2, 'M'),
    TREE(2, 'T'),
    UNKNOWN(-1, 'U');

    private final int entityID;
    private final char entityChar;

    EntityType(int typeID, char typeChar) {
        entityID = typeID;
        entityChar = typeChar;
    }

    // Getters
    public int getEntityID() {
        return entityID;
    }

    public char getEntityChar() {
        return entityChar;
    }
}
