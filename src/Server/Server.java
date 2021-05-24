package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService executor;
    protected static Configurations configurations;
    //private Thread thread;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        configurations = new Configurations();
        //thread = new Thread(this::runServer);

    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
       //thread.start();
    }

    private void runServer() {
        try {
            this.executor = Executors.newFixedThreadPool(configurations.getCountTreads());
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    this.executor.execute(new Thread(() -> ServerStrategy(clientSocket)));

                /*    new Thread(() -> {
                        ServerStrategy(clientSocket);
                    });*/
               // }).start();
                } catch (SocketTimeoutException e) {
//                    this.executor.shutdown();
//                    serverSocket.close();
                }
            }
            serverSocket.close();
        } catch (IOException e) {

        }
        this.executor.shutdown();

    }

    private void ServerStrategy(Socket clientSocket) {
        try {
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.getInputStream().close();
            clientSocket.getOutputStream().close();
            clientSocket.close();
        } catch (IOException e) {
        }
    }

    public void stop() {
        stop = true;
        //executor.shutdown();


/*        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}
