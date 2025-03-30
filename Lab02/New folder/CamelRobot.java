/*
    A ideia é que CamelRobot seja um robô terrestre capaz de armazenar e transportar água.
 */

public class CamelRobot extends GroundRobot{
    private int storageCapacity; //volume de água comportado em L
    private int waterLevel; //volume de água armazenda em L

    public CamelRobot(String name, int posX, int posZ, int maxSpeed, int storageCapacity){
        super(name, posX, posZ, maxSpeed);
        this.storageCapacity = storageCapacity;
        waterLevel = 0;
    }

    public int getStorageCapacity(){
        return storageCapacity;
    }

    public int getWaterLevel(){
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel){
        this.waterLevel = waterLevel;
    }

    public void fillStorage(int amount){
        if((waterLevel+amount)<=waterLevel){
            waterLevel += amount;
            System.out.printf
            ("%s's water storage filled with %d liters. Total reserve: %d liters.\n", 
            getName(), amount, waterLevel);
        }
        else{
            System.out.printf("Cannot add, the amount exceeds the storage capacity.\n");
        }
    }

    public void emptyStorage(int amount){
        if((waterLevel-amount)<0){
            System.out.printf("%s hasn't enough water in storage.\n");
        }
        else{
            waterLevel -= amount;
            System.out.printf
            ("%s's water storage was emptied by %d liters. Total reserve: %d liters.\n", 
            getName(), amount, waterLevel);
        }
    }
}
