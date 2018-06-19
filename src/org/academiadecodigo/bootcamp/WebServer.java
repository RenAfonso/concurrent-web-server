package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by codecadet on 19/06/2018.
 */
public class WebServer {

    //PrintWriter out = null;
    DataOutputStream out = null;
    BufferedReader in = null;
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    File outgoingFile = null;

    public WebServer() {

    }

    public void start() {

        System.out.println("waiting for requests");

        try

        {
            serverSocket = new ServerSocket(8080);
            clientSocket = serverSocket.accept();


            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            String line;

            if ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            String[] requestArray = line.split(" ");

            if (requestArray[0].equals("GET")) {

                String requestPath = requestArray[1];
                String requestProtocol = requestArray[2];

                String pathFile = checkRequestPath(requestPath);

                outgoingFile = new File(pathFile);

                out.writeUTF("HTTP/1.0" + checkFile(outgoingFile) + "\r\n" +
                        "Content-Type: " + checkRequestType(pathFile) + "\r\n" +
                        "Content-Length: " + outgoingFile.length() + "\r\n" +
                        "\r\n");

                Path filePath = Paths.get(outgoingFile.getPath());

                byte[] outgoingArray = Files.readAllBytes(filePath);

                out.write(outgoingArray, 0, (int) outgoingFile.length());
            }

            out.close();
            in.close();
            serverSocket.close();
            clientSocket.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String checkRequestPath(String path) {
        if (path.equals("/") || path.equals("/index.html")) {
            return "www/index.html";
        }
        if (path.equals("/academia.png")){
            return "www/academia.png";
        }
        return "www/error404.html";
    }

    private String checkRequestType(String fileName) {
        if (fileName.endsWith(".png")) {
            return "image/png";
        }
        if (fileName.endsWith(".jpg")) {
            return "image/jpg";
        }
        return "text/html; charset=UTF-8";
    }

    private String checkFile(File file) {
        if (file.exists()) {
            return "200 Document Follows";
        }
        return "404 Not Found";
    }

}
