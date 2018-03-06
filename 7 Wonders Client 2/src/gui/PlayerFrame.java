package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import networking.ClientSocket;
import wonders.*;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;

import javax.swing.*;
import java.awt.Font;

public class PlayerFrame extends JFrame {
	// this UID provides serialization needed for sending object via network
	private static final long serialVersionUID = 1L;
	// content pane of frame
	private JPanel contentPane;
	// text field containing player's name
	private JTextField playerNameField;
	// text field containing wonder board's name
	private JTextField wonderBoardNameField;
	// text field containing number of stages built
	private JTextField stagesBuiltField;
	// text field containing number of age the game currently running on
	private JTextField ageField;
	// Gameplay object for running the game
	public GamePlay gameplay;
	// Wonderboard object for this player
	WonderBoard wonderBoard;
	// combo box containing the cards player played yet in this game
	private JComboBox<Card> playedCardBox;
	// combo box containing the cards player has in his hand
	private JComboBox<Card> cardsOnHandBox;
	// scrollpane to contain details of the card selected by selection box of
	// played card list
	private JScrollPane selectedCardScrollPane;
	// text area to contain details of the card selected by selection box of
	// played card list

	private JTextArea playedCardDetailsTextArea;
	// scrollpane to contain details of the card selected by selection box of
	// cards on hand list

	private JScrollPane scrollPane;
	// text area to contain details of the card selected by selection box of
	// cards on hand list

	private JTextArea selectedCardDetailsTextArea;
	// button for playing cards
	private JButton playCardButton;
	// button for discarding cards
	private JButton discardButton;
	// button for building stage
	private JButton buildStageButton;
	// button for playing card free if free_card play is allowed by stage
	// building(wonder)
	private JButton playFreeButton;
	// scrollpane for containing trade list
	private JScrollPane tradeListScrollPane;
	// nothing just getting rid of unnecessary warnings
	@SuppressWarnings({ "rawtypes" })
	// selection box for selecting trade resource
	private JComboBox selectTradeResourceNameBox;
	// list of trades possible
	private JList<Trade> tradedList;
	// button for removing any trades if user wishes
	private JButton removeTradeItemButton;
	// nothing just getting rid of unnecessary warnings

	@SuppressWarnings("rawtypes")
	// selection box for selecting resource quantity of trade
	private JComboBox tradeQuantityBox;
	// trade list selected by user
	private DefaultListModel<Trade> tradeListModel;
	// client socket used for communicating with server
	public ClientSocket clientSocket;
	// text field indicating coins of the player
	private JTextField coinField;
	// text field indicating military points of the player
	private JTextField militaryPointField;
	// text field indicating victory points of the player
	private JTextField victoryPointField;
	int militaryPoint = 0;

	// box indicating resources available for the player
	private JComboBox<Resource> totalResourceAvailableBox;
	// field indicating conflict tokens obtained by the player
	private JTextField conflictTokenField;
	// text field indicating requirements of building next stage
	private JTextArea nextStageRequirementArea;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// constructor of the frame
	public PlayerFrame(WonderBoard wonder_Board, GamePlay game, int identityIndex) {
		// setting wonder board, game play and identity index of the player
		this.wonderBoard = wonder_Board;
		wonderBoard.identityIndex = identityIndex;
		gameplay = game;
		// initially the frame is invisible
		this.setVisible(false);
		// setting up design parameters of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 628);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// initializing the box design indicating cards played yet by the player
		playedCardBox = new JComboBox<Card>();

		playedCardBox.setBounds(152, 109, 260, 32);
		contentPane.add(playedCardBox);

		cardsOnHandBox = new JComboBox<Card>();

		cardsOnHandBox.setBounds(156, 268, 260, 32);
		contentPane.add(cardsOnHandBox);

		// initializing the design of the button "play card"
		playCardButton = new JButton("Play Card");

		playCardButton.setBounds(121, 335, 104, 23);
		contentPane.add(playCardButton);

		// initializing the design of the field "player name"

		playerNameField = new JTextField("Player Name: ");
		playerNameField.setEditable(false);

		playerNameField.setBounds(30, 11, 131, 20);
		contentPane.add(playerNameField);
		playerNameField.setColumns(10);

		// initializing the design of the field "wonderboard: "

		wonderBoardNameField = new JTextField();
		wonderBoardNameField.setText("Wonder Board: ");
		wonderBoardNameField.setEditable(false);
		wonderBoardNameField.setColumns(10);
		wonderBoardNameField.setBounds(30, 42, 250, 20);
		contentPane.add(wonderBoardNameField);

		// initializing the design of the field "stages built: "

		stagesBuiltField = new JTextField();
		stagesBuiltField.setText("Stages Built: ");
		stagesBuiltField.setEditable(false);
		stagesBuiltField.setColumns(10);
		stagesBuiltField.setBounds(30, 73, 126, 20);
		contentPane.add(stagesBuiltField);

		// initializing the design of the field "cards on hand: "

		JLabel lblCardsOnHand = new JLabel("Cards on Hand: ");
		lblCardsOnHand.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCardsOnHand.setBounds(47, 251, 114, 65);
		contentPane.add(lblCardsOnHand);

		// initializing the design of the field "cards played: "

		JLabel label = new JLabel("Cards Played: ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(47, 92, 114, 65);
		contentPane.add(label);

		// initializing the design of the text area containing details of
		// the selected card of played card list

		playedCardDetailsTextArea = new JTextArea("");
		playedCardDetailsTextArea.setEditable(false);
		scrollPane = new JScrollPane(playedCardDetailsTextArea);
		scrollPane.setBounds(458, 98, 370, 120);
		contentPane.add(scrollPane);
		
		
		// initializing the design of the text area containing details of
		// the selected card of cards on hand list
		selectedCardDetailsTextArea = new JTextArea("");
		selectedCardDetailsTextArea.setEditable(false);
		selectedCardScrollPane = new JScrollPane(selectedCardDetailsTextArea);
		selectedCardScrollPane.setBounds(458, 238, 370, 120);
		contentPane.add(selectedCardScrollPane);

		// initializing the design of the text field "age: "
		ageField = new JTextField();
		ageField.setText("Age: ");
		ageField.setEditable(false);
		ageField.setColumns(10);
		ageField.setBounds(183, 73, 96, 20);
		contentPane.add(ageField);
		
		// initializing the design of the button "Discard: "
		discardButton = new JButton("Discard");

		discardButton.setBounds(235, 335, 89, 23);
		contentPane.add(discardButton);
		
		
		// initializing the design of the button "Build Stage: "
		
		buildStageButton = new JButton("Build Stage");

		buildStageButton.setBounds(333, 335, 104, 23);
		contentPane.add(buildStageButton);
		
		// initializing the design of the button "Play as Free Card "
		
		playFreeButton = new JButton("Play as Free Card");
		playFreeButton.setBounds(221, 387, 124, 23);
		playFreeButton.setForeground(Color.MAGENTA);
		playFreeButton.setBackground(Color.YELLOW);
		playFreeButton.setOpaque(true);
		contentPane.add(playFreeButton);
		
		// initializing the design of the label "Make trade "

		JLabel makeTradeLabel = new JLabel("Make Trade: ");
		makeTradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		makeTradeLabel.setBounds(47, 417, 114, 65);
		contentPane.add(makeTradeLabel);
		
		// initializing the design of the scrollpane containing trade list

		tradeListModel = new DefaultListModel<Trade>();
		tradedList = new JList<Trade>(tradeListModel);
		tradeListScrollPane = new JScrollPane(tradedList);
		tradeListScrollPane.setBounds(488, 408, 340, 120);
		contentPane.add(tradeListScrollPane);
		
		// initializing the design of the box for selecting resource name
		
		JLabel label_2 = new JLabel("Select Resource: ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(139, 417, 114, 65);
		contentPane.add(label_2);
		
		// initializing the design of the box for selecting resource quantity
		// default values [1,2,3,4]
		JLabel label_3 = new JLabel("Select Quantity: ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(139, 448, 114, 65);
		contentPane.add(label_3);
		Integer[] quantityList = { 1, 2, 3, 4 };
		tradeQuantityBox = new JComboBox(quantityList);
		tradeQuantityBox.setBounds(240, 471, 126, 20);
		contentPane.add(tradeQuantityBox);
		
		
		// initializing the design of the box for selecting neighbor
		// default values ["left","right"]
	
		JLabel label_4 = new JLabel("Select Neighbor: ");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_4.setBounds(139, 481, 114, 65);
		contentPane.add(label_4);
		String[] neighbor = { "left", "right" };
		JComboBox<String> selectNeighborForTradeBox = new JComboBox(neighbor);
		selectNeighborForTradeBox.setBounds(240, 502, 126, 20);
		contentPane.add(selectNeighborForTradeBox);

		// initializing the design of the trade button
		JButton tradeButton = new JButton("Trade");
		tradeButton.setBounds(389, 470, 89, 23);
		contentPane.add(tradeButton);
		
		// text area for containing trade list via scroll pane
		JTextArea tradedTextArea = new JTextArea("");
		JScrollPane tradedResourcePane = new JScrollPane(tradedTextArea);
		tradedTextArea.setBounds(732, 417, -183, 97);
		contentPane.add(tradedResourcePane);
// default resource names for trading  { "wood", "ore", "stone", "clay", "glass", "loom", "papyrus" }
		
		String[] resourceNames = { "wood", "ore", "stone", "clay", "glass", "loom", "papyrus" };
		selectTradeResourceNameBox = new JComboBox(resourceNames);
		selectTradeResourceNameBox.setBounds(240, 440, 126, 20);
		contentPane.add(selectTradeResourceNameBox);
		
// initializing design of the button for removing any trade from trade list
		removeTradeItemButton = new JButton("Remove Item");
		removeTradeItemButton.setBounds(599, 539, 134, 23);
		removeTradeItemButton.setForeground(Color.RED);
		contentPane.add(removeTradeItemButton);

	//  initializing design of the label "resources available"
		
		JLabel label_1 = new JLabel("Resources Available:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(443, 42, 134, 65);
		contentPane.add(label_1);
		
//   initializing design of the selection box for available resources
		
		totalResourceAvailableBox = new JComboBox<Resource>();

		totalResourceAvailableBox.setBounds(568, 59, 260, 32);
		contentPane.add(totalResourceAvailableBox);

		
		// initializing coin field  with default value  3
		coinField = new JTextField();

		coinField.setText("Coins: 3");
		coinField.setEditable(false);
		coinField.setColumns(10);
		coinField.setBounds(289, 73, 126, 20);
		contentPane.add(coinField);
		
		// initializing military point field  with default value 0
		
		militaryPointField = new JTextField();

		militaryPointField.setText("Military Points: 0");
		militaryPointField.setEditable(false);
		militaryPointField.setColumns(10);
		militaryPointField.setBounds(289, 11, 126, 20);
		contentPane.add(militaryPointField);

		// initializing victory point field  with default value 0
		
		victoryPointField = new JTextField();
		victoryPointField.setText("Victory Points: 0");
		victoryPointField.setEditable(false);
		victoryPointField.setColumns(10);
		victoryPointField.setBounds(290, 42, 126, 20);
		contentPane.add(victoryPointField);

		// initializing conflict token field  with default empty list
		
		conflictTokenField = new JTextField();
		conflictTokenField.setText("Conflict Tokens: ");
		conflictTokenField.setEditable(false);
		conflictTokenField.setColumns(10);
		conflictTokenField.setBounds(443, 11, 189, 20);
		contentPane.add(conflictTokenField);

		// initializing design of the field "Next Stage Requirements "
		
		JLabel nextStageLabel = new JLabel("Next Stage Requirements ");
		nextStageLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nextStageLabel.setBounds(47, 152, 149, 87);
		contentPane.add(nextStageLabel);
		
		// initializing text area containing next stage requirements
		nextStageRequirementArea = new JTextArea();
		nextStageRequirementArea.setBounds(206, 152, 212, 105);
		contentPane.add(nextStageRequirementArea);

		// initializing game play with parameters of constructor
		initialize(game, identityIndex);
		
		// if any card is selected  on played card list then text area beside it holds automatically the details of this 
		// card through this action listener
		playedCardBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card card = (Card) playedCardBox.getSelectedItem();
				playedCardDetailsTextArea.setText(card.printDetails());
			}
		});
		
		// if any card is selected on  cards on hand list then text area beside it holds automatically the details of this 
		// card through this action listener
		cardsOnHandBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card card = (Card) cardsOnHandBox.getSelectedItem();
				if (card != null)
					selectedCardDetailsTextArea.setText(card.printDetails());

			}
		});
		// the main action listener containing the action of playing a card
		playCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get selected card from cards on hand box
				Card card = (Card) cardsOnHandBox.getSelectedItem();
				// get trade list 
				ArrayList<Trade> tradeListToBeSent = new ArrayList<Trade>();
				Enumeration<Trade> elements = (Enumeration<Trade>) tradeListModel.elements();
				while (elements.hasMoreElements()) {
					tradeListToBeSent.add(elements.nextElement());
				}
			
				// if card is free to play by requirements(like no requirement or required structures are built) 
				// or player can pay the cost of the card and also can pay required resources then allow  him to play the card
				if (card.iSFree(wonderBoard)
						|| (card.canPayCost(wonderBoard) && card.canPayResource(wonderBoard, tradeListToBeSent))) {
					card.playCard(wonderBoard);
					// remove the card from cards on hand list
					cardsOnHandBox.removeItem(card);
					// add the card to played cards list
					playedCardBox.addItem(card);
					// add the card name to played card name list
					wonderBoard.playerProperty.playedCardList.add(card.name);
					// remove the card from cards on hand box
					wonderBoard.player.getCardsOnHand().remove(card);
					// if seventh card then discard it automatically
					checkSeventhCard(wonderBoard);
					try {
					// send updated game play object to server via client socket
						clientSocket.sendObject(wonderBoard, clientSocket.clientSocket);
					// update fields of the frame after playing this card
						updateFields();
					// after other players have completed their move get updated gameplay object from server
						gameplay = (GamePlay) clientSocket.getObject(clientSocket.clientSocket);
					// update wonder board from game play object sent by server
						wonderBoard = gameplay.boards.get(identityIndex);
					// refresh card list of cards on hand box from game play object
						refreshCards();

						

					} catch (Exception e1) {

						e1.printStackTrace();
					}

				} else {
					// if unable to play the card show alert
					alert("Please fulfil requirements of playing this card");
				}
			}
		});
		// discard action of a card if discard button is pressed
		discardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get selected card from cards on hand box
				Card card = (Card) cardsOnHandBox.getSelectedItem();
				// discard the card (add 3 coins to playerproperty)
				card.discard(wonderBoard);
				// remove the card from cards on hand box
				cardsOnHandBox.removeItem(card);
				// remove the card from cards on hand list of player
				wonderBoard.player.getCardsOnHand().remove(card);
				// if it is sixth card then forcibly discard the seventh card
				checkSeventhCard(wonderBoard);
				try {
				// send the updated wonderboard to server
					clientSocket.sendObject(wonderBoard, clientSocket.clientSocket);
				// update various fields of the frame after discarding card
					updateFields();
				// after other players have completed their move, get the 
				// updated gameplay object from server through socket connection
					gameplay = (GamePlay) clientSocket.getObject(clientSocket.clientSocket);
				// update wonderboard according to the new game play object of server
					wonderBoard = gameplay.boards.get(identityIndex);
				// refresh cards on hand field and cards on hand list of wonderboard 
					refreshCards();
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		// if playing a card completely free is allowed as facility 
		// of building stage of wonderboard then play it if this button is pressed
		playFreeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// get selected cvard from cards on hand box
				Card card = (Card) cardsOnHandBox.getSelectedItem();
				// if free card use is allowed then play it
				if (wonderBoard.playerProperty.freeCardUseAllowed) {
					// play the card selected
					card.playCard(wonderBoard);
					// as free card play is allowed once disable this facility afterwards
					wonderBoard.getPlayerProperty().freeCardUseAllowed = false;
					// remove the card from wonderboard cards on
					// hand list and cards on hand box
					cardsOnHandBox.removeItem(card);
					// add the card to cards played list of wonderboard and played card box of frame
					playedCardBox.addItem(card);
					wonderBoard.player.getCardsPlayed().add(card);
					// add the name of the card to played card name list
					wonderBoard.playerProperty.playedCardList.add(card.name);
					wonderBoard.player.getCardsOnHand().remove(card);
					// if it is sixth card then forcibly discard the seventh card
					
					checkSeventhCard(wonderBoard);

					try {
						// send the updated wonderboard to server
						clientSocket.sendObject(wonderBoard, clientSocket.clientSocket);
					// update various fields of the frame after discarding card
						updateFields();
					// after other players have completed their move, get the 
					// updated gameplay object from server through socket connection
						gameplay = (GamePlay) clientSocket.getObject(clientSocket.clientSocket);
					// update wonderboard according to the new game play object of server
						wonderBoard = gameplay.boards.get(identityIndex);
					// refresh cards on hand field and cards on hand list of wonderboard 
						refreshCards();

					} catch (Exception e1) {

						e1.printStackTrace();
					}

				} else {
					// if free card use is not allowed then show alert
					alert("Sorry! you don't have facilities to play this as a free card");
				}
			}
		});
		// if trade button is pressed then add to trade list if requirements are met
		tradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get resource name from resource box 
				String resourceName = (String) selectTradeResourceNameBox.getSelectedItem();
				// get resource quantity from resource quantity box
				int resourceQuantity = (int) tradeQuantityBox.getSelectedItem();
				// get neighbor indicator string from select neighbor box
				String neighbor = (String) selectNeighborForTradeBox.getSelectedItem();
				// create a trade object from selected resource and neighbour name
				Trade tradeToBeDone = new Trade(new Resource(resourceName, resourceQuantity), neighbor);
				// if trade possible then add the trade to trade list model
				if (tradeToBeDone.isTradePossible(wonderBoard, gameplay)) {
					tradeListModel.addElement(tradeToBeDone);
				}

			}
		});
		// if remove trade button is pressed then remove the trade from trade list model
		removeTradeItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// remove trade object from trade list model
				Trade tradeToBeRemoved = tradedList.getSelectedValue();
				tradeListModel.removeElement(tradeToBeRemoved);

			}
		});
// if this button is pressed then building of next stage 
		// of wonderboard is done if everything is ok
		buildStageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get selected card from cards on hand box
				Card card = (Card) cardsOnHandBox.getSelectedItem();
				// Extract trade list from trade model created by player
				Enumeration<Trade> elements = (Enumeration<Trade>) tradeListModel.elements();
				ArrayList<Trade> tradeListToBeSent = new ArrayList<>();
			
				while (elements.hasMoreElements()) {
					tradeListToBeSent.add(elements.nextElement());
				}
	// if requirements of stage building is done build next stage
				if (wonderBoard.nextStagePossible(tradeListToBeSent)) {
				// build next stage of wonder board
					wonderBoard.buildNextStage(card);
				// remove the card from cards on hand box
					cardsOnHandBox.removeItem(card);
				// remove the card from cards on hand list
					wonderBoard.player.getCardsOnHand().remove(card);
					// if it is sixth card then forcibly discard the seventh card
					
					checkSeventhCard(wonderBoard);

					try {
						// send the updated wonderboard to server
						clientSocket.sendObject(wonderBoard, clientSocket.clientSocket);
					// update various fields of the frame after discarding card
						updateFields();
					// after other players have completed their move, get the 
					// updated gameplay object from server through socket connection
						gameplay = (GamePlay) clientSocket.getObject(clientSocket.clientSocket);
					// update wonderboard according to the new game play object of server
						wonderBoard = gameplay.boards.get(identityIndex);
					// refresh cards on hand field and cards on hand list of wonderboard 
						refreshCards();
						} catch (Exception e1) {

						e1.printStackTrace();
					}

				} else{
					// if requirements not met show alert message
					alert("Please fulfil requirements of building next stage first");
				}
					
			}
		});

	}
// if seventh cards is left alone in the cards on hand box then it is discarded by default
	public void checkSeventhCard(WonderBoard wonderBoard) {
// checking if it is seventh card left alone in the cards on hand box
		if (cardsOnHandBox.getItemCount() == 1) {
			// get the seventh card from card on hand box
			Card lastCard = (Card) cardsOnHandBox.getSelectedItem();
			// if playing of seventh card is allowed by the wonder board then play that seventh card automatically
			if (wonderBoard.playerProperty.seventhCardUseAllowed) {
				// if requirements are met then play the card
				if (lastCard.canPayCost(wonderBoard) && lastCard.canPayResource(wonderBoard, new ArrayList<Trade>()))
					lastCard.playCard(wonderBoard);
				else {
					// else discard the card
					lastCard.discard(wonderBoard);
				}

			} else {
				lastCard.discard(wonderBoard);
			}
			// clear the  cards on hand list
			wonderBoard.player.getCardsOnHand().clear();
		}

	}
// initialize the frame with game play object
	public void initialize(GamePlay gamePlayToInitialize, int index) {
	// initialize game play object from server
		gameplay = gamePlayToInitialize;
		// initialize player object
		Player player = gameplay.boards.get(index).player;
		// set name field by the player name
		String existingNameFieldText = playerNameField.getText();
		playerNameField.setText(existingNameFieldText + " " + player.getName());
		// set wonder board name field from game play object
		wonderBoardNameField.setText(wonderBoardNameField.getText() + " " + wonderBoard.getName());
		stagesBuiltField.setText(stagesBuiltField.getText() + " " + wonderBoard.getStagesBuilt());
		// set age field text  with default age: 1
		ageField.setText(ageField.getText() + " 1");
		// initialize cards on hand box from player object
		// populate with cards on hand of the player
		for (Card card : player.getCardsOnHand()) {
			cardsOnHandBox.addItem(card);
		}
		// default selected card is first one of the list, the details of the card 
		// is shown in the text area beside it
		Card card = (Card) cardsOnHandBox.getSelectedItem();
		selectedCardDetailsTextArea.setText(card.printDetails());
		// initialize available resources field with resources produced by wonder board
		for (String key : wonderBoard.playerProperty.mapOfResourceToName.keySet()) {
			totalResourceAvailableBox.addItem(wonderBoard.playerProperty.mapOfResourceToName.get(key));

		}
		// 
		nextStageRequirementArea.setText(wonderBoard.getStages().get(0).requirementsText());

	}
	// show alert messages on JoptionPane
	public void alert(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
// update fields of the player frame by wonder board properties
	public void updateFields() {
		// update coin field
		coinField.setText("Coins:  " + wonderBoard.playerProperty.coins);
	// update military point field
		militaryPointField.setText("Military Points: " + wonderBoard.playerProperty.militaryPoint);
	// update victory point field	
		victoryPointField.setText("Victory Points: " + wonderBoard.playerProperty.victoryPoint);
	// update stages built field
		stagesBuiltField.setText("Stages Built: " + wonderBoard.getStagesBuilt());
	// update total resources available field
		totalResourceAvailableBox.removeAllItems();

		for (String key : wonderBoard.playerProperty.mapOfResourceToName.keySet()) {
			totalResourceAvailableBox.addItem(wonderBoard.playerProperty.mapOfResourceToName.get(key));

		}
	//  update next stage requirements text area
		if (wonderBoard.getStagesBuilt() != 3) {
			nextStageRequirementArea
					.setText(wonderBoard.getStages().get(wonderBoard.getStagesBuilt()).requirementsText());

		} 
		else {
		// if all stages built then display it in the text field
			nextStageRequirementArea.setText("All stages built successfully!!");
		}
		// clear trade list
		tradedList.removeAll();
		tradeListModel.clear();

	}

	public void refreshCards() {
		// clear cards on hand box 
		cardsOnHandBox.removeAllItems();
		// update coin field
		coinField.setText("Coins:  " + wonderBoard.playerProperty.coins);
		// clear buffered trade list of wonderboard as coin transfers are done now
		wonderBoard.bufferedTradeList.clear();
		// populate cards on hand box from updated wonder board object
		for (Card card : wonderBoard.player.getCardsOnHand()) {
			cardsOnHandBox.addItem(card);

		}
		// update age field
		ageField.setText("Age: " + gameplay.age);
		// update conflict token field
		if (wonderBoard.playerProperty.conflictTokens.size() > 0)
			conflictTokenField.setText("Conflict Tokens: " + wonderBoard.playerProperty.conflictTokens.toString());
// if game is finished then activate the result frame to show final results
		if (cardsOnHandBox.getItemCount() == 0) {
			ResultTable frame = new ResultTable(gameplay);
			frame.f.setVisible(true);
		}
	}
}
