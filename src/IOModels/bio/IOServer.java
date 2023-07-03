package IOModels.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(3303);

    new Thread(() -> {
      while (true) {
        try {
          Socket socket = serverSocket.accept();
          new Thread(() -> {
            try {
              int len;
              byte[] data = new byte[1024];
              while ((len = socket.getInputStream().read(data)) != -1) {
                System.out.println(new String(data, 0, len));
              }

            } catch (Exception e) {
            }
          }).start();
        } catch (Exception e) {
          // TODO: handle exception
        }
      }
    }).start();
  }
}
