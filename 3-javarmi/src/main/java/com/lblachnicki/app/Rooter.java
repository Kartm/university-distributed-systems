package com.lblachnicki.app;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Rooter extends Remote {
    RootTask calculate(RootTask task) throws RemoteException;
}
