package JUC.JUC07;

public class Phone {
  public synchronized void sendSms() throws Exception {
    System.out.println(Thread.currentThread().getName() + "\tsendSms");
    sendEmail();
  }

  public synchronized void sendEmail() throws Exception {
    System.out.println(Thread.currentThread().getName() + "\tsendEmail");
  }
}
