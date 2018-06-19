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

// O header vem em texto
// Usar DataOutputStream para enviar os dados para o browser!!!!
// Verificar se um ficheiro existe e coisas sobre esse ficheiro. Usar a classe File. Dá exists e verificação de nome


// Criar HTML para devolver, incluindo erro 404.html
