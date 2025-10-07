// Demonstration of the Factory Method Design Pattern
public class Factory_Pattern {

    // Order method acts as the client request
    public static void Order(String budgetType, String knifeType) {

        // Step 1: Ask Factory_Producer to provide the correct factory 
        // based on the user's budget (High or Low)
        Factory k_factory = Factory_Producer.getFactory(budgetType);

        // Step 2: Handle invalid factory type
        if (k_factory == null) {
            System.out.println("Invalid budget type specified. Cannot create factory.");
            return;
        }

        // Step 3: Request the factory to create the desired knife
        Knife n1 = k_factory.Create_Knives(knifeType);

        // Step 4: Handle invalid knife type
        if (n1 == null) {
            System.out.println("Invalid knife type specified.");
            return;
        }

        // Step 5: Perform operations on the created knife
        n1.Identify();
        n1.Sharpening();
        n1.Packaging();
    }

    // Main driver method
    public static void main(String[] args) {
        System.out.println("--- Ordering a High Budget Chef Knife ---");
        Order("High", "Chef_Knife");

        System.out.println("\n--- Ordering a Low Budget Steak Knife ---");
        Order("Low", "Steak_Knife");
    }
}

/*------------------------------ FACTORY PRODUCER ------------------------------*/
// Factory_Producer decides which factory (High or Low budget) to return
class Factory_Producer {
    public static Factory getFactory(String budgetType) {
        if (budgetType.equalsIgnoreCase("High")) {
            return new High_budget_Factory();
        } else if (budgetType.equalsIgnoreCase("Low")) {
            return new Low_budget_Factory();
        } else {
            return null; // invalid input
        }
    }
}

/*------------------------------ FACTORY INTERFACE ------------------------------*/
// Common interface for all concrete factories
interface Factory {
    Knife Create_Knives(String type); // Method to create knife objects
}

/*------------------------------ LOW BUDGET FACTORY ------------------------------*/
// Factory that creates low-budget knife variants
class Low_budget_Factory implements Factory {
    public Knife Create_Knives(String type) {
        if (type.equalsIgnoreCase("Chef_Knife")) {
            return new Low_Budget_Chef_Knife();
        } else if (type.equalsIgnoreCase("Steak_Knife")) {
            return new Low_Budget_Steak_Knife();
        } else return null;
    }
}

/*------------------------------ HIGH BUDGET FACTORY ------------------------------*/
// Factory that creates high-budget knife variants
class High_budget_Factory implements Factory {
    public Knife Create_Knives(String type) {
        if (type.equalsIgnoreCase("Chef_Knife")) {
            return new High_Budget_Chef_Knife();
        } else if (type.equalsIgnoreCase("Steak_Knife")) {
            return new High_Budget_Steak_Knife();
        } else return null;
    }
}

/*------------------------------ PRODUCT INTERFACE ------------------------------*/
// Common interface for all knives (product family)
interface Knife {
    void Identify();
    void Sharpening();
    void Packaging();
}

/*------------------------------ LOW BUDGET KNIFE VARIANTS ------------------------------*/
class Low_Budget_Chef_Knife implements Knife {
    public void Identify() { System.out.println("The knife is identified as LBCK"); }
    public void Sharpening() { System.out.println("The low budget chef knife is Sharpened"); }
    public void Packaging() { System.out.println("The low budget chef_knife is packaged"); }
}

class Low_Budget_Steak_Knife implements Knife {
    public void Identify() { System.out.println("The knife is identified as LBSK"); }
    public void Sharpening() { System.out.println("The low budget Steak knife is Sharpened"); }
    public void Packaging() { System.out.println("The low budget Steak_knife is packaged"); }
}

/*------------------------------ HIGH BUDGET KNIFE VARIANTS ------------------------------*/
class High_Budget_Chef_Knife implements Knife {
    public void Identify() { System.out.println("The knife is identified as HBCK"); }
    public void Sharpening() { System.out.println("The High budget chef knife is Sharpened"); }
    public void Packaging() { System.out.println("The High budget chef_knife is packaged"); }
}

class High_Budget_Steak_Knife implements Knife {
    public void Identify() { System.out.println("The knife is identified as HBSK"); }
    public void Sharpening() { System.out.println("The High budget Steak knife is Sharpened"); }
    public void Packaging() { System.out.println("The High budget Steak_knife is packaged"); }
}
