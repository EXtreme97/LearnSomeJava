package JUC.JUC01;

public class BuyTicketsDemo {
  public static void main(String[] args) {
    BuyTickets buyTickets = new BuyTickets();
    new Thread(buyTickets, "小明").start();
    new Thread(buyTickets, "小红").start();
    new Thread(buyTickets, "小李").start();
    // buyTickets.sellTickets();
  }

}
