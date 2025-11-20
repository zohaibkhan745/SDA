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
    private Cleaner cleaner = new Cleaner();
    private Driver driver = new Driver();

    public Engine getEngine() {
        return engine;
    }

    public Cleaner getCleaner() {
        return cleaner;
    }

    public Driver getDriver() {
        return driver;
    }

    private void checkFuel() {
        System.out.println("Fuel OK.");
    }
}


public class ViolateLawOfDemetere {

    public static void main(String[] args) {
        Car car = new Car();

        // Directly calling methods of sub-objects (violates Law of Demeter)
        car.getEngine().start();
        car.getDriver().sit();
        car.getCleaner().wash();
    }
}
