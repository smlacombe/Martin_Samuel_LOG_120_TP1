package ets.log120.tp1;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Client chargé de communique avec le serveur.
 * 
 * @author Martin Desharnais
 * @author Samuel Milette-Lacombe
 */
public class NetworkClient {
	
	//////////////////////////////////////////////////
	// Constructor(s)
	//////////////////////////////////////////////////
	
	/**
	 * Construit un client et le connecte au serveur à l'aide de l'adresse et du port reçus en paramètre.
	 */
	public NetworkClient(String serverName, int serverPort) throws UnknownHostException, IOException {
		socket = new java.net.Socket(serverName, serverPort);
		out = new java.io.PrintStream(socket.getOutputStream());
		in = new java.io.BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
	}
	
	//////////////////////////////////////////////////
	// Method(s)
	//////////////////////////////////////////////////
	
	/**
	 * Retourne la prochaine requête reçue du serveur.
	 */
	public String getShapeRequest() throws IOException {
			in.readLine();
			out.println("GET");
			return in.readLine();
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
