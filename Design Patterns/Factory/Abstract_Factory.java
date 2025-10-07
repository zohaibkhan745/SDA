// Main class demonstrating Abstract Factory Pattern
public class Abstract_Factory {

    // Order method demonstrates how client code interacts with factories
    public static void Order(String budgetType, String knifeType) {

        // Step 1: Get a factory object based on budget (High or Low)
        Factory k_factory = Factory_Producer.getFactory(budgetType);

        if (k_factory == null) {
            System.out.println("Invalid budget type specified. Cannot create factory.");
            return;
        }

        // Step 2: Ask the factory to create a knife of the given type
        Knife n1 = k_factory.Create_Knives(knifeType);

        if (n1 == null) {
            System.out.println("Invalid knife type specified.");
            return;
        }

        // Step 3: Use the created knife
        n1.Identify();
        n1.Sharpening();
        n1.Packaging();

        // Step 4: Also ask the factory to create a sheath for the knife
        Sheath s1 = k_factory.Create_Sheath("Sheath");
        s1.createsheath();
    }

    // Main driver function
    public static void main(String[] args) {
        System.out.println("--- Ordering a High Budget Chef Knife ---");
        Order("High", "Chef_Knife");

        System.out.println("\n--- Ordering a Low Budget Steak Knife ---");
        Order("Low", "Steak_Knife");
    }
}

/*------------------------------ FACTORY PRODUCER ------------------------------*/
// Factory_Producer is responsible for returning the correct factory based on budget type
class Factory_Producer {
    public static Factory getFactory(String budgetType) {
        if (budgetType.equalsIgnoreCase("High")) {
            return new High_budget_Factory();
        } else if (budgetType.equalsIgnoreCase("Low")) {
            return new Low_budget_Factory();
        } else {
            return null; // Invalid type
        }
    }
}

/*------------------------------ ABSTRACT FACTORY INTERFACE ------------------------------*/
// Defines abstract methods to create products (Knives and Sheaths)
interface Factory {
    Knife Create_Knives(String type);
    Sheath Create_Sheath(String type);
}

/*------------------------------ LOW BUDGET FACTORY ------------------------------*/
// Concrete Factory for low-budget products
class Low_budget_Factory implements Factory {
    public Knife Create_Knives(String type) {
        if (type.equalsIgnoreCase("Chef_Knife")) {
            return new Low_Budget_Chef_Knife();
        } else if (type.equalsIgnoreCase("Steak_Knife")) {
            return new Low_Budget_Steak_Knife();
        } else return null;
    }

    public Sheath Create_Sheath(String type) {
        if (type.equalsIgnoreCase("Sheath")) {
            return new Low_Budget_Sheath();
        } else return null;
    }
}

/*------------------------------ HIGH BUDGET FACTORY ------------------------------*/
// Concrete Factory for high-budget products
class High_budget_Factory implements Factory {
    public Knife Create_Knives(String type) {
        if (type.equalsIgnoreCase("Chef_Knife")) {
            return new High_Budget_Chef_Knife();
        } else if (type.equalsIgnoreCase("Steak_Knife")) {
            return new High_Budget_Steak_Knife();
        } else return null;
    }

    public Sheath Create_Sheath(String type) {
        if (type.equalsIgnoreCase("Sheath")) {
            return new High_Budget_Sheath();
        } else return null;
    }
}

/*------------------------------ PRODUCT INTERFACES ------------------------------*/
// Knife interface declares the common operations for all knife types
interface Knife {
    void Identify();
    void Sharpening();
    void Packaging();
}

// Sheath interface declares operation for sheath creation
interface Sheath {
    void createsheath();
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

/*------------------------------ SHEATH VARIANTS ------------------------------*/
class Low_Budget_Sheath implements Sheath {
    public void createsheath() { System.out.println("Low budget Sheath is created"); }
}

class High_Budget_Sheath implements Sheath {
    public void createsheath() { System.out.println("High budget Sheath is created"); }
}
