/*
Only CamelRobots have this sensor, it's used to search the nearest water spot in a distance radius
 */

public class WaterSensor extends Sensor {
    
    public WaterSensor(double sensorRadius) {
        super(sensorRadius);
    }

    @Override
    // Searches for the closest water spot around the CamelRobot
    public void monitor() {
        Environment environment = BaseRobot.getEnvironment();
        BaseRobot robot = this.getOwnerBot();

        int x = robot.getPosX();
        int z = robot.getPosZ();
        int radius = (int) getSensorRadius();
        int maxX = environment.getSizeX();
        int maxZ = environment.getSizeZ();
        int [][][]matrix = environment.getObstacleMatrix();

        int distance = Integer.MAX_VALUE; // Starts with max integer value, then updates minX and minZ if a shorter distance is found
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

        if(closestX != -1) {
            System.out.printf("%s found a water spot at position (%d, 0, %d).\n", robot.getName(), closestX, closestZ);
        }
        else {
            System.out.printf("%s didn't find any water spot nearby.\n", robot.getName());
        }
    }
}
