package Account;

public class Account {

    private long balance;

    public Account() {
        this(0L);
    }

    public Account(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return this.balance;
    }

    public synchronized void deposit(long amount) {
        checkAmountNonNegative(amount);
        balance += amount;
        notifyAll();
    }

    public synchronized void withdraw(long amount) throws InterruptedException {
        checkAmountNonNegative(amount);
        while (balance < amount) {
            System.out.println("Waiting for money...");
            wait();
        }
        balance -= amount;
    }

    private static void checkAmountNonNegative(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Negative amount");
        }
    }

}
