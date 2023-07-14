package JUC.JUC09;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
  public static void main(String[] args) {
    /**
     * 1.CompletableFuture创建方式
     * CompletableFuture 提供了四个静态方法来创建一个异步操作
     * runAsync方法不支持返回值.适用于多个接口之间没有任何先后关系
     * supplyAsync可以支持返回值,我们一般用supplyAsync来创建
     * 没有指定Executor的方法会使用ForkJoinPool.commonPool()
     * 作为它的线程池执行异步代码。如果指定线程池,则使用指定的线程池运行。
     * 
     * 2.获得结果和触发计算(get、getNow、join、complete)
     * public T get( ) 不见不散(会抛出异常) 只要调用了get( )方法,不管是否计算完成都会导致阻塞
     * 
     * public T get(long timeout, TimeUnit unit) 过时不候
     * 
     * public T getNow(T
     * valuelfAbsent):没有计算完成的情况下,给我一个替代结果计算完,返回计算完成后的结果、没算完,返回设定的valuelfAbsent
     * 
     * public T join( ):join方法和get( )方法作用一样,不同的是,join方法不抛出异常
     * 
     * 3.对计算结果进行处理(thenApply、handle)
     * public <U> CompletableFuture<U> thenApply
     * 计算结果存在依赖关系,这两个线程串行化
     * 由于存在依赖关系(当前步错,不走下一步),当前步骤有异常的话就叫停
     * 
     * public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ?
     * extends U> fn):
     * 有异常也可以往下一步走,根据带的异常参数可以进一步处理
     * 
     * whenComplete:是执行当前任务的线程执行继续执行whenComplete的任务
     * 
     * whenCompleteAsync:是执行把whenCompleteAsync这个任务继续提交给线程池来进行执行
     * 
     * 4.对计算结果进行消费(thenRun、thenAccept、thenApply)
     * thenRun(Runnable runnable)
     * 任务A执行完执行B,并且B不需要A的结果
     * 
     * CompletableFuture<Void> thenAccept(Consumer<? super T> action)
     * 任务A执行完成执行B,B需要A的结果,但是任务B无返回值
     * 
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U>
     * fn)
     * 任务A执行完成执行B,B需要A的结果,同时任务B有返回值
     * 
     * 线程串行化方法
     * 带了Async的方法表示的是:会重新在线程池中启动一个线程来执行任务
     * 
     * 5.对计算速度进行选用(applyToEither、acceptEither、runAfterEither)
     * public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T>
     * other, Function<? super T, U> fn)
     * 这个方法表示的是,谁快就用谁的结果,类似于我们在打跑得快,或者麻将谁赢了就返回给谁
     * 
     * 6对计算结果进行合并(thenCombine、thenAcceptBoth、runAfterBoth)
     * public <U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U>
     * other,BiFunction<? super T,? super U,? extends V> fn)
     * 两个CompletionStage任务都完成后,最终把两个任务的结果一起交给thenCombine来处理
     * 先完成的先等着,等待其他分支任务
     * 
     * 7.多任务组合(allOf、anyOf)
     * ①. allOf:等待所有任务完成
     * (public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs))
     * 
     * ②. anyOf:只要有一个任务完成
     * (public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs))
     */
    CompletableFuture<Integer> example = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return 2;
    }).thenApply(res -> {
      return res + 3;
    }).whenComplete((res, rej) -> {
      if (rej == null)
        System.out.println(Thread.currentThread().getName() + "\t" + "result = " + res);
    }).exceptionally(e -> {
      e.printStackTrace();
      return null;
    });
    System.out.println(Thread.currentThread().getName() + "\t" + "over...");
    // 主线程不要立即结束,否则CompletableFuture默认使用的线程池会立即关闭,暂停几秒
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
