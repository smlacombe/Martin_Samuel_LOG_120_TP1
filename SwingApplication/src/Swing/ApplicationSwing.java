package Swing;
/******************************************************
 Cours :             LOG120
 Session :           Automne 2011
 Groupe :            4
 Projet :            Laboratoire 1
 Étudiant(e)(s) :    Martin Desharnais
                     Samuel Milette-Lacombe
 Code(s) perm. :     DESM21099102
                     MILS26059100
 Professeur :        Sébastien Adam
 Date créée :        2011-09-16
 Date dern. modif. : 2011-10-13
********************************************************/

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import ets.util.containers.*;
import ets.log120.*;
import ets.log120.tp1.ShapeFactory;
import ets.log120.tp1.functors.*;
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
		
	/** Traiter l'item "Start". */
	class DemarrerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			final SwingWorker worker = new SwingWorker() {
				@Override
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
		
		/**
		 * Gère l'approvisionnement en formes à des fins d'affichage.
		 */
		protected void dessinerFormes() {
			if(connectToServer()) {
				getFormsFromServer();
				disconnectClient();
			}
		}
	}

	/** Créer le panneau sur lequel les formes sont dessinées. */
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
							
			Graphics2D g2d = (Graphics2D) g;
			
			sortShapes();
			
			final int DISTANCE_BETWEEN_SHAPES = 5;
			
			int x = DISTANCE_BETWEEN_SHAPES;
			int Y = DISTANCE_BETWEEN_SHAPES;
			int maxHeight = 0;
			
			for (ets.log120.tp1.Shape s : list) {
				if(x + s.getWidth() > CANEVAS_LARGEUR) {
					x = DISTANCE_BETWEEN_SHAPES;
					Y += maxHeight + DISTANCE_BETWEEN_SHAPES;
					maxHeight = 0;
				}
				s.draw(g2d, x, Y);
				x += s.getWidth() + DISTANCE_BETWEEN_SHAPES;
				maxHeight = Math.max(maxHeight, s.getHeight());			
			}
						
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);		
		}
	}
	
	// ////////////////////////////////////////////////
	// Constructeur(s)
	// ////////////////////////////////////////////////
	
	/**
	 * Créer le cadre dans lequel les formes sont dessinées.
	 */
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

	/** Créer le menu "File". */
	private JMenu creerMenuFichier() {
		JMenu menu = ApplicationSupport.addMenu(this, MENU_FICHIER_TITRE,new String[] {"Obtenir formes", MENU_FICHIER_QUITTER });

		getFormsMenuItem = menu.getItem(0);
		getFormsMenuItem.addActionListener(new DemarrerListener());
		
		menu.getItem(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (connectedToServer)
					disconnectClient();
				
				System.exit(0);
			}
		});
		menu.getItem(1).setAccelerator(KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC, MENU_FICHIER_QUITTER_TOUCHE_MASK));

		return menu;
	}
	
	private boolean connectToServer() {
		try {
			assert !connectedToServer;
			assert serverAddress != null;
			assert serverPort != 0;
			
			connection = new ets.log120.tp1.NetworkClient(serverAddress, serverPort);
			connectedToServer = true;
			rafraichirMenus();
			
			System.out.println("Connexion established with \"" + serverAddress + ":" + serverPort + "\"");
			return true;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Le nom du serveur « " + serverAddress + " » est impossible à résoudre.",
					ApplicationSupport.getResource("app.frame.dialog.network.title.dnsError"), JOptionPane.WARNING_MESSAGE);
		} catch (java.net.ConnectException e) {
			JOptionPane.showMessageDialog(null, "Le serveur « " + serverAddress + " » sur le port « " + serverPort + " » est introuvable.",
					ApplicationSupport.getResource("app.frame.dialog.network.title.serverNotFound"), JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void getFormsFromServer() {
		try {
			list.clear();
			repaint();
			for(int i = 0; i < NOMBRE_DE_FORMES; ++i) {
				String request = connection.getShapeRequest();
				System.out.println(request);
				list.pushBack(ShapeFactory.makeShape(request));
			}
			repaint();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (java.net.SocketException e) {
			JOptionPane.showMessageDialog(null, ApplicationSupport.getResource("app.frame.dialog.network.message.unattendedDisconnection"),
				ApplicationSupport.getResource("app.frame.dialog.network.title.disconnectionError"), JOptionPane.WARNING_MESSAGE);
			System.out.println("Connexion diestablished with \"" + serverAddress + ":" + serverPort + "\"");
			workerActif = false;
			connectedToServer = false;
		} catch (IOException e) {
			System.out.println("Hello world");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Créer le menu "Network". */
	private JMenu createNetworkMenu() {
		JMenu menu = ApplicationSupport.addMenu(this, MENU_NETWORK_TITLE,
				new String[] {MENU_NETWORK_SERVER_ADDRESS});
		
		serverAddressMenuItem = menu.getItem(0);
		serverAddressMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String result = JOptionPane.showInputDialog(ApplicationSupport.getResource("app.frame.dialog.network.message.serverInformationQuestion"));
				if (result != null) {
					serverAddress = result.substring(0, result.indexOf(":"));
					serverPort = Integer.parseInt(result.substring(result.indexOf(":") + 1));
					rafraichirMenus();
					//à des fins de déboguage, le texte affiché n'est pas traduit.
					System.out.println("Server address changed to \"" + serverAddress + ":" + serverPort + "\"");
				}
			}
		});
		
		return menu;
	}
	
	/** Créer le menu "Network". */
	private JMenu createOrderMenu() {
		JMenuBar menuBar = getJMenuBar();
		if(menuBar == null) {
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
		}
		
		JMenu menu = new JMenu(ApplicationSupport.getResource(MENU_VIEW_TITLE));
		ButtonGroup group = new ButtonGroup();
		
		menu.add(sortBySequenceNumberAscending  = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_SEQUENCE_NUMBER_ASCENDING));
		menu.add(sortBySequenceNumberDescending = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_SEQUENCE_NUMBER_DESCENDING));
		menu.add(sortByAreaAscending            = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_AREA_ASCENDING));
		menu.add(sortByAreaDescending           = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_AREA_DESCENDING));
		menu.add(sortByShapeTypeAscending       = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_SHAPE_TYPE_ASCENDING));
		menu.add(sortByShapeTypeDescending      = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_SHAPE_TYPE_DESCENDING));
		menu.add(sortByDistanceAscending        = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_DISTANCE_ASCENDING));
		menu.add(sortByDistanceDescending       = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_DISTANCE_DESCENDING));
		menu.add(sortByHeightAscending          = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_HEIGHT_ASCENDING));
		menu.add(sortByHeightDescending         = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_HEIGHT_DESCENDING));
		menu.add(sortByWidthAscending           = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_WIDTH_ASCENDING));
		menu.add(sortByWidthDescending          = createRadioButtonMenuItem(group, MENU_VIEW_SORT_AS_WIDTH_DESCENDING));
		
		sortBySequenceNumberAscending.setSelected(true);
		
		menuBar.add(menu);
		return menu;
	}
	
	private JRadioButtonMenuItem createRadioButtonMenuItem(ButtonGroup group, String title) {
		JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem(ApplicationSupport.getResource(title));
		group.add(radioButton);
		radioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});
		
		return radioButton;
	}
	
	private void sortShapes() {
		if (sortBySequenceNumberAscending.isSelected()) {
			list.sort(new SequenceNumberAscending());
		} else if (sortBySequenceNumberDescending.isSelected()) {
			list.sort(new Not(new SequenceNumberAscending()));
		} else if (sortByAreaAscending.isSelected()) {
			list.sort(new AreaAscending());
		} else if (sortByAreaDescending.isSelected()) {
			list.sort(new Not(new AreaAscending()));
		} else if (sortByShapeTypeAscending.isSelected()) {
			list.sort(new ShapeTypeAscending());
		} else if (sortByShapeTypeDescending.isSelected()) {
			list.sort(new Not(new ShapeTypeAscending()));
		} else if (sortByDistanceAscending.isSelected()) {
			list.sort(new MaxDistanceBetweenPointsAscending());
		} else if (sortByDistanceDescending.isSelected()) {
			list.sort(new Not(new MaxDistanceBetweenPointsAscending()));
		} else if (sortByHeightAscending.isSelected()) {
			list.sort(new HeightAscending());
		} else if (sortByHeightDescending.isSelected()) {
			list.sort(new Not(new HeightAscending()));
		} else if (sortByWidthAscending.isSelected()) {
			list.sort(new WidthAscending());
		} else if (sortByWidthDescending.isSelected()) {
			list.sort(new Not(new WidthAscending()));
		}
	}
	
	/** Créer le menu "Help". */
	private JMenu creerMenuAide() {
		JMenu menu = ApplicationSupport.addMenu(this, MENU_AIDE_TITRE,
				new String[] { MENU_AIDE_PROPOS });

		menu.getItem(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, ApplicationSupport.getResource(MESSAGE_DIALOGUE_A_PROPOS),
					ApplicationSupport.getResource(MENU_AIDE_PROPOS), JOptionPane.INFORMATION_MESSAGE);
		}
		});

		return menu;
	}

	/**
	 * Désactive l'affichage des formes si nécessaire et déconnecte le client.
	 */
	private void disconnectClient() {
		//La précondition est que le client doit être connecté au serveur.
		assert connectedToServer;
		
		if (workerActif) {
			workerActif = false;

			try {
				Thread.sleep(DELAI_QUITTER_MSEC);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			connection.close();
			connectedToServer = false;
			rafraichirMenus();
			System.out.println("Connexion diestablished with \"" + serverAddress + ":" + serverPort + "\"");
		} catch (IOException ie) {
			JOptionPane.showMessageDialog(null, ApplicationSupport.getResource("app.frame.dialog.network.message.disconnectionError"),
			ApplicationSupport.getResource("app.frame.dialog.network.title.disconnectionError"), JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Activer ou désactiver les items du menu selon la sélection. 
	*/
	private void rafraichirMenus() {
		getFormsMenuItem.setEnabled(!connectedToServer && serverAddress != null && serverPort != 0);
		serverAddressMenuItem.setEnabled(!connectedToServer);
	}
	
	/**
	 * Charge les préférences de l'utilisateur.
	 * Établit si le fichier de préférences prefs.properties contient l'adresse du serveur et le port.
	 * Si aucune information de ces infos n'est contenue dans le fichier, l'utilisateur devra les entrer avant de pouvoir se connecter.
	 */
	private void loadPreferences() {
		try {
			serverAddress = ApplicationSupport.getHostName();
			serverPort = ApplicationSupport.getPortNumber();
			System.out.println("Server address taken from configuration file :\"" + serverAddress + ":" + serverPort + "\"");
		} catch (java.util.MissingResourceException ex) {
			serverAddress = null;
			serverPort = 0;			
		}
	}
	
	/**
	 *  Lancer l'exécution de l'application. 
	 */
	public static void main(String[] args) {
		
		/* Créer la fenêtre de l'application. */
		ApplicationSwing cadre = new ApplicationSwing();

		cadre.creerMenuFichier();
		cadre.createOrderMenu();
		cadre.createNetworkMenu();
		cadre.creerMenuAide();
		cadre.loadPreferences();
		cadre.rafraichirMenus();

		/* Centrer la fenêtre. */
		cadre.setLocationRelativeTo(null);

		/* Lancer l'application. */
		ApplicationSupport.launch(cadre, ApplicationSupport
				.getResource("app.frame.title"), 0, 0, CANEVAS_LARGEUR
				+ MARGE_H, CANEVAS_HAUTEUR + MARGE_V);
	}
	
	// ////////////////////////////////////////////////
	// Attribut(s)
	// ////////////////////////////////////////////////
	
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
			MENU_FICHIER_TITRE                            = "app.frame.menus.file.title",
			MENU_FICHIER_QUITTER                          = "app.frame.menus.file.exit",
			MENU_DESSIN_TITRE                             = "app.frame.menus.draw.title",
			MENU_DESSIN_DEMARRER                          = "app.frame.menus.draw.start",
			MENU_DESSIN_ARRETER                           = "app.frame.menus.draw.stop",
			MENU_AIDE_TITRE                               = "app.frame.menus.help.title",
			MENU_AIDE_PROPOS                              = "app.frame.menus.help.about",
			MENU_NETWORK_TITLE                            = "app.frame.menus.network.title",
			MENU_NETWORK_SERVER_ADDRESS                   = "app.frame.menus.network.serverAddress",
			MENU_VIEW_TITLE                               = "app.frame.menus.view.title",
			MENU_VIEW_SORT_AS_SEQUENCE_NUMBER_ASCENDING   = "app.frame.menus.view.sortBy.sequenceNumberAscending",
			MENU_VIEW_SORT_AS_SEQUENCE_NUMBER_DESCENDING  = "app.frame.menus.view.sortBy.sequenceNumberDescending",
			MENU_VIEW_SORT_AS_AREA_ASCENDING              = "app.frame.menus.view.sortBy.areaAscending",
			MENU_VIEW_SORT_AS_AREA_DESCENDING             = "app.frame.menus.view.sortBy.areaDescending",
			MENU_VIEW_SORT_AS_SHAPE_TYPE_ASCENDING        = "app.frame.menus.view.sortBy.shapeTypeAscending",
			MENU_VIEW_SORT_AS_SHAPE_TYPE_DESCENDING       = "app.frame.menus.view.sortBy.shapeTypeDescending",
			MENU_VIEW_SORT_AS_DISTANCE_ASCENDING          = "app.frame.menus.view.sortBy.distanceAscending",
			MENU_VIEW_SORT_AS_DISTANCE_DESCENDING         = "app.frame.menus.view.sortBy.distanceDescending",
			MENU_VIEW_SORT_AS_HEIGHT_ASCENDING            = "app.frame.menus.view.sortBy.heightAscending",
			MENU_VIEW_SORT_AS_HEIGHT_DESCENDING           = "app.frame.menus.view.sortBy.heightDescending",
			MENU_VIEW_SORT_AS_WIDTH_ASCENDING             = "app.frame.menus.view.sortBy.widthAscending",
			MENU_VIEW_SORT_AS_WIDTH_DESCENDING            = "app.frame.menus.view.sortBy.widthDescending",
			MESSAGE_DIALOGUE_A_PROPOS                     = "app.frame.dialog.about";
	private static final int NOMBRE_DE_FORMES = 10;
	private static final long serialVersionUID = 1L;
	private List<ets.log120.tp1.Shape> list = new List<ets.log120.tp1.Shape>();
	private String serverAddress;
	private int serverPort;
	private ets.log120.tp1.NetworkClient connection;
	private boolean workerActif, connectedToServer;
	private JMenuItem getFormsMenuItem, serverAddressMenuItem;
	private JRadioButtonMenuItem sortBySequenceNumberAscending;
	private JRadioButtonMenuItem sortBySequenceNumberDescending;
	private JRadioButtonMenuItem sortByAreaAscending;
	private JRadioButtonMenuItem sortByAreaDescending;
	private JRadioButtonMenuItem sortByShapeTypeAscending;
	private JRadioButtonMenuItem sortByShapeTypeDescending;
	private JRadioButtonMenuItem sortByDistanceAscending;
	private JRadioButtonMenuItem sortByDistanceDescending;
	private JRadioButtonMenuItem sortByHeightAscending;
	private JRadioButtonMenuItem sortByHeightDescending;
	private JRadioButtonMenuItem sortByWidthAscending;
	private JRadioButtonMenuItem sortByWidthDescending;
}
