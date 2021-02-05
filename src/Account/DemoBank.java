package Account;


public class DemoBank {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);
        System.out.println("Begin balance = " + account.getBalance());

        Thread withdrowThread = new WithdrawThread(account);
        Thread depositThread = new DepositThread(account);
        withdrowThread.start();
        depositThread.start();


        depositThread.join();
        withdrowThread.join();

        System.out.println("End balance = " + account.getBalance());

    }


    private static class WithdrawThread extends Thread {
        private final Account account;

        private WithdrawThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            try {
                account.withdraw(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class DepositThread extends Thread {
        private final Account account;

        private DepositThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                account.deposit(1);
            }
        }
    }

}
