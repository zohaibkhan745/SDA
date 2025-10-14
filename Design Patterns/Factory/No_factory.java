// Main class to demonstrate object creation without using a Factory
public class No_factory {

    public static void main(String[] args) {

        // Directly creating an object of Chef_Knife (tight coupling)
        Knife k1 = new Chef_Knife();

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
