
package com.cc.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
          
          /** INPUT - Stream (vom Browser) | REQUEST*/

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

          /** Output - Stream (zum Browser) | RESPONSE*/
  
          OutputStream clientOutput = client.getOutputStream();
          clientOutput.write(("HTTP/1.1 200 OK \r\n").getBytes()); // Bytes
          clientOutput.write(("\r\n").getBytes());
          clientOutput.write(("Hello World").getBytes());
          
          clientOutput.flush();
          clientOutput.close();

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
