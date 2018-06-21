package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by codecadet on 19/06/2018.
 */
public class WebServer {

    //PrintWriter out = null;
    ServerSocket serverSocket = null;
    Socket clientSocket = null;

    public void start() {

        System.out.println("waiting for requests");

        try {
            serverSocket = new ServerSocket(8080);

            while (true) {

                clientSocket = serverSocket.accept();

                Thread thread = new Thread(new HttpThreading(serverSocket, clientSocket));
                thread.start();

                System.out.println("this thread : " + Thread.currentThread().getName());

            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
