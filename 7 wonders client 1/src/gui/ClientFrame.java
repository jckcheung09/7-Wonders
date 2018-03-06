package gui;


import javax.swing.*;
import javax.swing.border.*;

import networking.ClientSocket;


import java.awt.Color;

import java.awt.event.*;

public class ClientFrame extends JFrame {

	// serialization needed for sending via network
	private static final long serialVersionUID = 1001L;
	// panel for loading playerframe
	private JPanel contentPane;
	// player's name  - textfield
	public JTextField nameField;
	
	// conncting message shown when trying to connect server in this textarea
	public JTextArea connectingText;
	// socket needed for connecting server
	public ClientSocket socket;
	public ClientFrame() {
		// initializing frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		//setting background color to cyan
		contentPane.setBackground(Color.CYAN);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// initializing namefield
		nameField = new JTextField();
	
		nameField.setBounds(215, 28, 120, 20);
		contentPane.add(nameField);
		nameField.setVisible(true);
		nameField.setColumns(10);
		
		//initializing name label containing text "Name"
		JTextArea nameLabel = new JTextArea();	
		nameLabel.setEditable(false);
		nameLabel.setText("Name");
		nameLabel.setBounds(137, 25, 52, 22);
		nameLabel.setVisible(true);
		contentPane.add(nameLabel);
		
		// conncting message shown when trying to connect server in this textarea, initializing it

		connectingText = new JTextArea();
		connectingText.setEditable(false);
		connectingText.setBackground(Color.WHITE);
		connectingText.setText("Connecting...........");
		connectingText.setBounds(50, 70, 336, 69);
		connectingText.setVisible(false);
		contentPane.add(connectingText);
		
// this is the button that sends request to join game to server when pressed
		JButton btnNewButton = new JButton("Join Game!!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setVisible(false);
				nameLabel.setVisible(false);
				nameField.setVisible(false);
				connectingText.setVisible(true);
				String name = nameField.getText();
				// if valid request(valid name) then send request to server about joing game through client socket
				if(!name.isEmpty())
					try {
						socket.sendMessage(name, socket.clientSocket);
					} catch (Exception e1) {
							e1.printStackTrace();
					}
				else{
					// if name is null then give alert
					JOptionPane.showMessageDialog(null, "Please enter a name");
				}
			}
		});
		btnNewButton.setBounds(154, 103, 134, 23);
		contentPane.add(btnNewButton);

	}

}
