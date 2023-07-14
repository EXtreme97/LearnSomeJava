package JUC.JUC05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
  public static void main(String[] args) {
    // ExecutorService threadPool = Executors.newFixedThreadPool(10);//一池10个线程
    // ExecutorService threadPool = Executors.newSingleThreadExecutor();// 一池1个线程
    ExecutorService threadPool = Executors.newCachedThreadPool();// 一池5个线程

    try {
      for (int i = 0; i < 100; i++) {
        threadPool.execute(() -> {
          System.out.println(Thread.currentThread().getName() + " is running");
        });
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    } finally {
      threadPool.shutdown();
    }
  }
}
