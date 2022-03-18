
package com.cc.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

  public static void main(String[] args) 
  {
  
   try (ServerSocket serverSocket = new ServerSocket(8080)) {
   
    output("Hi from " + serverSocket + " \nI'm ready to serve your request!");

    while (true) { 
    // keeps the server alive!

        try(Socket client = serverSocket.accept()){
          output("msg: " + client.toString());
          
          // request als Byte-Steam
          InputStreamReader inputStream = new InputStreamReader(client.getInputStream());
          // Bytes --> Characters
          BufferedReader bfReader = new BufferedReader(inputStream);
          // Chars in Puffer
          StringBuilder request = new StringBuilder();
          // Puffer --> String
          String tmpLine = bfReader.readLine(); // one Line
          while (!tmpLine.isBlank()) {  // Leerzeile als Trenner
            request.append(tmpLine + "\r\n");
            tmpLine = bfReader.readLine(); // zeile anhängen
          }

          // Request als String ...
          output("--> REQUEST: ");
          output("--> " + request);





        }
    }

  } catch (IOException e) {
    e.printStackTrace();
  }

 
  }

  private static void output(String outStr) {
    System.out.println(outStr);
  }



} // EoC
