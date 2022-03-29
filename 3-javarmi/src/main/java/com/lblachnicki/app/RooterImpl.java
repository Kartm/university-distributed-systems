package com.lblachnicki.app;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RooterImpl extends UnicastRemoteObject implements Rooter {
    protected RooterImpl() throws RemoteException {
        super();
    }

    @Override
    public RootTask calculate(RootTask task) throws RemoteException {
        task.result = Math.pow(task.x, 1.0 / task.degree);

        return task;
    }
}
