package gui;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import wonders.GamePlay;

import javax.swing.JTextField;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class ServerFrame extends JFrame {
	// 
	private static final long serialVersionUID = 1000L;
	// jpanel containing objects
	private JPanel contentPane;
	// textfield of number of players
	private JTextField numberOfPlayersField;
	// wait message (shown while waiting for clients)
	private JTextArea waitingText;
	// text area containing names of players connected yet
	public JTextArea connectedPlayerNameArea;


	


	 public ServerFrame(GamePlay game) {
		// initializing design of the player frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// initializing design of the player number of players  field
		numberOfPlayersField = new JTextField();
		numberOfPlayersField.setBounds(218, 50, 86, 20);
		contentPane.add(numberOfPlayersField);
		numberOfPlayersField.setColumns(10);
		
		// initializing design of the waiting text area
		waitingText = new JTextArea("Waiting for players to connect......");
		waitingText.setBounds(73, 11, 297, 22);
		contentPane.add(waitingText);
		waitingText.setVisible(false);
		
		// initializing design of number of players text area
		JTextArea textArea = new JTextArea("Number of Players");
		textArea.setEditable(false);
		textArea.setBounds(33, 48, 140, 22);
		contentPane.add(textArea);
		
		// initializing design of players connected text area
		JTextArea connectedPlayerNameLabel = new JTextArea("Players connected: ");
		connectedPlayerNameLabel.setBounds(57, 70, 140, 22);
		contentPane.add(connectedPlayerNameLabel);
		connectedPlayerNameLabel.setVisible(false);
		
		connectedPlayerNameArea = new JTextArea();
		connectedPlayerNameArea.setBounds(207, 70, 206, 37);
		contentPane.add(connectedPlayerNameArea);
		connectedPlayerNameArea.setVisible(false);
		
		// start button (if pressed game starts)
		JButton btnStart = new JButton("Start!!!");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// parse text of number of players field
				String numberOfPlayersString = numberOfPlayersField.getText();
				// if number of players field blank then show alert
				if(numberOfPlayersString.isEmpty()){
					JOptionPane.showMessageDialog(null,"Please enter a valid number of players");
					return;
				}
				// if the number of plyers inputted is less than 3 then show alert
				int numberOfPlayers = Integer.parseInt(numberOfPlayersString);
				if(numberOfPlayers<3){
					JOptionPane.showMessageDialog(null,"Please enter a valid number(greater than 2) of players");
					return;
				}
				else{
					// if OK then start game by setting some field visible/invisible
					game.setNumberOfPlayers(numberOfPlayers);
					numberOfPlayersField.setVisible(false);
					textArea.setVisible(false);
					btnStart.setVisible(false);
					waitingText.setVisible(true);
					connectedPlayerNameLabel.setVisible(true);
					connectedPlayerNameArea.setVisible(true);
					
				}
			}
		});
		// design of start button
		btnStart.setBounds(154, 129, 89, 23);
		contentPane.add(btnStart);
		
	
		
		
	
	}
}
