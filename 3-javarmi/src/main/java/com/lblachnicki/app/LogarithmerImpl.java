package com.lblachnicki.app;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LogarithmerImpl extends UnicastRemoteObject implements Logarithmer {
    protected LogarithmerImpl() throws RemoteException {
        super();
    }

    @Override
    public LogarithmTask calculate(LogarithmTask task) throws RemoteException {
        task.result = Math.log(task.x) / Math.log(task.base); // uses identity

        return task;
    }
}
