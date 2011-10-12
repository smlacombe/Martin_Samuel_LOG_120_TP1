package Swing;
/******************************************************
 Cours :             LOGnnn
 Session :           Saison (�t�, automne, hiver) 200X
 Groupe :            n
 Projet :            Laboratoire #n
 �tudiant(e)(s) :    Marcel Marceau
                     Edith Piaf
 Code(s) perm. :     MARM987341987
                     PIAE324398724
 Professeur :        Groucho Marx
 Date cr��e :        2002-05-28
 Date dern. modif. : 2005-05-01
 
*******************************************************
 Historique des modifications
*******************************************************
  2002-05-28	Cris Fuhrman : Version initiale
  
  2004-03-07	Cris Fuhrman : Int�gration de SwingWorker 
                requierant la classe additionnelle 
                SwingWorker.java, utilisation des variables 
                constantes, formatage de code source, 
                organisation des imports, etc.

  2005-05-01	Cris Fuhrman : Int�gration de ApplicationSupport
  				requierant la classe additionnelle
  				ApplicationSupport.java et les fichiers
  				prefs.properties, app_xx.properties (o� xx est le
  				code de la langue, p. ex. fr = fran�ais, en = anglais).
  				Suppression de l'interface Shape.
  				
  2006-05-03	S�bastien Adam :
  
                Uniformisation et maintenance du code.

                Ajout des classes pour la gestion des
                items de menu. Un �couteur ajout� pour chaque item 
                (DemarrerListener, ArreterListener, QuitterListener, 
                AProposDeListener).  
                
                La classe ApplicationSwing n'impl�mente plus ActionListener. 
                Elle d�l�gue la gestion des items.
                
                Plus besion d'un "if else if" dans la methode actionPerformed pour 
                ex�cuter l'action associ�e � un item. Le code est plus
                simple � comprendre, lire et maintenir.	

 La distribution originale se trouve � 
 https://cours.ele.etsmtl.ca/academique/log120/notesdecours/exemples/lab/lab1/ApplicationSwing.zip
********************************************************/

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.rmi.ConnectException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import ets.util.containers.*;
import ets.log120.*;
import ets.log120.tp1.ShapeFactory;
/**
 * <code>ApplicationSwing</code> est un exemple d'une
 * application en Java qui fournit une interface Swing, avec un simple
 * menu et un dessin.
 *
 * <h4>References</h4> 
 * <ul> 
 *
 * <li>C. Fuhrman, &quot;Notes de cours de LOG120,&quot; &Eacute;cole
 * de technologie sup&eacute;rieure, Montr&eacute;al, Qu&eacute;bec,
 * Canada, 2002
 *
 * <li>Xemacs (for generation of the initial template), <a target="_top" 
 * href="http://www.xemacs.org">www.xemacs.org</a>, 2002 
 *
 * <li><a target="_top" 
 * href="http://java.sun.com/docs/books/tutorial/uiswing/painting/overview.html">Overview
 * of Custom Painting</a>, une partie du tutoriel Java de Sun, 2002.
 *
 * <li>Java Software, <a target="_top" 
 * href="http://java.sun.com/j2se/javadoc/writingdoccomments/index.html">&quot;How
 * to Write Doc Comments for the Javadoc<sup>TM</sup> Tool,&quot;</a>
 * 2002
 *
 * </ul>
 *
 * Distribution originale &agrave; partir du 
 * <a target="_top" href="https://cours.ele.etsmtl.ca/academique/log120/">site Web</a>
 * du cours LOG120.
 * 
 * Created: Tue May 28 11:31:18 2002
 *
 * @author <a href="mailto:christopher.fuhrman@etsmtl.ca">Christopher Fuhrman</a>
 *
 * @version 1.1
 */

public class ApplicationSwing extends JFrame {
	private static final int CANEVAS_HAUTEUR = 500;
	private static final int CANEVAS_LARGEUR = 500;
	private static final int DELAI_ENTRE_FORMES_MSEC = 1000;
	private static final int DELAI_QUITTER_MSEC = 200;
	private static final int FORME_MAX_HAUTEUR = 200;
	private static final int FORME_MAX_LARGEUR = 200;
	private static final int MARGE_H = 50;
	private static final int MARGE_V = 60;
	private static final int MENU_DESSIN_ARRETER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_ARRETER_TOUCHE_RACC = KeyEvent.VK_A;
	private static final int MENU_DESSIN_DEMARRER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D;
	private static final int MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;
	private static final String
			MENU_FICHIER_TITRE = "app.frame.menus.file.title",
			MENU_FICHIER_QUITTER = "app.frame.menus.file.exit",
			MENU_DESSIN_TITRE = "app.frame.menus.draw.title",
			MENU_DESSIN_DEMARRER = "app.frame.menus.draw.start",
			MENU_DESSIN_ARRETER = "app.frame.menus.draw.stop",
			MENU_AIDE_TITRE = "app.frame.menus.help.title",
			MENU_AIDE_PROPOS = "app.frame.menus.help.about";
	private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";
	private static final int NOMBRE_DE_FORMES = 10;
	private static final long serialVersionUID = 1L;
	private Queue<ets.log120.tp1.Shape> queue = new Queue<ets.log120.tp1.Shape>();
	private String serverAddress;
	private int serverPort;
	private ets.log120.tp1.NetworkClient connexion;
	private boolean workerActif, connectedToServer;
	private JMenuItem arreterMenuItem, demarrerMenuItem, disconnectMenuItem, connectMenuItem, serverAddressMenuItem;
	
	/* Traiter l'item "About...". */
	class AProposDeListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, ApplicationSupport
					.getResource(MESSAGE_DIALOGUE_A_PROPOS), ApplicationSupport
					.getResource(MENU_AIDE_PROPOS),
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/* Traiter l'item "Stop". */
	class ArreterListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			workerActif = false;
			rafraichirMenus();
		}
	}
	
	/* Traiter l'item "Start". */
	class DemarrerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			final SwingWorker worker = new SwingWorker() {
				public Object construct() {
					dessinerFormes();
					workerActif = false;
					rafraichirMenus();
					return new Integer(0);
				}
			};
			
			worker.start();
			workerActif = true;
			rafraichirMenus();
		}
		
		protected void dessinerFormes() {
			try {
				//connexion = new ets.log120.tp1.NetworkClient("localhost", 10000);
				while (workerActif) {
					String request = connexion.getShapeRequest();
					System.out.println(request);
					queue.push(ShapeFactory.makeShape(request));
					if(queue.size() > NOMBRE_DE_FORMES)
						queue.pop();
					
					repaint();
					try {
						Thread.sleep(DELAI_ENTRE_FORMES_MSEC);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	 			}
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (java.net.SocketException e1) {
					JOptionPane.showMessageDialog(null, "La connexion avec le serveur a été interrompue. Vérifiez que le serveur est encore ouvert.","Erreur de serveur introuvable", JOptionPane.WARNING_MESSAGE);
					workerActif = false;
					connectedToServer = false;
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* Traiter l'item "Exit". */
	class QuitterListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (workerActif) {
				workerActif = false;

				try {
					Thread.sleep(DELAI_QUITTER_MSEC);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if (connectedToServer)
				disconnectClient();
			
			System.exit(0);
		}
	}

	/* Cr�er le panneau sur lequel les formes sont dessin�es. */
	class CustomCanvas extends JPanel {
		private static final long serialVersionUID = 1L;

		public CustomCanvas() {
			setSize(getPreferredSize());
			setMinimumSize(getPreferredSize());
			CustomCanvas.this.setBackground(Color.white);
		}

		public Dimension getPreferredSize() {
			return new Dimension(CANEVAS_LARGEUR, CANEVAS_HAUTEUR);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//on efface l'�cran avec un rectangle vierge de la taille de la fen�tre
			//g.clearRect(0, 0, getContentPane().getWidth(), getContentPane().getHeight());	
				
			Graphics2D g2d = (Graphics2D) g;
			
			for (ets.log120.tp1.Shape s : queue)
				s.draw(g2d);
						
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);		
		}
	}
	
	/* - Constructeur - Cr�er le cadre dans lequel les formes sont dessin�es. */
	public ApplicationSwing() {
		getContentPane().add(new JScrollPane(new CustomCanvas()));
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (connectedToServer)
					disconnectClient();

				System.exit(0);
			}
		});
	}

	/* Cr�er le menu "Draw". */
	private JMenu creerMenuDessiner() {
		JMenu menu = ApplicationSupport.addMenu(this, MENU_DESSIN_TITRE,
				new String[] { MENU_DESSIN_DEMARRER, MENU_DESSIN_ARRETER });

		demarrerMenuItem = menu.getItem(0);
		demarrerMenuItem.addActionListener(new DemarrerListener());
		demarrerMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_DEMARRER_TOUCHE_RACC,
				MENU_DESSIN_DEMARRER_TOUCHE_MASK));

		arreterMenuItem = menu.getItem(1);
		arreterMenuItem.addActionListener(new ArreterListener());
		arreterMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_ARRETER_TOUCHE_RACC,
				MENU_DESSIN_ARRETER_TOUCHE_MASK));

		return menu;
	}

	/* Cr�er le menu "File". */
	private JMenu creerMenuFichier() {
		JMenu menu = ApplicationSupport.addMenu(this, MENU_FICHIER_TITRE,
				new String[] { MENU_FICHIER_QUITTER });

		menu.getItem(0).addActionListener(new QuitterListener());
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC,
						MENU_FICHIER_QUITTER_TOUCHE_MASK));

		return menu;
	}
	
	
	private JMenu createNetworkMenu() {
		JMenu menu = ApplicationSupport.addMenu(this, "Network", new String[] {"Server address", "Connect", "Disconnect"});
		
		serverAddressMenuItem = menu.getItem(0);
		serverAddressMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String result = JOptionPane.showInputDialog("Quel est le nom d'hôte et le port du serveur de forme?");
				serverAddress = result.substring(0, result.indexOf(":"));
				serverPort = Integer.parseInt(result.substring(result.indexOf(":") + 1));
				rafraichirMenus();
				System.out.println("Server address changed to \"" + serverAddress + ":" + serverPort + "\"");
			}
		});
		
		connectMenuItem = menu.getItem(1);
		connectMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					connexion = new ets.log120.tp1.NetworkClient(serverAddress, serverPort);
					connectedToServer = true;
					rafraichirMenus();
					System.out.println("Connexion established with \"" + serverAddress + ":" + serverPort + "\"");
				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(null, "Le nom du serveur « " + serverAddress + " » est impossible à résoudre.","Erreur DNS", JOptionPane.WARNING_MESSAGE);
				} catch (java.net.ConnectException e) {
					JOptionPane.showMessageDialog(null, "Le serveur « " + serverAddress + " » sur le port « " + serverPort + " » est introuvable.","Erreur de serveur introuvable", JOptionPane.WARNING_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		disconnectMenuItem = menu.getItem(2);
		disconnectMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				disconnectClient();
			}
		});
		
		return menu;
	}
	

	/* Cr�er le menu "Help". */
	private JMenu creerMenuAide() {
		JMenu menu = ApplicationSupport.addMenu(this, MENU_AIDE_TITRE,
				new String[] { MENU_AIDE_PROPOS });

		menu.getItem(0).addActionListener(new AProposDeListener());

		return menu;
	}

	private void disconnectClient() {
		assert (connectedToServer);
		
		try {
			connexion.close();
			connectedToServer = false;
			workerActif = false;
			rafraichirMenus();
			System.out.println("Connexion diestablished with \"" + serverAddress + ":" + serverPort + "\"");
		} catch (IOException ie) {
			JOptionPane.showMessageDialog(null, "Erreur dans la déconnexion du serveur","Erreur de déconnexion du serveur", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/* Activer ou d�sactiver les items du menu selon la s�lection. */
	private void rafraichirMenus() {
		demarrerMenuItem.setEnabled(connectedToServer && !workerActif);
		arreterMenuItem.setEnabled(connectedToServer && workerActif);
		serverAddressMenuItem.setEnabled(!connectedToServer);
		
		if (serverAddress == null && serverPort == 0)
			try {
				serverAddress = ApplicationSupport.getHostName();
				serverPort = ApplicationSupport.getPortNumber();
				System.out.println("Server address taken from configuration file :\"" + serverAddress + ":" + serverPort + "\"");
			} catch (java.util.MissingResourceException ex) {
				serverAddress = null;
				serverPort = 0;			
			}
			
		connectMenuItem.setEnabled(!connectedToServer && serverAddress != null && serverPort != 0);
		disconnectMenuItem.setEnabled(connectedToServer);
	}
	
	/* Lancer l'ex�cution de l'application. */
	public static void main(String[] args) {
		
		/* Cr�er la fen�tre de l'application. */
		ApplicationSwing cadre = new ApplicationSwing();

		cadre.creerMenuFichier();
		cadre.creerMenuDessiner();
		cadre.createNetworkMenu();
		cadre.creerMenuAide();
		cadre.rafraichirMenus();

		/* Centrer la fen�tre. */
		cadre.setLocationRelativeTo(null);

		/* Lancer l'application. */
		ApplicationSupport.launch(cadre, ApplicationSupport
				.getResource("app.frame.title"), 0, 0, CANEVAS_LARGEUR
				+ MARGE_H, CANEVAS_HAUTEUR + MARGE_V);
	}
}
