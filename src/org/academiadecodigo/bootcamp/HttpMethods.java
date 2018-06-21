package org.academiadecodigo.bootcamp;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by codecadet on 20/06/2018.
 */
public class HttpMethods {

    public void get(String[] requestArray, Checker checker, DataOutputStream out) throws IOException {

        // Works inbound header

        String requestPath = requestArray[1];
        String requestProtocol = requestArray[2];

        String pathFile = checker.checkRequestPath(requestPath);

        File outgoingFile = new File(pathFile);

        out.writeUTF("HTTP/1.0" + checker.checkFile(outgoingFile) + "\r\n" +
                "Content-Type: " + checker.checkRequestType(pathFile) + "\r\n" +
                "Content-Length: " + outgoingFile.length() + "\r\n" +
                "\r\n");


        // Sends outgoing file;

        int bytesRead = 0;
        byte[] buffer = new byte[1024];

        FileInputStream fileInputStream = new FileInputStream(pathFile);

        while((bytesRead = fileInputStream.read(buffer)) != -1) {
            out.write(buffer, 0 , bytesRead);
        }

    }

}
