package JUC.JUC07;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Phone1 implements Runnable {
  private Lock lock = new ReentrantLock();

  @Override
  public void run() {
    get();
  }

  private void get() {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + "\tget");
      set();
    } finally {
      lock.unlock();
    }
  }

  private void set() {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + "\tset");
    } finally {
      lock.unlock();
    }
  }

}
