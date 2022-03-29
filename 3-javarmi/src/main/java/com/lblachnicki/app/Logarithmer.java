package com.lblachnicki.app;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Logarithmer extends Remote {
    LogarithmTask calculate(LogarithmTask task) throws RemoteException;
}
