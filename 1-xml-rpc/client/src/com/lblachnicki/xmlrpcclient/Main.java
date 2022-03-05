package com.lblachnicki.xmlrpcclient;

import org.apache.xmlrpc.XmlRpcClient;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        args = new String[]{"http://localhost:10000", "show"};
//        args = new String[]{"http://localhost:10000", "arithmeticMean", "1", "2", "3.0"};
//        args = new String[]{"http://localhost:10000", "geometricMean", "1", "2", "3.0"};

        if (args.length <= 1) {
            System.out.println("Bad format. Expected: [host] [command]. Try command \"show\" for help.");
            return;
        }

        try {
            String host = args[0];
            XmlRpcClient server = new XmlRpcClient(host);

            String command = args[1];
            switch (command) {
                case "arithmeticMean":
                    if (args.length < 2) {
                        System.out.println("Missing parameters. Try \"show\" for help.");
                        break;
                    }
                    executeArithmeticMean(server, Arrays.stream(args).skip(1).collect(Collectors.toList()));
                    break;
                case "geometricMean":
                    if (args.length < 2) {
                        System.out.println("Missing parameters. Try \"show\" for help.");
                        break;
                    }
                    executeGeometricMean(server, Arrays.stream(args).skip(1).collect(Collectors.toList()));
                    break;
                case "show":
                    executeShow(server);
                    break;
                default:
                    System.out.println("Unknown command. Try \"show\" for help.");
            }
        } catch (Exception exception) {
            System.err.println("XML-RPC client: " + exception);
        }
    }

    private static void executeArithmeticMean(XmlRpcClient server, List<String> parameters) {
        Vector<Object> requestParams = new Vector<>();
        requestParams.addElement(parameters.toArray());

        AC callback = new AC();
        server.executeAsync("Server.arithmeticMean", requestParams, callback);

        System.out.println("What's the arithmetic mean?");
    }

    private static void executeGeometricMean(XmlRpcClient server, List<String> parameters) {
        Vector<Object> requestParams = new Vector<>();
        requestParams.addElement(parameters.toArray());

        AC callback = new AC();
        server.executeAsync("Server.geometricMean", requestParams, callback);

        System.out.println("What's the geometric mean?");
    }

    private static void executeShow(XmlRpcClient server) {
        Vector<Object> requestParams = new Vector<>();

        AC callback = new AC();
        server.executeAsync("Server.show", requestParams, callback);
    }
}
