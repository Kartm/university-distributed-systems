package com.lblachnicki.app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Client {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            Rooter rooterStub = null;
            try {
                rooterStub = (Rooter) Naming.lookup("rmi://localhost:1090/rooter");
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            var rootTask = new RootTask();
            rootTask.x = 2;
            rootTask.degree = 2;

            RootTask rootResponse = null;
            try {
                rootResponse = rooterStub.calculate(rootTask);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            System.out.println(rootResponse.result);
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            Logarithmer logarithmerStub = null;
            try {
                logarithmerStub = (Logarithmer) Naming.lookup("rmi://localhost:1091/logarithmer");
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            var logarithmTask = new LogarithmTask();
            logarithmTask.x = 256;
            logarithmTask.base = 2;

            LogarithmTask logarithmResponse = null;
            try {
                logarithmResponse = logarithmerStub.calculate(logarithmTask);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            System.out.println(logarithmResponse.result);
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(future1, future2);
        future.get();
    }
}
