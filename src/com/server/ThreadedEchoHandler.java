package com.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedEchoHandler implements Runnable {

	private Socket incoming;
	private Scanner in;

	public ThreadedEchoHandler(Socket i) {
		incoming = i;
	}

	@Override
	public void run() {
		try {
			try {
				InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();

				in = new Scanner(inStream);
				PrintWriter out = new PrintWriter(outStream, true /* autoFluch */);
				
				out.println("Hello! Welcome to the server :D Press * to exit.");
				
				//Echo Client input
				boolean done = false;
				while (!done && in.hasNextLine()) {
					String line = in.nextLine();
					
					if (line.trim().equals("*")) {
						done = true;
						out.println("Bye!");
						break;
					}
					out.println("Echo: " + line);
				}
			} finally {
				in.close();
				incoming.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
