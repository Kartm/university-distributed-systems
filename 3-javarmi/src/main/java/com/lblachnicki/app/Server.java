package com.lblachnicki.app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String args[]) throws RemoteException, MalformedURLException {
        int port = Integer.parseInt(args[0]);

        System.out.println("Starting server at localhost:" + port);

        LocateRegistry.createRegistry(port);

        Rooter rooter = new RooterImpl();
        Naming.rebind(String.format("rmi://localhost:%d/rooter", port), rooter);

        Logarithmer logarithmer = new LogarithmerImpl();
        Naming.rebind(String.format("rmi://localhost:%d/logarithmer", port), logarithmer);
    }
}
