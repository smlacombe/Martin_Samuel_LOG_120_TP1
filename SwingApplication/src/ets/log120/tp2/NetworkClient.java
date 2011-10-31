package ets.log120.tp2;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Client chargé de communiquer avec le serveur.
 * 
 * @author Martin Desharnais
 * @author Samuel Milette-Lacombe
 */
public class NetworkClient {
	
	//////////////////////////////////////////////////
	// Constructor(s)
	//////////////////////////////////////////////////
	
	/**
	 * Construit un client et le connecte au serveur à l'aide de l'adresse et du port reçu en paramètre.
	 */
	public NetworkClient(String serverAddress, int serverPort) throws UnknownHostException, IOException {
		socket = new java.net.Socket(serverAddress, serverPort);
		out = new java.io.PrintStream(socket.getOutputStream());
		in = new java.io.BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
	}
	
	//////////////////////////////////////////////////
	// Method(s)
	//////////////////////////////////////////////////
	
	/**
	 * Retourne la prochaine requête reçue du serveur.
	 */
	public String getShapeRequest() throws IOException, java.net.SocketException {
		in.readLine();
		out.println("GET");
		
		String result = in.readLine();
		
		if (result == null)
			throw new java.net.SocketException();
		
		return result;
	}
	
	/**
	 * Ferme la connexion avec le serveur.
	 */
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
