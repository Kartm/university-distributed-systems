package com.lblachnicki.xmlrpcserver;

import java.util.Arrays;
import java.util.Vector;

public class ServerRPC {
    public String show() {
        return "\n" +
                "Available commands:\n" +
                "show | Shows available commands\n" +
                "arithmeticMean [number] [number] [number] [...] | Calculates arithmetic mean of any number of parameters\n" +
                "geometricMean [number] [number] [number] [...] | Calculates geometric mean of any number of parameters";
    }

    public String arithmeticMean(Vector<Object> args) {
        System.out.println("Processing arithmetic mean...");

        double[] input = Arrays.stream(args.toArray())
                .mapToDouble(num -> Double.parseDouble(num.toString()))
                .toArray();

        double sum = Arrays.stream(input).sum();

        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }

        System.out.println("Processing arithmetic mean finished!");
        return String.valueOf(sum / (double)input.length);
    }

    public String geometricMean(Vector<Object> args) {
        System.out.println("Processing geometric mean...");

        double[] input = Arrays.stream(args.toArray())
                .mapToDouble(num -> Double.parseDouble(num.toString()))
                .toArray();

        double sum = Arrays.stream(input).sum();

        try {
            Thread.sleep(1500);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }

        System.out.println("Processing geometric mean finished!");
        return String.valueOf(Math.pow(sum, 1.0 / input.length));
    }
}