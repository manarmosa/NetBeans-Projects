package car;

public class CarDrive {

    private double fueleffesency;
    private double fuel;

    public CarDrive(double fueleffesency) {
        this.fueleffesency = fueleffesency;
        fuel = 0;
    }

    public double getGasInTent() {
        return fuel;
    }

    public void addGas(double gas) {
        fuel = fuel + gas;
    }
    public void drive(double distance){
        fuel = fuel - distance/(fueleffesency);
    }
}
