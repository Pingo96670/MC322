package lab.entities.obstacles;

import lab.entities.Entity;
import lab.entities.EntityType;
import lab.generalexceptions.NoSuchObjectTypeException;

public class Obstacle implements Entity {
    private final int posX1;    // X coordinate of southwest corner
    private final int posX2;    // Z coordinate of southwest corner
    private final int posZ1;    // X coordinate of northeast corner
    private final int posZ2;    // Z coordinate of northeast corner
    private final int height;   // Obstacle's height
    private final ObstacleType type;    // Obstacle's type
    private final EntityType entityType;    // Obstacle's entity type

    // Constructor
    public Obstacle(ObstacleType type, int x1, int z1) {
        this.type = type;
        this.posX1 = x1;
        this.posZ1 = z1;
        this.posX2 = x1 + type.getWidth();
        this.posZ2 = z1 + type.getDepth();
        this.height = type.getHeight();

        switch (type) {
            case WATER -> entityType = EntityType.WATER;

            case ROCK -> entityType = EntityType.ROCK;

            case MOUNTAIN -> entityType = EntityType.MOUNTAIN;

            case TREE -> entityType = EntityType.TREE;

            default -> throw new NoSuchObjectTypeException("Assigned ObstacleType element does not exist in ObstacleType file.");
        }
    }

    // Returns the general obstacle description
    @Override
    public String getDescription() {
        return """
                Obstacles are objects within the environment which block a robot's movement.
                Each obstacle type has its own dimensions and representing character.
                All obstacles are grounded (y1 = 0).
                """;
    }

    // Returns the obstacle's representing character
    @Override
    public char getRepChar() {
        return type.getRepChar();
    }

    // Getters for positions of main point
    @Override
    public int getPosX() {
        return posX1;
    }

    @Override
    public int getPosY() {
        return 0;
    }

    @Override
    public int getPosZ() {
        return posZ1;
    }

    // Getters
    public int getPosX1() {
        return posX1;
    }

    public int getPosX2() {
        return posX2;
    }

    public int getPosZ1() {
        return posZ1;
    }

    public int getPosZ2() {
        return posZ2;
    }

    public int getHeight() {
        return height;
    }

    public ObstacleType getType() {
        return type;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }
}
