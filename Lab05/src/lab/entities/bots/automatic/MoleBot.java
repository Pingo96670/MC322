package lab.entities.bots.automatic;/*
The MoleBot class is a special subclass of BaseRobot that moves in the underground to search for and collect gold.
*/

import lab.entities.bots.BaseRobot;
import lab.system.Log;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MoleBot extends BaseRobot {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private int goldAmount = 0;
    private boolean isRunning = false;
    private static final String MOLE_BOT_LOG_PATH = "bin/lab/MoleBotLog.txt";
    
    public MoleBot(String name, int startX, int startZ, double sensorRadius) {
        super(name, startX, startZ, sensorRadius);
        setIsAutomatic(true);

        Log.resetLogFile(MOLE_BOT_LOG_PATH);
    }

    // Randomly defines if gold was found
    private boolean foundGold() {
        return Math.random() < 0.2; // 20% of chance to find gold
    }

    // The MoleBot moves differently from other robots, since the underground has no obstacles, instead of following a path, it randomly teleports to a valid underground position.
    // The underground shares the same X and Z dimensions as the environment, but it's Y axis ranges from -1 to -environmentSizeY.
    public void move() {
        Random rand = new Random();
        int x = rand.nextInt(16);
        int z = rand.nextInt(16);
        int y = -rand.nextInt(15) - 1;

        String text = String.format("Robot %s is moving to position (%d, %d, %d).", getName(), x, y, z);
        Log.register(MOLE_BOT_LOG_PATH, text);

        setPosX(x);
        setPosY(y);
        setPosZ(z);
        
        if(foundGold()) {
            goldAmount = goldAmount + 1;
            text = String.format("Robot %s found gold! Total gold collected: %d.", getName(), goldAmount);
            Log.register(MOLE_BOT_LOG_PATH, text);
        }
    }

    // Randomly searches for gold at specific intervals
    public void specificTask() {
        // Schedules the move function to run periodically:
        // it starts 10 seconds after the MoleBot is created, and repeats every 5 seconds after.
        executor.scheduleAtFixedRate(() -> {
            move();
        }, 10, 5, TimeUnit.SECONDS);
    }

    // Ends execution of the thread running Mole Bot
    public void shutdown() {
        executor.shutdown();
    }

    public String getDescription() {
        return "The MoleBot class is a special subclass of BaseRobot that moves in the underground to search for and collect gold.";
    }

    // Getters and setters
    public int getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(int amount) {
        goldAmount = amount;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean bool) {
        isRunning = bool;
    }
}
