package networking;

import java.util.*;

import wonders.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import gui.*;

// server
public class Server implements Runnable {
// server socket to connect with clients
	ServerSocket serverSocket;
	// object output stream to send messages/objects
	ObjectOutputStream objectOutputStream;
	// object input stream to get messages/objects
	ObjectInputStream objectInputStream;
	// socket list connected to server
	List<Socket> registeredUserSocketList = new ArrayList<Socket>();
	// game play reference
	GamePlay game = new GamePlay();
	// server frame reference
	ServerFrame frame = new ServerFrame(game);
	// list of player names
	ArrayList<String> playerNames = new ArrayList<String>();

	public Server() {
		try {
			// run server socket with arbitrary port
			serverSocket = new ServerSocket(1025);
			// run server
			run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// set frame visible
		frame.setVisible(true);

	

		try {
			// default number of players is 3
			int numberOfPlayers = 3;
			// list of sockets
			ArrayList<Socket> socketList = new ArrayList<Socket>();
			// for each index of socket
			for (int index = 0; index < numberOfPlayers; index++) {
				// connect socket(client) to server
				Socket socket = serverSocket.accept();
				System.out.println("socket connected");
				// get player name from socket
				String playerName = getMessage(socket);
				// add player name
				playerNames.add(playerName);
				// update connected player names text area of frame
				String previousList = frame.connectedPlayerNameArea.getText() + " ";
				frame.connectedPlayerNameArea.setText(previousList + playerName);
				// add socket to socket list
				socketList.add(socket);
				// update number of players
				numberOfPlayers = game.getNumberOfPlayers();
				// send success message to socket
				sendMessage("Connected Successfully, " + "Waiting for other players to connect", socket);
			}
			// initialize game play with player names
			game.initialize(playerNames);
			// for each age
			for (int ageIndex = 1; ageIndex < 4; ageIndex++) {
				// create age object
				Age age = new Age(ageIndex);
				// discard irrelevant cards (which needs more players than number of players of this game)
				age.discardIrrelevantCards(numberOfPlayers);
				// distribute cards to players
				age.sampleCards(numberOfPlayers, game);
				// update age value of game
				game.age = ageIndex;
				 // for each player
					for (int index = 0; index < numberOfPlayers; index++) {
						// if first age then send identity index to players
						if (ageIndex == 1)sendMessage("" + index, socketList.get(index));
						// send game play object to players
						sendObject(game, socketList.get(index));
					}
					// set server frame invisible now
					frame.setVisible(false);
				
					// for each of 6 moves
				for (int moveIndex = 0; moveIndex < 6; moveIndex++) {
					// for each player
					for (int clientIndex = 0; clientIndex < numberOfPlayers; clientIndex++) {
					// get wonder board from player
						WonderBoard board = (WonderBoard) getObject(socketList.get(clientIndex));
					// update wonder board of game play object
						game.boards.set(board.identityIndex, board);
						}
					// commit buffered trades as moves completed by every player
					game.commitTrades();
					// shift cards clockwise or anti-clockwise depending on age index
					game.ShiftCards(ageIndex);
					// for each player
					for (int clientIndex = 0; clientIndex < numberOfPlayers; clientIndex++) {
					// if move number is less than 6 then send updated game play object to each player
						if(moveIndex<5)sendObject(game, socketList.get(clientIndex));

					}
				}
				// resolve military conflicts as age finished
				game.resolveConflicts(ageIndex);
				
				
			}
			// send game play object to every player (game finished now)
			for(int index =0 ; index<game.numberOfPlayers;index++){
				sendObject(game, socketList.get(index));
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
// send object to client via object output stream
	private void sendObject(Object object, Socket clientSocket) throws Exception {
		objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();

	}
//  send message to client via object output stream
	private void sendMessage(String message, Socket clientSocket) throws Exception {
		objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
		objectOutputStream.writeUTF(message);
		objectOutputStream.flush();

	}
// get object from client via object input stream
	private Object getObject(Socket socket) throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		Object object;
		// wait until client sends object
		while ((object = objectInputStream.readObject()) == null) {

		}

		return object;
	}

	// get message from client via object input stream
	private String getMessage(Socket socket) throws Exception {
		String message;
		objectInputStream = new ObjectInputStream(socket.getInputStream());
		// wait until client sends message
		while ((message = objectInputStream.readUTF()) == null) {

		}
		return message;
	}
// main method of server
	public static void main(String agrgs[]) {
	// run server
		new Server();

	}
}
