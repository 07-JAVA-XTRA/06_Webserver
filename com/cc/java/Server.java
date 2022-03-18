
package com.cc.java;

import java.io.IOException;
import java.net.ServerSocket;

class Server {

  public static void main(String[] args) 
  {
  
   try (ServerSocket serverSocket = new ServerSocket(8080)) {
   
    output("Hi from " + serverSocket + " \nI'm ready to serve your request!");






  } catch (IOException e) {
    e.printStackTrace();
  }

 
  }

  private static void output(String outStr) {
    System.out.println(outStr);
  }



} // EoC
