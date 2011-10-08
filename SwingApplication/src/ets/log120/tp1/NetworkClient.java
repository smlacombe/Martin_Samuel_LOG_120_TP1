package ets.log120.tp1;

import java.io.IOException;
import java.net.UnknownHostException;

public class NetworkClient {
	
	//////////////////////////////////////////////////
	// Constructor(s)
	//////////////////////////////////////////////////
	
	public NetworkClient(String serverName, int serverPort) throws UnknownHostException, IOException {
		socket = new java.net.Socket(serverName, serverPort);
		out = new java.io.PrintStream(socket.getOutputStream());
		in = new java.io.BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
	}
	
	//////////////////////////////////////////////////
	// Method(s)
	//////////////////////////////////////////////////
	
	public String getShapeRequest() throws IOException {
			in.readLine();
			out.println("GET");
			return in.readLine();
	}
	
	public void close() throws IOException {
		in.readLine();
		out.println("END");
		socket.close();
	}
	
	//////////////////////////////////////////////////
	// Attribut(s)
	//////////////////////////////////////////////////
	java.net.Socket socket = null;
	java.io.PrintStream out = null;
	java.io.BufferedReader in = null;
}
