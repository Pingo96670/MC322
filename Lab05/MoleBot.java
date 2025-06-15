/* 
The MoleBot class is a special subclass of BaseRobot that moves in the underground to search for and collect gold.
*/

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MoleBot extends BaseRobot {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private int goldAmount = 0;
    
    public MoleBot(String name, int startX, int startZ, double sensorRadius) {
        super(name, startX, startZ, sensorRadius);
        specificTask();
    }

    // Randomly defines if gold was found
    private boolean foundGold() {
        return Math.random() < 0.2; // 20% of chance to find gold
    }

    // Since there are no obstacles in the underground, the MoleBot moves in a different way, ignoring the path and going to random spots
    // The underground's size is exactly the same of the environment, but the Y(height) axe goes from -1 to -(enrivonmentSizeY)
    public void move() {
        Random rand = new Random();
        int x = rand.nextInt(16);
        int z = rand.nextInt(16);
        int y = -rand.nextInt(15) - 1;

        String text = String.format("Robot %s is moving to position (%d, %d, %d).", getName(), x, y, z);
        Log.register(text);

        setPosX(x);
        setPosY(y);
        setPosZ(z);
        
        if(foundGold()) {
            goldAmount = goldAmount + 1;
            text = String.format("Robot %s found gold! Total gold collected: %d.", getName(), goldAmount);
            Log.register(text);
        }
    }

    public void specificTask() {
        executor.scheduleAtFixedRate(() -> {
            move();
        }, 10, 15, TimeUnit.SECONDS); 
    }

    public void shutdown() {
        executor.shutdown();
    }

    public String getDescription() {
        return "The MoleBot class is a special subclass of BaseRobot that moves in the underground to search for and collect gold.";
    }

    // Getters and setters
    public void setGoldAmount(int amount) {
        goldAmount = amount;
    }

    public int getGoldAmount() {
        return goldAmount;
    }
}
