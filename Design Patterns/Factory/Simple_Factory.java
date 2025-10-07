// Main class to demonstrate the Simple Factory Pattern
public class Simple_Factory {

    public static void main(String[] args) {

        // Factory creates a Knife object based on the input type (Steak_Knife here)
        Knife k1 = Factory.Craete_Knives("Steak_Knife");

        // Perform operations on the created Knife object
        k1.Indentify();
        k1.Sharpening();
        k1.Packaging();
    }
}


// Knife interface acts as a contract for all types of knives
interface Knife {

    void Indentify();   // Method to identify the knife
    void Sharpening();  // Method to sharpen the knife
    void Packaging();   // Method to package the knife
}


// Concrete implementation of Knife: Chef_Knife
class Chef_Knife implements Knife {

    public void Indentify() {
        System.out.println("The Chef_Knife is identified");
    }

    public void Sharpening() {
        System.out.println("The Chef_Knife is sharpened");
    }

    public void Packaging() {
        System.out.println("The Chef_knife is packaged");
    }
}


// Concrete implementation of Knife: Steak_Knife
class Steak_Knife implements Knife {

    public void Indentify() {
        System.out.println("The Steak_Knife is identified");
    }

    public void Sharpening() {
        System.out.println("The Steak_Knife is sharpened");
    }

    public void Packaging() {
        System.out.println("The Steak_knife is packaged");
    }
}


// Factory class responsible for object creation
class Factory {

    // Static method that decides which Knife object to create based on input
    public static Knife Craete_Knives(String type) {

        if (type.equalsIgnoreCase("Chef_knife")) {
            // Create and return a Chef_Knife object
            return new Chef_Knife();
        }

        else if (type.equalsIgnoreCase("Steak_Knife")) {
            // Create and return a Steak_Knife object
            return new Steak_Knife();
        }

        else {
            // Return null if input type doesn't match any known Knife
            return null;
        }
    }
}
