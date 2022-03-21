package main.java.io.grpc.examples.helloworld;

public class MyUser {
    public String username;
    public String password;
    public double myNumber;

    public MyUser(String username, String password, double myNumber) {
        this.username = username;
        this.password = password;
        this.myNumber = myNumber;
    }
}