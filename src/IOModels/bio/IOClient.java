package IOModels.bio;

import java.net.Socket;

public class IOClient {
  public static void main(String[] args) {
    new Thread(() -> {
      try {
        Socket socket = new Socket("127.0.0.1", 3303);
        while (true) {
          try {
            socket.getOutputStream().write("hello".getBytes());
            Thread.sleep(2000);
          } catch (Exception e) {
            // TODO: handle exception
          }
        }
      } catch (Exception e) {
        // TODO: handle exception
      }
    }).start();

  }
}
