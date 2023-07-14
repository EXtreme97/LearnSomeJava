package JUC.JUC01;

public class RunnableDemo {

  public static void main(String[] args) {
    MyRunnale mr = new MyRunnale();
    new Thread(mr).start();
    for (int i = 0; i < 10; i++) {
      System.out.println("main" + i);
    }
  }
}

class MyRunnale implements Runnable {
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("runnable" + i);
    }
  }
}
