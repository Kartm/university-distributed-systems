package com.lblachnicki.app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String args[]) throws MalformedURLException, NotBoundException, RemoteException {
        Adder stub = (Adder) Naming.lookup("rmi://localhost:1099/adder");

        System.out.println(stub.add(1, 2));
        System.out.println(stub.add(-1, 1));

        Name name = new Name();
        name.name = "xd";

        System.out.println(stub.addPrefix(name).name);
    }
}
