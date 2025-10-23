public class Chain_of_responsibilty{

    public static void main(String[] args) {

        Approver manager = new Manager();
        Approver director = new Director();
        Approver ceo = new CEO();

        manager.setNext(director);
        director.setNext(ceo);

        System.out.println("\n--- Expense Approval Chain Starts ---\n");

        manager.processRequest(800);
        manager.processRequest(3000);
        manager.processRequest(12000);

        System.out.println("\n--- End of Approval Process ---");
    }
}

interface Approver {
    void setNext(Approver nextApprover);
    void processRequest(int amount);
}

class Manager implements Approver {
    private Approver next;

    public void setNext(Approver nextApprover) {
        this.next = nextApprover;
    }

    public void processRequest(int amount) {
        if (amount <= 1000) {
            System.out.println("Manager approved the expense of $" + amount);
        } else if (next != null) {
            System.out.println("Manager forwarded the request of $" + amount + " to the next level.");
            next.processRequest(amount);
        }
    }
}

class Director implements Approver {
    private Approver next;

    public void setNext(Approver nextApprover) {
        this.next = nextApprover;
    }

    public void processRequest(int amount) {
        if (amount <= 5000) {
            System.out.println("Director approved the expense of $" + amount);
        } else if (next != null) {
            System.out.println("Director forwarded the request of $" + amount + " to the next level.");
            next.processRequest(amount);
        }
    }
}

class CEO implements Approver {
    public void setNext(Approver nextApprover) {}

    public void processRequest(int amount) {
        System.out.println("CEO approved the expense of $" + amount);
    }
}
