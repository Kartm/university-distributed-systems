package com.lblachnicki.xmlrpcserver;

import org.apache.xmlrpc.WebServer;

public class Main {


    public static void main(String[] args) {
	    try {
            int port = 10000;
            System.out.println("Starting server on port " + port + "...");

            WebServer webServer = new WebServer(port);
            webServer.addHandler("Server", new ServerRPC());
            webServer.start();

            System.out.println("Server started successfully!");

        } catch(Exception exception) {
            System.err.println("XML-RPC Server: " + exception);
        }
    }
}
