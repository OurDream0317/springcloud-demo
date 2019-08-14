package Test;

import org.junit.Test;

public class threadDemo implements Runnable {
    public int i = 0;

    @Override
    public void run() {
        synchronized (this) {
            if (Thread.currentThread().getName().equals("A")) {
                Thread.yield();
                return;
            }
            i++;
            System.out.println("i:" + i);
            System.out.println("线程" + Thread.currentThread().getName() + "进入睡眠状态");
            System.out.println("线程" + Thread.currentThread().getName() + "睡眠结束");
            i++;
            System.out.println("i:" + i);

        }
    }

    @Test
    public void method() {
        threadDemo t = new threadDemo();
        Thread thread = new Thread(t, "A");
        thread.start();
        Thread thread1 = new Thread(t, "B");
        thread1.start();
    }
}
