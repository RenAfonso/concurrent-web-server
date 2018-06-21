package org.academiadecodigo.bootcamp;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by codecadet on 21/06/2018.
 */
public class HttpMethods {

    public void handleHeader(Checker checker, String pathFile, DataOutputStream out) throws IOException {



        File outgoingFile = new File(pathFile);

        out.writeUTF("HTTP/1.0" + checker.checkFile(outgoingFile) + "\r\n" +
                "Content-Type: " + checker.checkRequestType(pathFile) + "\r\n" +
                "Content-Length: " + outgoingFile.length() + "\r\n" +
                "\r\n");

        System.out.println("this thread : " + Thread.currentThread().getName());
    }

    public void handleGet(String pathFile, DataOutputStream out, Socket clientSocket) throws IOException {

        int bytesRead = 0;
        byte[] buffer = new byte[1024];

        FileInputStream fileInputStream = new FileInputStream(pathFile);

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        clientSocket.close();
    }
}
