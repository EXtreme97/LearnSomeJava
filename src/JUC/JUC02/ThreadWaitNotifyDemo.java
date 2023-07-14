package JUC.JUC02;

/**
 * 使用Sychronized实现(隐式锁)
 * 为了体现生产和消费过程总的等待和唤醒，Java就提供了几个方法供我们使用，这几个方法就在Object类中Object类的等待和唤醒方法(隐式锁)
 * viod wait( )：导致当前线程等待，直到另一个线程调用该对象的notify（）方法和notifyAll（）方法
 * void notify( ):唤醒正在等待对象监视器的单个线程
 * void notifyAll( ):唤醒正在等待对象监视器的所有线程
 * (注意:wait、notify、notifyAll方法必须要在同步块或同步方法里且成对出现使用)
 */
/*
 * 1.题目:
 * 现在两个线程,可以操作初始值为0的一个变量，实现一个线程对该变量加1,
 * 一个线程对该变量减1,交替执行,来10轮,变量的初始值为0
 * 2.思想:
 * 1.在高内聚低耦合的前提下,线程->操作->资源类
 * 2.判断操作唤醒[生产消费中]
 * 3.多线程交互中,必须要放置多线程的虚假唤醒,也即(判断使用while,不能使用if)
 */

public class ThreadWaitNotifyDemo {
  public static void main(String[] args) {
    AirCondition airCondition = new AirCondition();
    new Thread(() -> {
      for (int i = 1; i < 11; i++)
        airCondition.increment();
    }, "线程A").start();
    new Thread(() -> {
      for (int i = 1; i < 11; i++)
        airCondition.decrement();
    }, "线程B").start();
    new Thread(() -> {
      for (int i = 1; i < 11; i++)
        airCondition.increment();
    }, "线程C").start();
    new Thread(() -> {
      for (int i = 1; i < 11; i++)
        airCondition.decrement();
    }, "线程D").start();
  }
}

class AirCondition {
  private int number = 0;

  public synchronized void increment() {
    // 1.判断
    /* if(number!=0){ */
    while (number != 0) {
      try {
        // 为什么不用if?解释如下
        // 第一次A进来了,在number++后(number=1) C抢到执行权,进入wait状态
        // 这个时候,A抢到cpu执行权,也进入wait状态,此时,B线程进行了一次消费
        // 唤醒了线程,这个时候A抢到CPU执行权,不需要做判断,number++(1),唤醒线程
        // C也抢到CPU执行权,不需要做判断,number++(2)

        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    // 2.干活
    number++;
    System.out.println(Thread.currentThread().getName() + ":" + number);
    // 3.唤醒
    this.notifyAll();
  }

  public synchronized void decrement() {
    while (number == 0) {
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    number--;
    System.out.println(Thread.currentThread().getName() + ":" + number);
    this.notifyAll();
  }
}
