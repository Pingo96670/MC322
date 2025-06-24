package lab.entities.bots.interfaces;

public interface FluidHandler {
    void refill(); // Refill the robot's fluid storage to max capacity
    void fill(int amount); // Refill the robot's fluid storage partially
    void printFluidLevel(); // Prints current level of fluid in storage
}