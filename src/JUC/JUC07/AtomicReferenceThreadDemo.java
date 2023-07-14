package JUC.JUC07;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 什么是自旋锁？
 * (是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁,当线程发现锁被占用时，会不断循环判断锁的状态，直到获取。
 * 这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU)
 */
public class AtomicReferenceThreadDemo {
  static AtomicReference<Thread> atomicReference = new AtomicReference<>();
  static Thread thread;

  public static void lock() {
    thread = Thread.currentThread();
    System.out.println(Thread.currentThread().getName() + "\t" + "coming.....");
    while (!atomicReference.compareAndSet(null, thread)) {

    }
  }

  public static void unlock() {
    System.out.println(Thread.currentThread().getName() + "\t" + "over.....");
    atomicReference.compareAndSet(thread, null);
  }

  public static void main(String[] args) {
    new Thread(() -> {
      AtomicReferenceThreadDemo.lock();
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      AtomicReferenceThreadDemo.unlock();
    }, "A").start();

    new Thread(() -> {
      AtomicReferenceThreadDemo.lock();
      AtomicReferenceThreadDemo.unlock();
    }, "B").start();
  }
}
