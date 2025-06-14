package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public Runnable getRunnable() {
       return new Runnable() {
           @Override
           public void run() {
            int port = 8010;
               try {
    InetAddress address = InetAddress.getByName("localhost");
    Socket socket = new Socket(address , port);
    PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
    BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    toSocket.println("Hello from the client!"+socket.getLocalSocketAddress());
    String line = fromSocket.readLine();
    System.out.println(" response fom socket is: " + line);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       };
    }
    public static void main(String[] args) {
       Client client = new Client();
       for (int i = 0; i < 100; i++) {
           Thread thread = new Thread(client.getRunnable());
           thread.start();
       }
    }
}
