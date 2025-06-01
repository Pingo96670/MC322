/*
The CamelRobot class is a subclass of GroundRobot capable of storing and transporting water.
 */

public class CamelRobot extends GroundRobot implements FluidHandler, WaterCarrier {
    private final int storageCapacity; // Max volume of water in the reserve (liters)
    private int waterLevel; // Volume of water currently in reserve (liters)
    private final WaterSensor waterSensor;

    // CamelRobot constructor
    public CamelRobot(String name, int posX, int posZ, int maxSpeed, int storageCapacity, double obstacleDistanceRadius, double waterDistanceRadius){
        super(name, posX, posZ, maxSpeed, obstacleDistanceRadius);
        this.setType("Camel Bot");
        this.storageCapacity = storageCapacity;
        waterLevel = 0;
        this.waterSensor = new WaterSensor(waterDistanceRadius);
    }

    @Override
    public void joinSensor() {
        super.joinSensor();
        this.waterSensor.setOwnerBot(this);
    }

    // Uses the WaterSensor to detect nearby water source
    public void searchWater() {
        this.waterSensor.monitor();
    }

    // Uses all sensors
    @Override
    public void activateSensors() {
        if(getState()==RobotState.OFF) {
            System.out.printf("%s is OFF and cannot perform this action.%n", getName());
        }
        else {
            super.activateSensors();
            searchWater();
        }
    }

    // Refill water storage to max capacity
    public void refill() {
        if(waterLevel<storageCapacity) {
            int amount = storageCapacity - waterLevel;
            fill(amount);
        }
        else {
            System.out.printf("%s's water storage level is already at full capacity.%n", getName());
        }
    }

    // Fills the robot's reserve with [amount]
    public void fill(int amount) {
        // Validating if [amount] is a valid input
        if (amount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
            System.out.println();
        // Validating if the reserve has enough capacity
        } else if ((waterLevel + amount) > storageCapacity) {
            System.out.println("Cannot add. Amount exceeds the storage capacity.");
            System.out.println();
        // Successful operation
        } else {
            waterLevel += amount;
            System.out.printf("Robot \"%s\"'s water reserve was filled with %d liters. Total reserve: %d liters.\n",
                    getName(), amount, waterLevel);
            System.out.println();
        }
    }

    // Empties the robot's reserve by [amount]
    public void emptyStorage(int amount) {
        // Validating if [amount] is a valid input
        if (amount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
            System.out.println();
        // Validating if the reserve has enough water
        } else if ((waterLevel - amount) < 0) {
            System.out.printf("The robot \"%s\" does not have enough water in its reserve. Current amount: %d liters.\n", getName(), getWaterLevel());
            System.out.println();
        // Successful operation
        } else {
            waterLevel -= amount;
            System.out.printf("The robot \"%s\"'s water reserve was emptied by %d liters. Total reserve: %d liters.\n",
            getName(), amount, waterLevel);
            System.out.println();
        }
    }

    // Prints the current water in the robot's reserve
    public void printFluidLevel() {
        System.out.printf("The robot \"%s\"'s water reserve contains %d liters.\n",
                getName(), waterLevel);
        System.out.println();
    }

    // Execute the robot's specific task, which is to try to refill the water storage if it's not full yet
    public void specificTask() {
        goToWater();
    }

    @Override
    // Searches for the closest water spot around the CamelRobot
    public void goToWater() {
        
        if(waterLevel<storageCapacity) {
            int x = getPosX();
            int z = getPosZ();
            int radius = (int)waterSensor.getDistanceRadius();
            int maxX = getEnvironment().getSizeX();
            int maxZ = getEnvironment().getSizeZ();
            int [][][]matrix = getEnvironment().getObstacleMatrix();

            int distance = Integer.MAX_VALUE; // Começa com max value, e se encontrar uma distância menor, ele atualiza o ponto minX e minZ da posição encontrada
            int closestX = -1;
            int closestZ = -1;

            for(int dx = -radius; dx <= radius; dx++) {
                for(int dz = -radius; dz <= radius; dz++) {
                    
                    // Verifies if the position is within environment's bonds
                    if((x+dx)>=0 && (z+dz)>=0 && (x+dx)<=maxX && (z+dz)<=maxZ) {
                        
                        // Verifies if there is a water obstacle in the position
                        if(matrix[x+dx][0][z+dz]==ObstacleType.WATER.getCode()) {

                            // Verifies if the water obstacle is closer than the previous found
                            if(((dx*dx)+(dz*dz))<distance) {
                                distance = (dx*dx)+(dz*dz);
                                closestX = x + dx;
                                closestZ = z + dz;
                            }
                        }
                    }
                }
            }
            if(closestX!=-1) {
                int dx = closestX - getPosX();
                int dz = closestZ - getPosZ();
                move(dx, dz);
                if(isNearWater()) {
                    refill();
                    return;
                }
                else {
                    System.out.printf("%s couldn't find a way to reach the nearby water spot.%n", getName());
                    return;
                }
            }
            else {
                System.out.printf("%s did'nt find any water spots nearby, the search for water stopped.%n", getName());
                return;
            }
        }
        else {
            System.out.printf("%s's water level is already at max capacity, no need to search for water.%n", getName());
            return;
        }
    }

    @Override
    public boolean isNearWater() {
        int[][][]matrix = getEnvironment().getObstacleMatrix();
        if((getPosX()+1)<=getEnvironment().getSizeX() && matrix[getPosX()+1][getPosZ()][getPosY()]==ObstacleType.WATER.getCode()) {
            return true;
        }
        if((getPosZ()+1)<=getEnvironment().getSizeZ() && matrix[getPosX()][getPosZ()+1][getPosY()]==ObstacleType.WATER.getCode()) {
            return true;
        }
        if((getPosX()-1)>=0 && matrix[getPosX()-1][getPosZ()][getPosY()]==ObstacleType.WATER.getCode()) {
            return true;
        }
        if((getPosZ()-1)>=0 && matrix[getPosX()][getPosZ()-1][getPosY()]==ObstacleType.WATER.getCode()) {
            return true;
        }
        return false;
    }

    // Getters and setters

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public WaterSensor getWaterSensor() {
        return waterSensor;
    }
}
