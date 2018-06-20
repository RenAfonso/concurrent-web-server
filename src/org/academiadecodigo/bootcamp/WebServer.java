package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by codecadet on 19/06/2018.
 */
public class WebServer {

    //PrintWriter out = null;
    DataOutputStream out = null;
    BufferedReader in = null;
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    Checker checker;
    HttpGet httpGet;

    public WebServer() {
        this.checker = new Checker();
        this.httpGet = new HttpGet();
    }

    public void start() {

        System.out.println("waiting for requests");

        try {
            serverSocket = new ServerSocket(8080);

            while (true) {

                clientSocket = serverSocket.accept();

                out = new DataOutputStream(clientSocket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


                String line;

                if ((line = in.readLine()) != null) {
                    System.out.println(line);
                }

                String[] requestArray = line.split(" ");

                if (requestArray[0].equals("GET")) {

                    httpGet.returnFile(requestArray, checker, out);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());

        } finally {
            closeStreams(out, in);
            closeSockets(serverSocket, clientSocket);
        }
    }



    private static void closeStreams(DataOutputStream out, BufferedReader in) {
        try {
            out.close();
            in.close();

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void closeSockets(ServerSocket serverSocket, Socket clientSocket) {
        try {
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
