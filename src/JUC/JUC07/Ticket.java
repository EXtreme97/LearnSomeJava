package JUC.JUC07;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {
  private int number = 50;

  private Lock lock = new ReentrantLock(true); // 默认用的是非公平锁，分配的平均一点，=--》公平一点

  public void sale() {
    lock.lock();
    try {
      if (number > 0) {
        System.out.println(Thread.currentThread().getName() + "\t 卖出第: " + (number--) + "\t 还剩下: " + number);
      }
    } finally {
      lock.unlock();
    }
  }
  /**
   * Object objectLock = new Object();
   * 
   * public void sale(){
   * synchronized (objectLock)
   * {
   * if(number > 0)
   * {
   * System.out.println(Thread.currentThread().getName()+"\t 卖出第: "+(number--)
   * +"\t 还剩下: "+number);
   * }
   * }
   * }
   */
}
