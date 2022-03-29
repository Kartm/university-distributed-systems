package com.lblachnicki.app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String args[]) throws MalformedURLException, NotBoundException, RemoteException {
        var rooterStub = (Rooter) Naming.lookup("rmi://localhost:1099/rooter");

        var rootTask = new RootTask();
        rootTask.x = 2;
        rootTask.degree = 2;

        var rootResponse = rooterStub.calculate(rootTask);
        System.out.println(rootResponse.result);

        var logarithmerStub = (Logarithmer) Naming.lookup("rmi://localhost:1099/logarithmer");

        var logarithmTask = new LogarithmTask();
        logarithmTask.x = 256;
        logarithmTask.base = 2;

        var logarithmResponse = logarithmerStub.calculate(logarithmTask);
        System.out.println(logarithmResponse.result);
    }
}
