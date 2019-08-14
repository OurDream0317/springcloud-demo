public class ThreadDemo implements Runnable {
    public int count = 500;

    @Override
    public void run() {
        while (count > 0) {
            System.out.println(Thread.currentThread().getName() + "获得" + count);
            count--;
        }
    }

    public static void main(String args[]) {
        ThreadDemo threadDemo = new ThreadDemo();
        Thread thread = new Thread(threadDemo, "A");
        Thread thread1 = new Thread(threadDemo, "B");
        thread.start();
        Thread.yield();
        thread1.start();

    }
}
