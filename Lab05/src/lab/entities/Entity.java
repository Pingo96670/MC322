package lab.entities;

// Related to ambient objects, like robots and obstacles
public interface Entity {
    int getPosX();
    int getPosY();
    int getPosZ();
    EntityType getEntityType();
    String getDescription();
    char getRepChar();
}
