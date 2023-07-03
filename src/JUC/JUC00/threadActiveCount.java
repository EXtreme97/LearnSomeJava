package JUC.JUC00;

public class threadActiveCount {
  public static void main(String[] args) {
    Thread.currentThread().getThreadGroup().list();
    System.out.println(Thread.activeCount());
  }
}
