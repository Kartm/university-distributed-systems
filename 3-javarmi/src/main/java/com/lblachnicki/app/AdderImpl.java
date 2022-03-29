package com.lblachnicki.app;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicInteger;

public class AdderImpl extends UnicastRemoteObject implements Adder {
    AtomicInteger acc = new AtomicInteger(0);

    protected AdderImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int x, int y) throws RemoteException {
        acc.set(acc.addAndGet(x + y));

        return acc.get() + x + y;
    }

    @Override
    public Name addPrefix(Name n) throws RemoteException {
        n.name = "pre-" + n.name;

        return n;
    }
}
