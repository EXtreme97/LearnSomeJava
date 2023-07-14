package JUC.JUC01;

public class SetDaemonDemo {
  public static void main(String[] args) throws InterruptedException {
    Thread t = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(2000);
          System.out.println("\u6211\u662F\u5B50\u7EBF\u7A0B(\u7528\u6237\u7EBF\u7A0B.I am running");
        } catch (Exception e) {
        }
      }
    });
    t.setDaemon(false);
    t.start();
    Thread.sleep(3000);
    System.out.println("主线程执行完毕...");
  }
}
