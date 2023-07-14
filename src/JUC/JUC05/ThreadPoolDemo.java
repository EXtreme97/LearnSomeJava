package JUC.JUC05;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

public class ThreadPoolDemo {
  public static void main(String[] args) {
    ExecutorService executorService = new ThreadPoolExecutor(
        2,
        5,
        1L,
        TimeUnit.SECONDS,
        new LinkedBlockingDeque<Runnable>(3),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.AbortPolicy());

    try {
      for (int i = 1; i <= 10; i++) {
        executorService.execute(() -> {
          System.out.println(Thread.currentThread().getName() + "\t 办理业务");
        });
      }
    } catch (Exception e) {
      // TODO: handle exception
    } finally {
      executorService.shutdown();
    }
  }
}
