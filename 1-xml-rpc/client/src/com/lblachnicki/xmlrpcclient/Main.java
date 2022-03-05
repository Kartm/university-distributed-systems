package com.lblachnicki.xmlrpcclient;

import org.apache.xmlrpc.XmlRpcClient;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        try {
            XmlRpcClient srv = new XmlRpcClient("http://localhost:10000");
            Vector<Integer> params = new Vector<Integer>();
            params.addElement(new Integer(13));
            params.addElement(new Integer(21));
            Object result = srv.execute("Server.echo", params);

            System.out.println("Sync result: " + ((Integer) result).intValue());

            AC cb = new AC();
            Vector<Integer> params2 = new Vector<Integer>();
            params2.addElement(new Integer(1000));
            srv.executeAsync("Server.echoAsync", params2, cb);
            System.out.println("Async call sent...");
        } catch (Exception exception) {
            System.err.println("XML-RPC client: " +exception);
        }
    }
}
