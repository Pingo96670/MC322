/*
The CamelRobot class is a subclass of GroundRobot capable of storing and transporting water.
 */

public class CamelRobot extends GroundRobot implements FluidHandler, WaterCarrier {
    private final int storageCapacity; // Max volume of water in the reserve (liters)
    private int waterLevel = 0; // Volume of water currently in reserve (liters)
    private final WaterSensor waterSensor;

    // CamelRobot constructor
    public CamelRobot(String name, int posX, int posZ, int maxSpeed, int storageCapacity, double obstacleSensorRadius, double waterSensorRadius){
        super(name, posX, posZ, maxSpeed, obstacleSensorRadius);
        this.setType("Camel Bot");
        this.storageCapacity = storageCapacity;
        this.waterSensor = new WaterSensor(waterSensorRadius);
    }

    @Override
    public void joinSensor() {
        super.joinSensor();
        this.waterSensor.setOwnerBot(this);
    }

    // Uses the WaterSensor to detect nearby water source
    public void searchWater() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        this.waterSensor.monitor();
    }

    // Uses all sensors
    @Override
    public void activateSensors() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        if(getState()==RobotState.OFF) {
            System.out.printf("%s is OFF and cannot perform this action.%n", getName());
        }
        else {
            super.activateSensors();
            searchWater();
        }
    }

    // Fills the robot's reserve with [amount]
    public void fill(int amount) throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

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
        }
    }

    // Refill water storage to max capacity
    public void refill() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        if(waterLevel<storageCapacity) {
            int amount = storageCapacity - waterLevel;
            fill(amount);
        }
        else {
            System.out.printf("%s's water storage level is already at full capacity.%n", getName());
        }
    }

    // Empties the robot's reserve by [amount]
    public void emptyStorage(int amount) throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        // Validating if [amount] is a valid input
        if (amount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
        // Validating if the reserve has enough water
        } else if ((waterLevel - amount) < 0) {
            System.out.printf("The robot \"%s\" does not have enough water in its reserve. Current amount: %d liters.\n", getName(), getWaterLevel());
        // Successful operation
        } else {
            waterLevel -= amount;
            System.out.printf("The robot \"%s\"'s water reserve was emptied by %d liters. Total reserve: %d liters.\n",
            getName(), amount, waterLevel);
        }
    }

    // Prints the current water in the robot's reserve
    public void printFluidLevel() {
        System.out.printf("The robot \"%s\"'s water reserve contains %d liters.\n",
                getName(), waterLevel);
        System.out.println();
    }

    @Override
    public boolean isNearWater() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        EntityType[][][]matrix = getEnvironment().getEntityTypeMatrix();

        // Checks for water in the orthogonally adjacent position (North, South, East, West, respectively)
        if((getPosZ()+1)<=getEnvironment().getSizeZ() && matrix[getPosX()][getPosY()][getPosZ()+1]==EntityType.WATER) {
            return true;
        }

        if((getPosZ()-1)>=0 && matrix[getPosX()][getPosY()][getPosZ()-1]==EntityType.WATER) {
            return true;
        }

        if((getPosX()+1)<=getEnvironment().getSizeX() && matrix[getPosX()+1][getPosY()][getPosZ()]==EntityType.WATER) {
            return true;
        }

        if((getPosX()-1)>=0 && matrix[getPosX()-1][getPosY()][getPosZ()]==EntityType.WATER) {
            return true;
        }

        return false;
    }

    @Override
    // Searches for the closest water spot around the CamelRobot
    public void goToWater() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        if(waterLevel<storageCapacity) {
            int x = getPosX();
            int z = getPosZ();
            int radius = (int)waterSensor.getSensorRadius();
            int maxX = getEnvironment().getSizeX();
            int maxZ = getEnvironment().getSizeZ();
            EntityType[][][]matrix = getEnvironment().getEntityTypeMatrix();

            int distance = Integer.MAX_VALUE; // Starts with max value, and updates mixX and minZ if a new nearest position is found
            int closestX = -1;
            int closestZ = -1;

            for(int dx = -radius; dx <= radius; dx++) {
                for(int dz = -radius; dz <= radius; dz++) {

                    // Verifies if the position is within environment's bonds
                    if((x+dx)>=0 && (z+dz)>=0 && (x+dx)<=maxX && (z+dz)<=maxZ) {

                        // Verifies if there is a water obstacle in the position
                        if(matrix[x+dx][0][z+dz]==EntityType.WATER) {

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

                if(dx==0) {
                    if(dz>0) {
                        move(dx, dz-1);
                    }
                    if(dz<0) {
                        move(dx, dz+1);
                    }
                }

                if(dx<0) {
                    move(dx+1, dz);
                }

                if(dx>0) {
                    move(dx-1, dz);
                }

                if(isNearWater()) {
                    refill();
                } else {
                    System.out.printf("%s couldn't find a way to reach the nearby water spot.%n", getName());
                }
            } else {
                System.out.printf("%s didn't find any water spots nearby.%n", getName());
            }
        } else {
            System.out.printf("%s's water level is already at max capacity, no need to search for water.%n", getName());
        }
    }

    // Execute the robot's specific task, which is to try to refill the water storage if it's not full yet
    public void specificTask() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        goToWater();
    }

    @Override
    public String getDescription() {
        return "The CamelRobot class is a subclass of GroundRobot capable of storing and transporting water.";
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
