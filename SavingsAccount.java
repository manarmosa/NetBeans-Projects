
public class SavingsAccount {

    private double balance;
    public static final double E = 2.7182818284590452354;
    private double interest;

    public SavingsAccount() {
        balance = 0;

    }

    public void addInsert(double x) {
        balance = balance + balance * x / 100;
    }

    public SavingsAccount(double initialBalance,double interest) {
        balance = initialBalance;
        this.interest=interest;

    }

    public void deposit(double amount) {
        balance = balance + amount;

    }

    public void withdraw(double amount) {
        balance = balance - amount;

    }

    double getBalance() {
        return balance;

    }
    public void addInterest(){
        balance = balance + balance*interest/100.0;
        
    }

}
