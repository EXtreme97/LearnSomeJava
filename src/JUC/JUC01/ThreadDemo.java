package JUC.JUC01;

public class ThreadDemo {
  public static void main(String[] args) {
    MyThread myThread = new ThreadDemo().new MyThread();
    myThread.start();
    for (int i = 0; i < 20; i++) {
      System.out.println("main" + i);
    }
    System.out.println("main over");
  
  }

  class MyThread extends Thread {
    @Override
    public void run() {
      for (int i = 0; i < 20; i++) {
        System.out.println("run" + i);
      }
    }
  }
}
