package JUC.JUC09;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class CompletableFutureExample2 {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 2L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3));
    CompletableFuture future1 = CompletableFuture.runAsync(() -> {
      System.out.println(Thread.currentThread().getName() + "*************** future coming in");
    });
    System.out.println(future1.get());
  }
}
