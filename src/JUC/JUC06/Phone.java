package JUC.JUC06;

import java.util.concurrent.TimeUnit;

public class Phone {
  public static synchronized void sendEmail() throws Exception {
    try {
      TimeUnit.SECONDS.sleep(5);
      System.out.println("sendEmail");

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public synchronized void sendSms() {
    System.out.println("sendSMS");
  }
}
