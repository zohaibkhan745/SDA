// Facade Pattern Example – Hiding complexity of multiple account operations behind a single interface (Bank_Facade)
public class Facade {

    public static void main(String[] args) {

        // Example without Facade (direct interaction with specific account class)
        // IAccount a1 = new Saving_Account();
        // a1.createAccount(12334, 1000.0);
        // a1.Withdraw(500);
        // a1.Check_Balence();

        // Using Facade — single object provides simplified access to complex subsystem
        Bank_Facade f1 = new Bank_Facade();

        // Creating a Current Account through the Facade
        f1.CreateCurrentAccount(123456, 12000);

        // Checking balance of Current Account through Facade
        f1.CurrentCheckBalence();
    }
}


// Common interface for different types of bank accounts
interface IAccount {

    void createAccount(int Account_no, double Initial_Balence);
    void Deposit(double amount);
    void Withdraw(double amount);
    void Check_Balence();
}


// Concrete implementation for Saving Account
class Saving_Account implements IAccount {

    private int Account_no;
    private double Balence;

    // Creates a new saving account with an initial balance
    public void createAccount(int Account_no, double Initial_Balence) {
        this.Account_no = Account_no;
        this.Balence = Initial_Balence;
        System.out.println("Saving Account created successfully.");
    }

    // Deposits the given amount into the account
    public void Deposit(double amount) {
        Balence += amount;
        System.out.println("The amount: " + amount + " is successfully deposited.");
    }

    // Withdraws the given amount if sufficient funds are available
    public void Withdraw(double amount) {
        if (amount > Balence) {
            System.out.println("Insufficient funds in Saving Account.");
        } else {
            Balence -= amount;
        }
    }

    // Displays the current balance of the account
    public void Check_Balence() {
        System.out.println("Saving Account balance: " + Balence);
    }
}


// Concrete implementation for Current Account
class Current_Account implements IAccount {

    private int Account_no;
    private double Balence;

    public void createAccount(int Account_no, double Initial_Balence) {
        this.Account_no = Account_no;
        this.Balence = Initial_Balence;
        System.out.println("Current Account created successfully.");
    }

    public void Deposit(double amount) {
        Balence += amount;
        System.out.println("The amount: " + amount + " is successfully deposited.");
    }

    public void Withdraw(double amount) {
        if (amount > Balence) {
            System.out.println("Insufficient funds in Current Account.");
        } else {
            Balence -= amount;
        }
    }

    public void Check_Balence() {
        System.out.println("Current Account balance: " + Balence);
    }
}


// Concrete implementation for Chequing Account
class Chequing_Account implements IAccount {

    private int Account_no;
    private double Balence;

    public void createAccount(int Account_no, double Initial_Balence) {
        this.Account_no = Account_no;
        this.Balence = Initial_Balence;
        System.out.println("Chequing Account created successfully.");
    }

    public void Deposit(double amount) {
        Balence += amount;
        System.out.println("The amount: " + amount + " is successfully deposited.");
    }

    public void Withdraw(double amount) {
        if (amount > Balence) {
            System.out.println("Insufficient funds in Chequing Account.");
        } else {
            Balence -= amount;
        }
    }

    public void Check_Balence() {
        System.out.println("Chequing Account balance: " + Balence);
    }
}


// Facade class — simplifies access to the complex subsystem of multiple account types
class Bank_Facade {

    private Saving_Account saving = new Saving_Account();
    private Current_Account current = new Current_Account();
    private Chequing_Account Cheq = new Chequing_Account();

    // Creates a Current Account without exposing the Current_Account class directly to the client
    public void CreateCurrentAccount(int Acc, double Ib) {
        current.createAccount(Acc, Ib);
    }

    // Similarly, you can add Facade methods for Saving and Chequing accounts too.
    // The idea is: the client doesn’t need to know which class to interact with.

    public void DepositToCurrentAccount(double amount) {
        current.Deposit(amount);
    }

    public void CurrentCheckBalence() {
        current.Check_Balence();
    }
}
