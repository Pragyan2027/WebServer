package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

import SingleThreaded.Server;

public class server {
    public Consumer<Socket> getconsumer(){
        // return new Consumer<Socket>() {
        //         try {
        //             PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
        //             BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //             toClient.close();
        //             fromClient.close();
        //         } catch (IOException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // };
        return (clientSocket) -> {
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello from the server!");
                toClient.close();
                clientSocket.close();
        
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8010;
        server thisServer = new server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                Thread thread = new Thread(() -> thisServer.getconsumer().accept(acceptedSocket));
                thread.start();
    }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

