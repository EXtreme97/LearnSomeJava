package JUC.JUC07;

import java.util.concurrent.TimeUnit;

/**
 * 什么是死锁？
 * 死锁是指两个或两个以上的线程在执行过程中,因争夺资源而造成的一种互相等待的现象,若无外力干涉那它们都将无法推进下去,如果资源充足,进程的资源请求都能够得到满足,
 * 死锁出现的可能性就很低,否则就会因争夺有限的资源而陷入死锁
 * 产生死锁的原因:
 * 1.系统资源不足
 * 2.进程运行推进的顺序不合适
 * 3.资源分配不当
 */
public class DeadLockDemo {
  static Object lockA = new Object();
  static Object lockB = new Object();

  public static void main(String[] args) {
    Thread a = new Thread(() -> {
      synchronized (lockA) {
        System.out.println(Thread.currentThread().getName() + "\t" + " 自己持有A锁，期待获得B锁");

        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        synchronized (lockB) {
          System.out.println(Thread.currentThread().getName() + "\t 获得B锁成功");
        }
      }
    }, "a");
    a.start();

    new Thread(() -> {
      synchronized (lockB) {

        System.out.println(Thread.currentThread().getName() + "\t" + " 自己持有B锁，期待获得A锁");

        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        synchronized (lockA) {

          System.out.println(Thread.currentThread().getName() + "\t 获得A锁成功");
        }
      }
    }, "b").start();

  }
}
