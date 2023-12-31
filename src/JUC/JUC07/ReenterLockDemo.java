package JUC.JUC07;

/**
 * Description:
 * 可重入锁(也叫做递归锁)
 * 指的是同一先生外层函数获得锁后,内层敌对函数任然能获取该锁的代码
 * 在同一线程外外层方法获取锁的时候,在进入内层方法会自动获取锁
 * 也就是说,线程可以进入任何一个它已经标记的锁所同步的代码块
 **/
public class ReenterLockDemo {
  public static void main(String[] args) {
    Phone phone = new Phone();
    new Thread(() -> {
      try {
        phone.sendSms();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, "t1").start();
    new Thread(() -> {
      try {
        phone.sendSms();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, "t2").start();
  }
}
