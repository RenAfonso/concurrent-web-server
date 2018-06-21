package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
    HttpMethods httpMethods;

    public WebServer() {
        this.checker = new Checker();
        this.httpMethods = new HttpMethods();
    }

    public void start() {

        System.out.println("waiting for requests");

        try {
            serverSocket = new ServerSocket(5080);

            while (true) {

                clientSocket = serverSocket.accept();

                out = new DataOutputStream(clientSocket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


                String line;
                String oldLine = null;
                String raw = "";
                int emptyLine = 0;
                int lengthInt = 0;

                while ((line = in.readLine()) != null) {
                    raw += line;
                    System.out.println(line);
                    System.out.println(line.length());

                    String[] temp = raw.split(" ");
                    if (temp[0].equals("GET")) {
                        break;
                    }

                    /*if (temp[0].equals("Content-length:")) {
                        lengthInt = Integer.parseInt(temp[1]);
                    }

                    if (emptyLine == 1) {
                        break;
                    }

                    if (line.equals("")) {
                        emptyLine++;
                    }*/

                }

                System.out.println("out of the while loop");

                String[] requestArray = raw.split(" ");
                System.out.println("split done");

                if (requestArray[0].equals("GET")) {
                    System.out.println(requestArray[0]);

                    httpMethods.get(requestArray, checker, out);
                }

                /*if (requestArray[0].equals("POST")) {


                    httpMethods.post();
                }*/

            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
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
