package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by codecadet on 19/06/2018.
 */
public class Main {

    public static void main(String[] args) {

        WebServer webServer = new WebServer();

        while (true) {
            webServer.start();
        }

    }
}

