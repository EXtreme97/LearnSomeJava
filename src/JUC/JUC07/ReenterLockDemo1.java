package JUC.JUC07;

/**
 * Description:
 * 可重入锁(也叫做递归锁)
 * 指的是同一先生外层函数获得锁后,内层敌对函数任然能获取该锁的代码
 * 在同一线程外外层方法获取锁的时候,在进入内层方法会自动获取锁
 * 也就是说,线程可以进入任何一个它已经标记的锁所同步的代码块
 * 
 * 可重入锁的种类
 * 隐式锁(即synchronized关键字使用的锁)默认是可重入锁,在同步块、同步方法使用
 * (在一个synchronized修饰的方法或者代码块的内部调用本类的其他synchronized修饰的方法或代码块时,是永远可以得到锁的)
 * 显示锁(即Lock)也有ReentrantLock这样的可重入锁
 * (lock和unlock一定要一 一匹配,如果少了或多了,都会坑到别的线程)
 **/
public class ReenterLockDemo1 {
  public static void main(String[] args) {
    Phone1 phone = new Phone1();
    Thread t3 = new Thread(phone);
    Thread t4 = new Thread(phone);
    t3.start();
    t4.start();
  }
}
