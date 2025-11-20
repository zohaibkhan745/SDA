
class Engine {
    public void start() {
        System.out.println("Engine starting...");
    }
}

class Driver {
    public void sit() {
        System.out.println("Driver sits in the car.");
    }
}

class Cleaner {
    public void wash() {
        System.out.println("Cleaning the car...");
    }
}

class Car {
    private Engine engine = new Engine();

    public void drive() {
        checkFuel();
        engine.start();
        System.out.println("Car is driving...");
    }

    private void checkFuel() {
        System.out.println("Fuel OK.");
    }

    public void allowDriver(Driver driver) {
        driver.sit();
    }

    public void washCar() {
        Cleaner cleaner = new Cleaner();
        cleaner.wash();
    }
}

public class FollowLawOfDemeter {


    public static void main(String[] args) {
        Car car = new Car();
        car.drive();

        Driver d = new Driver();
        car.allowDriver(d);

        car.washCar();
    }
    
}



