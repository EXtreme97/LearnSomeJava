package JUC.JUC03;

public class Noodles {
  private int num = 0;

  public synchronized void makeNoodles() throws InterruptedException {
    // if (num != 0)
    // this.wait();
    while (num != 0)
      this.wait();
    num++;
    System.out.println(Thread.currentThread().getName() + "：做好了一份面，当前有" + num + "份面");
    this.notifyAll();
  }

  public synchronized void eatNoodles() throws InterruptedException {
    // if (num == 0)
    // this.wait();
    while (num != 0)
      this.wait();
    num--;
    System.out.println(Thread.currentThread().getName() + "：吃了一份面，当前还有" + num + "面");
    this.notifyAll();
  }
}
