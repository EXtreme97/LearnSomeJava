package JUC.JUC08;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {

  // 针对一个数字，做计算。
  private static final Integer MAX = 200;

  /**
   * 1.RecursiveAction无结果返回的任务
   * 2.RecursiveTask有返回结果的任务
   * 3.CountedCompleter无返回值任务，完成任务后可以触发回调
   */
  static class CalcForJoinTask extends RecursiveTask<Integer> {
    private Integer startValue; // 子任务的开始计算的值
    private Integer endValue; // 子任务结束计算的值

    public CalcForJoinTask(Integer startValue, Integer endValue) {
      this.startValue = startValue;
      this.endValue = endValue;
    }

    @Override
    protected Integer compute() {
      // 如果当前的数据区间已经小于MAX了，那么接下来的计算不需要做拆分
      if (endValue - startValue < MAX) {
        System.out.println("开始计算：startValue:" + startValue + " ; endValue:" + endValue);
        Integer totalValue = 0;
        for (int i = this.startValue; i <= this.endValue; i++) {
          totalValue += i;
        }
        return totalValue;
      }
      // 任务拆分，拆分成两个任务
      CalcForJoinTask subTask = new CalcForJoinTask(startValue, (startValue + endValue) / 2);
      subTask.fork();// fork()让task异步执行
      CalcForJoinTask calcForJoinTask = new CalcForJoinTask((startValue + endValue) / 2 + 1, endValue);
      calcForJoinTask.fork();
      return subTask.join() + calcForJoinTask.join();// join()让task异步执行
    }
  }

  public static void main(String[] args) {
    /**
     * ForkJoinPool ： 专门用来运行 ForkJoinTask 的线程池，（在实际使用中，也可以接收Runnable/Callable
     * 任务，但在真正运行时，也会把这些任务封装成 ForkJoinTask 类型的任务）。
     * 
     * 是 fork/join 框架的核心，是 ExecutorService
     * 的一个实现，用于管理工作线程，并提供了一些工具来帮助获取有关线程池状态和性能的信息。
     * 
     * 工作线程一次只能执行一个任务。
     * 
     * ForkJoinPool 线程池并不会为每个子任务创建一个单独的线程，相反，池中的每个线程都有自己的双端队列用于存储任务 （ double-ended
     * queue ）。
     * 
     * 这种架构使用了一种名为工作窃取（ work-stealing ）算法来平衡线程的工作负载,就是空闲的线程试图从繁忙线程的 deques 中 窃取工作。
     */
    CalcForJoinTask calcForJoinTask = new CalcForJoinTask(1, 1000);

    // 这是Fork/Join框架的线程池
    ForkJoinPool pool = new ForkJoinPool();
    ForkJoinTask<Integer> taskFuture = pool.submit(calcForJoinTask);
    try {
      Integer result = taskFuture.get();
      System.out.println("result:" + result);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

}
