package Clock;

class Clock implements Runnable {

    public void run() {
        Thread current = Thread.currentThread();
        while (!current.isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error occured");
                break;
            }
            System.out.println("Tik");
        }
    }
}