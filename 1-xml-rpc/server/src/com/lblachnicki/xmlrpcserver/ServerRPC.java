package com.lblachnicki.xmlrpcserver;

public class ServerRPC {
    public Integer echo(int x, int y) {
        return new Integer(x + y);
    }

    public int echoAsync(int x) {
        System.out.println("...execAasy called - processing");
        try {
            Thread.sleep(x);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("... execAsy – finished");
        return (123);
    }
}
