package com.example.export_pdf;

public class FilePath {

    static public String name(String templateName) {
        FilePath file = new FilePath();
        String path = file.getClass().getClassLoader().getResource("").getPath()+"templates/"+templateName;
        return path;
    }
}
