/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.grpc.examples.helloworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import main.java.io.grpc.examples.helloworld.MyUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class HelloWorldServer {
  private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());

  private Server server;

  private void start() throws IOException {
    /* The port on which the server should run */
    int port = 50051;
    server = ServerBuilder.forPort(port)
        .addService(new GreeterImpl())
        .build()
        .start();
    logger.info("Server started, listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        try {
          HelloWorldServer.this.stop();
        } catch (InterruptedException e) {
          e.printStackTrace(System.err);
        }
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    final HelloWorldServer server = new HelloWorldServer();
    server.start();
    server.blockUntilShutdown();
  }

  static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    List<MyUser> users = new ArrayList<>();

    @Override
    public void register(RegisterRequest req, StreamObserver<RegisterReply> responseObserver) {
      users.add(new MyUser(req.getUsername(), req.getPassword(), req.getMyNumber()));

      RegisterReply reply = RegisterReply.newBuilder()
              .setStatus("OK")
              .setMessage("Register successful")
              .build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }

    @Override
    public void login(LoginRequest req, StreamObserver<LoginReply> responseObserver) {
      List<MyUser> result = users.stream().filter(u ->
              u.username.contentEquals(req.getUsername()) && u.password.contentEquals(req.getPassword())
      ).collect(Collectors.toList());

      LoginReply reply;

      if(result.size() > 0) {
        reply = LoginReply.newBuilder()
                .setStatus("OK")
                .setMessage("Login successful")
                .setMyNumber(result.get(0).myNumber)
                .build();
      } else {
        reply = LoginReply.newBuilder()
                .setStatus("BAD")
                .setMessage("Login failed")
                .build();
      }
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }
  }
}