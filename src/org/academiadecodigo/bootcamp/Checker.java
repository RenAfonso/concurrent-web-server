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

        if (fileName.length() > 3) {

            String fileType = fileName.substring(fileName.length() - 4);

            if (fileType.equals(".jpg") || fileType.equals(".png")) {
                return "image/" + fileType.substring(fileType.length() - 3);
            }
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
