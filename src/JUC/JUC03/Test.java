package JUC.JUC03;

public class Test {
  public static void main(String[] args) {
    Noodles noodles = new Noodles();
    new Thread(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        try {
          for (int i = 0; i < 10; i++) {
            noodles.makeNoodles();
          }
        } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
        }
      }

    }, "厨师A").start();
    new Thread(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        try {
          for (int i = 0; i < 10; i++) {
            noodles.eatNoodles();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }, "食客甲").start();

  }

}
