package lab.entities.bots.interfaces;

// Specification of the FluidHandler interface
// Currently only implemented by the Camel Bot type
public interface WaterCarrier {
    boolean isNearWater();
    void goToWater();
}