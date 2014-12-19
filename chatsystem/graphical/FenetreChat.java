package chatsystem.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import chatsystem.ChatSystem;
import chatsystem.User;
import chatsystem.graphical.notification.FileNotifications;
import chatsystem.graphical.notification.MessageDisplay;
import chatsystem.graphical.notification.Notification;
import chatsystem.graphical.notification.NotificationDisplay;
import chatsystem.graphical.notification.StatusFile;

// TODO: Auto-generated Javadoc
/**
 * The Class FenetreChat.
 */
public class FenetreChat extends JFrame implements ListSelectionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1574189318933480883L;

	/** The Constant WIDTH. */
	public static final int WIDTH=1000;
	
	/** The Constant HEIGHT. */
	public static final int HEIGHT=600;

	/** The send file button. */
	private JButton deconnexionButton,sendButton, sendFileButton;
	
	/** The message text area. */
	private JTextArea messageTextArea;
	
	/** The liste. */
	private JList <User>liste;
	
	/** The conversation. */
	private JList <NotificationDisplay>conversation;
	
	/** The nom user. */
	private JLabel labelListe,nomUser;

	/** The fc. */
	final JFileChooser fc = new JFileChooser();



	/** The selected value. */
	private User selectedValue;

	/** The dialog. */
	final JDialog dialog=new JDialog();

	/** The model. */
	final DefaultListModel<User> model=new DefaultListModel<User>();

	/** The model conversation. */
	final DefaultListModel<NotificationDisplay> modelConversation=new DefaultListModel<NotificationDisplay>();

	/** The gui. */
	private ChatGUI gui;




	/**
	 * Instantiates a new fenetre chat.
	 *
	 * @param gui the gui
	 */
	public FenetreChat(ChatGUI gui) {
		super(ChatSystem.NOM);
		this.gui=gui;

		if (gui==null){
			System.out.println("gui null");
		}




		// Création du panneau
		JPanel panneau = new JPanel();
		setBounds(150,100,WIDTH,HEIGHT);

		// Panel de la fenetre
		panneau.setBackground(new Color(255,255,255));
		panneau.setBorder(new EmptyBorder(0, 0, 0, 0));
		panneau.setLayout(new BorderLayout(0,0));
		setContentPane(panneau);

		JPanel pane = new JPanel();
		pane.setBackground(new Color(240,240,240));
		pane.setBounds(0, 0, WIDTH, HEIGHT);
		panneau.add(pane, BorderLayout.CENTER);
		pane.setLayout(new BorderLayout(0,0));


		JPanel paneGauche = new JPanel();
		paneGauche.setBackground(new Color(52, 73, 94));
		paneGauche.setPreferredSize(new Dimension(250,700));
		paneGauche.setLayout(new BorderLayout());
		pane.add(paneGauche, BorderLayout.WEST);


		//Label de la liste
		this.labelListe = new JLabel("<html><p style='color:white; font-family:Arial; font-size : 12px; margin-left:10px;'>List of connected users<p></html>");
		labelListe.setPreferredSize(new Dimension(250,40));
		paneGauche.add(labelListe, BorderLayout.NORTH);

		// création de la liste
		liste = new JList<User>(model);
		JScrollPane scrollPaneList = new JScrollPane(liste);
		liste.setBackground(new Color(52, 152, 219));
		liste.addListSelectionListener(this);
		paneGauche.add(scrollPaneList, BorderLayout.CENTER);

		// Button deconnexion
		deconnexionButton = new JButton("<html><p style='color:black; font-family:Arial; font-size : 12px;'>Disconnect<p></html>");
		deconnexionButton.setPreferredSize(new Dimension(200,40));
		paneGauche.add(deconnexionButton, BorderLayout.SOUTH);


		// PANEL BLEU AU CENTRE
		JPanel paneCenter = new JPanel();
		paneCenter.setBackground(new Color(230, 126, 34));
		paneCenter.setPreferredSize(new Dimension(900,700));
		paneCenter.setLayout(new BorderLayout());
		pane.add(paneCenter, BorderLayout.CENTER);
		//gui.whoAmI().getName() +
		nomUser = new JLabel("<html><p style='color:white; font-family:Arial; font-size : 14px; margin-left:10px;'>Welcome " +  " to chat System<p></html>");
		nomUser.setPreferredSize(new Dimension(250,40));
		paneCenter.add(nomUser, BorderLayout.NORTH);


		//conversation
		conversation = new JList<NotificationDisplay>(modelConversation);
		JScrollPane scrollPaneConversation = new JScrollPane(conversation);
		conversation.setBackground(new Color(236, 240, 241));
		//conversation.setEnabled(false);
		conversation.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				conversation.clearSelection();
				conversation.removeSelectionInterval(0, 100);
			}
		});
		//UIManager.put("Label.disabledForeground", Color.BLACK);
		paneCenter.add(scrollPaneConversation, BorderLayout.CENTER);

		//BottomPanel est contenu dans Right Panel et comporte le textfield et le bouton Send
		JPanel bottomPane = new JPanel();
		bottomPane.setBackground(new Color(52, 73, 94));
		bottomPane.setLayout(new BorderLayout());
		paneCenter.add(bottomPane, BorderLayout.SOUTH);


		messageTextArea = new JTextArea();
		messageTextArea.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane scrollPane = new JScrollPane(messageTextArea);
		scrollPane.setPreferredSize(new Dimension(200,40));
		bottomPane.add(scrollPane, BorderLayout.CENTER);

		JPanel sendPane = new JPanel();
		sendPane.setLayout(new BorderLayout());
		sendPane.setBackground(new Color(236, 240, 241));
		bottomPane.add(sendPane, BorderLayout.EAST);

		sendButton = new JButton("<html><p style='color:black; font-family:Arial; font-size : 12px;'>Send<p></html>");
		sendButton.setPreferredSize(new Dimension(100,40));
		sendPane.add(sendButton, BorderLayout.CENTER);

		sendFileButton = new JButton("<html><p style='color:black; font-family:Arial; font-size : 12px;'>Send File<p></html>");
		sendPane.add(sendFileButton, BorderLayout.NORTH);



		//byte [] bytes1={(byte)192,(byte)2,(byte)10,(byte)1};

		// TEST SCROLL LIST
//						int i=0;
//						for (i=0; i<50;i++){
//							User A=null;
//							try {
//								A = new User("toto"+i,InetAddress.getByAddress(bytes1));
//							} catch (UnknownHostException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							this.addtoList(A);
//						}






		deconnexionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FenetreChat.this.gui.disconnect();
				FenetreChat.this.dispose();
				new FenetreConnect().setGui(FenetreChat.this.gui);
				

			}
		});




		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String message =messageTextArea.getText();
				messageTextArea.setText("");
				FenetreChat.this.gui.sendMessage(message);

			}
		});


		sendFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int returnVal = fc.showOpenDialog(FenetreChat.this);

					if (returnVal == JFileChooser.APPROVE_OPTION){
						File file = fc.getSelectedFile();
						System.out.println("Fichier choisi : "+file);
						System.out.println(file.getName());
						FenetreChat.this.gui.sendFile(file);
					}
				} catch (Exception e){
					e.printStackTrace();
				}

			}
		});



		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

	/**
	 * Addto list.
	 *
	 * @param u the u
	 */
	public void addtoList(User u) {
		this.model.addElement(u);
		System.out.println(this.model.toString());
	}

	/**
	 * Removefrom list.
	 *
	 * @param u the u
	 */
	public void removefromList(User u){
		User user;
		for (int i=0;i<model.getSize();i++){
			user=(User)model.get(i);
			if (user.equals(u)){
				model.remove(i);
			}
		}
	}


	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public User getValue(){
		return this.selectedValue;
	}


	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		this.selectedValue=(User)liste.getSelectedValue();
		this.gui.selectUser();
	}


	/**
	 * Adds the message.
	 *
	 * @param messageDisplay the message display
	 */
	public void addMessage(MessageDisplay messageDisplay){
		this.modelConversation.addElement(messageDisplay);
		int lastIndex = conversation.getModel().getSize() - 1;

		if (lastIndex >= 0) {

			conversation.ensureIndexIsVisible(lastIndex);

		}
	}

	/**
	 * Adds the message.
	 *
	 * @param notification the notification
	 */
	public void addMessage(Notification notification){
		this.modelConversation.addElement(notification);
	}
	
	/**
	 * Adds the message.
	 *
	 * @param statusFile the status file
	 */
	public void addMessage(StatusFile statusFile){
		this.modelConversation.addElement(statusFile);
	}
	
	/**
	 * Adds the message.
	 *
	 * @param fileNotification the file notification
	 */
	public void addMessage(FileNotifications fileNotification){
		this.modelConversation.addElement(fileNotification);
	}

	/**
	 * Display dialog.
	 *
	 * @param username the username
	 * @param name the name
	 */
	public void displayDialog(String username,String name){
		Object[] options = {"Yes","No"};

		int n = JOptionPane.showOptionDialog(this,
				"Would you like to receive : "+name+" from "+username+"?",
				"A file is coming",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				options[0]); //default button title

		if(n==0){

			fc.setCurrentDirectory(new java.io.File("."));
			fc.setDialogTitle("Save...");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);
			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
				File file=fc.getSelectedFile();
				gui.acceptFile(name,file);
			}
			
		} else{
			gui.declineFile(name);
		}

	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try{

			FenetreChat frame = new FenetreChat(null);



			frame.setVisible(true);
		} catch(Exception e) {
			e.printStackTrace();
		}


	}









}
