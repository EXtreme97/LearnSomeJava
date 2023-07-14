package JUC.JUC02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* 使用Lock代替Synchronized来实现新版的生产者和消费者模式 !
* */
@SuppressWarnings("all")
public class ThreadWaitNotifyDemo1 {
  public static void main(String[] args) {
    AirCondition airCondition = new AirCondition();

    new Thread(() -> {
      for (int i = 0; i < 10; i++)
        airCondition.decrement();
    }, "线程A").start();
    new Thread(() -> {
      for (int i = 0; i < 10; i++)
        airCondition.increment();
    }, "线程B").start();
    new Thread(() -> {
      for (int i = 0; i < 10; i++)
        airCondition.decrement();
    }, "线程C").start();
    new Thread(() -> {
      for (int i = 0; i < 10; i++)
        airCondition.increment();
    }, "线程D").start();
  }
}

class AirCondition {
  private int number = 0;
  // 定义Lock锁对象
  final Lock lock = new ReentrantLock();
  final Condition condition = lock.newCondition();

  // 生产者,如果number=0就 number++
  public void increment() {
    lock.lock();
    try {
      // 1.判断
      while (number != 0) {
        try {
          condition.await();// this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      // 2.干活
      number++;
      System.out.println(Thread.currentThread().getName() + ":\t" + number);
      // 3.唤醒
      condition.signalAll();// this.notifyAll();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  // 消费者,如果number=1,就 number--
  public void decrement() {
    lock.lock();
    try {
      // 1.判断
      while (number == 0) {
        try {
          condition.await();// this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      // 2.干活
      number--;
      System.out.println(Thread.currentThread().getName() + ":\t" + number);
      // 3.唤醒
      condition.signalAll();// this.notifyAll();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
