package org.academiadecodigo.bootcamp;

import java.io.File;

/**
 * Created by codecadet on 19/06/2018.
 */
public class Checker {

    public Checker() {

    }

    public String checkRequestPath(String path) {
        if ((new File("www" + path)).exists()) {

            if (path.equals("/") || path.equals("/index.html")) {
                return "www/index.html";
            }

            return "www" + path;
        }
        return "www/error404.html";
    }

    public String checkRequestType(String fileName) {
        if (fileName.endsWith(".png")) {
            return "image/png";
        }
        if (fileName.endsWith(".jpg")) {
            return "image/jpg";
        }
        return "text/html; charset=UTF-8";
    }

    public String checkFile(File file) {
        if (file.exists()) {
            return "200 Document Follows";
        }
        return "404 Not Found";
    }
}
