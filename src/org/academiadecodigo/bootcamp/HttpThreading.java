package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by codecadet on 21/06/2018.
 */
public class HttpThreading implements Runnable {

    DataOutputStream out;
    BufferedReader in;
    ServerSocket serverSocket;
    Socket clientSocket;
    Checker checker;

    public HttpThreading(DataOutputStream out, BufferedReader in, ServerSocket serverSocket, Socket clientSocket, Checker checker) {
        this.out = out;
        this.in = in;
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
        this.checker = checker;
    }


    @Override
    public void run() {

        try {

            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            HttpMethods httpMethods = new HttpMethods();

            String line;
            String raw = "";

            while ((line = in.readLine()) != null) {
                raw += line;

                String[] temp = raw.split(" ");
                if (temp[0].equals("GET")) {
                    break;
                }
            }

            String[] requestArray = raw.split(" ");

            if (requestArray[0].equals("GET")) {

                // Works inbound header

                String requestPath = requestArray[1];
                String requestProtocol = requestArray[2];

                String pathFile = checker.checkRequestPath(requestPath);


                httpMethods.handleHeader(checker, pathFile, out);

                // Sends outgoing file;

               httpMethods.handleGet(pathFile, out, clientSocket);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
