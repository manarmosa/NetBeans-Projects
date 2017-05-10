
package car;

public class Car {

    public static void main(String[] args) {
        CarDrive myHybrid = new CarDrive(50); // 50 miles per gallon
myHybrid.addGas(100); // Tank 20 gallons
myHybrid.drive(20);
System.out.println(myHybrid.getGasInTent());
    }
    
}
