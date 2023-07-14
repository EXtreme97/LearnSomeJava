package JUC.JUC01;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskDemo {
  public static void main(String[] args) {
    FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "\t" + "coming......");
      return 1024;
    });
    new Thread(futureTask, "A").start();

    while (true) {
      if (futureTask.isDone()) {
        try {
          System.out.println("\u4F7F\u7528\u8F6E\u8BE2\u6765\u89E3\u51B3,\u503C\u4E3A:" + futureTask.get());
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      } else
        System.out.println("阻塞中**********");

    }
  }
}