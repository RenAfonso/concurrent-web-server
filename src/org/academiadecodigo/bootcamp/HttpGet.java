package org.academiadecodigo.bootcamp;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by codecadet on 20/06/2018.
 */
public class HttpGet {

    public void returnFile(String[] requestArray, Checker checker, DataOutputStream out) throws IOException {

        String requestPath = requestArray[1];
        String requestProtocol = requestArray[2];

        String pathFile = checker.checkRequestPath(requestPath);

        File outgoingFile = new File(pathFile);

        out.writeUTF("HTTP/1.0" + checker.checkFile(outgoingFile) + "\r\n" +
                "Content-Type: " + checker.checkRequestType(pathFile) + "\r\n" +
                "Content-Length: " + outgoingFile.length() + "\r\n" +
                "\r\n");

        Path filePath = Paths.get(outgoingFile.getPath());

        byte[] outgoingArray = Files.readAllBytes(filePath);

        out.write(outgoingArray, 0, (int) outgoingFile.length());
    }
}
