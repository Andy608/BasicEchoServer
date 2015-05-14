package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		
		try {
			ServerSocket s = new ServerSocket(608);
			
			while (true) {
				Socket incoming = s.accept();
				System.out.println("New user connected to the server!");
				Runnable r = new ThreadedEchoHandler(incoming);
				Thread t = new Thread(r);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
