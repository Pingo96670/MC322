package lab.entities;

public interface Entity {
    int getPosX();
    int getPosY();
    int getPosZ();
    EntityType getEntityType();
    String getDescription();
    char getRepChar();
}
