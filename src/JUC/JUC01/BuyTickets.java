package JUC.JUC01;

public class BuyTickets implements Runnable {
  private static int tickets = 100;

  @Override
  public void run() {
    // 循环必须在锁的前面保证多个线程可进入
    while (true) {
      // 不加synchronized会出现重复的票和负数的票
      // 锁是任意类型的但必须唯一，不管多少线程锁都必须是唯一的
      // synchronized(obj)
      synchronized (this) {
        if (tickets > 0) {
          try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + "正在买最后第" + tickets-- +
                "张票");
          } catch (Exception e) {
          }
        }
      } // 释放锁
      this.sellTickets(); // 同步方法(锁是同一个对象
      this.staticSellTickets();
    }
  }

  // 同步方法:就是把synchronized 关键字加到方法上 同步方法的锁对象是什么呢?this
  public synchronized void sellTickets() {
    if (tickets > 0) {
      try {
        Thread.sleep(100);
      } catch (Exception e) {
      }
      System.out.println(Thread.currentThread().getName() + "正在卖出第" + tickets-- + "票");
    }
  }
  // 同步静态方法:就是把synchronized关键字加到静态方法上.同步静态方法的锁对象是什么呢?类名.class

  public static synchronized void staticSellTickets() {
    if (tickets > 0) {
      try {
        Thread.sleep(100);
      } catch (Exception e) {
      }
      System.out.println("static:" + Thread.currentThread().getName() + "正在卖出第" + tickets-- + "票");
    }
  }
}
