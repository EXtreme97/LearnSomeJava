package JUC.JUC01;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo {
  public static void main(String[] args) {
    MyCallable mc = new MyCallable();
    // 一个FutureTask,多个线程调用call( )方法只会调用一次
    // 如果需要调用call方法多次,则需要多个FutureTask
    FutureTask<Integer> ft = new FutureTask<Integer>(mc);
    new Thread(ft, "线程1").start();
    // new Thread(ft, "线程2").start();

    try {
      Integer o = ft.get(); // get( )方法建议放在最后一行,防止线程阻塞(一旦调用了get( )方法,不管是否计算完成都会阻塞)
      System.out.println("FutureTask:" + o);
    } catch (Exception e) {
    }
  }
}

class MyCallable implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    var sum = 0;
    for (int i = 0; i <= 10; i++) {
      sum += i;
      System.out.println("callable" + i);
    }
    return sum;
  }

}
