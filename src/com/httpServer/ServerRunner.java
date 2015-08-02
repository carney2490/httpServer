package com.httpServer;

import com.httpServer.Config.ServerConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServerRunner {

    public void run(Logger logger, ServerConfig config) throws IOException {

        try {
            FileHandler requestLogHandler = new FileHandler(System.getProperty("user.dir") + "/log/logs.txt");
            logger.addHandler(requestLogHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Couldn't add handler to logger", e);
        }

        ServerSocket serverSocket = new ServerSocket(config.getPort());
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        logger.log(Level.INFO, "Server starting on port " + config.getPort());

        while (true) {
            Socket clientSocket = serverSocket.accept();
            RunnableServer server = new RunnableServer(clientSocket);
            threadPool.execute(server);
        }
    }

}
