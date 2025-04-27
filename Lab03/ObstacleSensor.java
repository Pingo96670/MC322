/*
All robots have this sensor, it searches for obstacles in the environment on a radius distance around a robot and is used to check collisions before it moves.
 */

public class ObstacleSensor extends Sensor {
    
    public ObstacleSensor(double distanceRadius){
        super(distanceRadius);
    }

    // Verifies if there is an obstacle in the robot's direction
    public boolean isObstacleAhead(Environment environment, BaseRobot robot, String direction, double distance){
        int x = robot.getPosX();
        int z = robot.getPosZ();
        int y = robot.getPosY();
        int[][][] matrix = environment.getObstacleMatrix();
        int maxX = environment.getSizeX();
        int maxY = environment.getSizeY();
        int maxZ = environment.getSizeZ();
        
        for(int i = 1; i<= distance; ++i) {
            switch (direction) {
                case "North": z++; break;
                case "South": z--; break;
                case "East": x++; break;
                case "West": x--; break;
                case "Above": y++; break;
                case "Below": y--; break;
            }

            // Verifies if is out of environment's bounds
            if(x<0 || x>maxX || z<0 || z>maxZ || y<0 || y>maxY) {
                break;
            }

            if(matrix[x][y][z] != 0) {
                return true;
            }
        }
        
        return false;
    }

    // Verifies if there is an obstacle in all the robot's directions
    public boolean isObstacleNear(Environment environment, BaseRobot robot) {
        if(isObstacleAhead(environment, robot, "North", getDistanceRadius())) {
            return true;
        }

        if(isObstacleAhead(environment, robot, "South", getDistanceRadius())) {
            return true;
        }

        if(isObstacleAhead(environment, robot, "East", getDistanceRadius())) {
            return true;
        }

        if(isObstacleAhead(environment, robot, "West", getDistanceRadius())) {
            return true;
        }

        if(isObstacleAhead(environment, robot, "Above", getDistanceRadius())) {
            return true;
        }

        if(isObstacleAhead(environment, robot, "Below", getDistanceRadius())) {
            return true;
        }

        return false;

    }
    
    @Override
    // The robot verifies all directions in search of obstacles
    public void monitor() {
        Environment environment = BaseRobot.getEnvironment();
        BaseRobot robot = this.getOwnerBot();

        if(isObstacleNear(environment, robot)) {
            System.out.printf("%s detected a nearby obsctacle.\n", robot.getName());
        }
        else{
            System.out.printf("%s didn't detect a nearby obstacle in any direction.\n", robot.getName());
        }

        System.out.println();
    }
}
