package org.example;

import org.example.OkHTTP.MyOkHTTP;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        MyOkHTTP okhttp = new MyOkHTTP();
        okhttp.init();
    }
}