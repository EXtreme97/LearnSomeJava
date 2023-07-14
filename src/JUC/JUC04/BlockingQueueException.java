package JUC.JUC04;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueException {
  public static void main(String[] args) {
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
    System.out.println(blockingQueue.add("a"));
    System.out.println(blockingQueue.add("b"));
    System.out.println(blockingQueue.add("c"));

    blockingQueue.element();
    System.out.println(blockingQueue.remove());
    System.out.println(blockingQueue.remove());
    System.out.println(blockingQueue.remove());

  }

  public void offerAndPoll() throws InterruptedException {
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
    System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
    System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
    System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
    // System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
    // System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));

  }
}
