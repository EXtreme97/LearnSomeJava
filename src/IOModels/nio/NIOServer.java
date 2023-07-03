package IOModels.nio;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;

public class NIOServer {
  public static void main(String[] args) throws IOException {
    Selector serverSelector = Selector.open();
    Selector clientSelector = Selector.open();

    new Thread(() -> {
      try {
        ServerSocketChannel listenerChannel = ServerSocketChannel.open();
        listenerChannel.socket().bind(new InetSocketAddress("127.0.0.1", 3303));
        listenerChannel.configureBlocking(false);
        listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
        while (true) {
          if (serverSelector.select(1) > 0) {
            Set<SelectionKey> set = serverSelector.selectedKeys();
            Iterator<SelectionKey> keyIterator = set.iterator();

            while (keyIterator.hasNext()) {
              SelectionKey key = keyIterator.next();

              if (key.isAcceptable()) {
                try {
                  SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                  clientChannel.configureBlocking(false);
                  clientChannel.register(clientSelector, SelectionKey.OP_READ);
                } catch (Exception e) {
                  // TODO: handle exception
                } finally {
                  keyIterator.remove();
                }
              }
            }
          }
        }

      } catch (Exception e) {
        // TODO: handle exception
      }
    }).start();
    new Thread(() -> {
      try {
        while (true) {
          if (clientSelector.select(1) > 0) {
            Set<SelectionKey> set = serverSelector.selectedKeys();
            Iterator<SelectionKey> keyIterator = set.iterator();

            while (keyIterator.hasNext()) {
              SelectionKey key = keyIterator.next();

              if (key.isReadable()) {
                try {
                  SocketChannel clientChannel = (SocketChannel) key.channel();
                  ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                  clientChannel.read(byteBuffer);

                  byteBuffer.flip();
                  System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                } catch (Exception e) {
                  // TODO: handle exception
                } finally {
                  keyIterator.remove();
                  key.interestOps(SelectionKey.OP_READ);
                }
              }
            }

          }
        }
      } catch (Exception e) {
        // TODO: handle exception
      }
    }).start();
  }
}
