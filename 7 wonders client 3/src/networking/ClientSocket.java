package networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import gui.ClientFrame;
import gui.PlayerFrame;
import wonders.*;

public class ClientSocket {
	// socket that communicates with server
	public Socket clientSocket;
	// output stream to receive objects/messages from server
	ObjectOutputStream objectOutputStream;
	// player frame reference
	PlayerFrame playerFrame ;
	// initializing client frame
	ClientFrame initialFrame = new ClientFrame();
	// identity index of the player
	int identityIndex=0;
	// main game play method
	public void play() throws Exception{
		// set initial frame visible
		initialFrame.setVisible(true);
		// send reference of client socket to initial frame
		initialFrame.socket= this;
		
	// initialize client socket with ip address of the server
		clientSocket = new Socket("127.0.0.1", 1025);
		// get message from server
		String message = getMessage(clientSocket);
	// update initial frame's text with that message
		initialFrame.connectingText.setText(message);
	// get identity index from server	
		identityIndex = Integer.parseInt(getMessage(clientSocket));
	// get game play object from server	
		GamePlay game = (GamePlay) getObject(clientSocket);
	
	// initialize player frame
		playerFrame = new PlayerFrame(game.boards.get(identityIndex), game, identityIndex);
		playerFrame.clientSocket=this;
	// turn off initial frame
		initialFrame.setVisible(false);
	// turn on player frame
		playerFrame.setVisible(true);
		
		
	}
	// send object to server via object output stream
	public void sendObject(Object object,Socket clientSocket) throws Exception{
		objectOutputStream=new ObjectOutputStream(clientSocket.getOutputStream());
		
		objectOutputStream.writeObject(object);
	
		objectOutputStream.flush();
	}
	// send message to server via object output stream
	public void sendMessage(String message,Socket clientSocket) throws Exception{
		objectOutputStream=new ObjectOutputStream(clientSocket.getOutputStream());
		objectOutputStream.writeUTF(message);
		objectOutputStream.flush();
	
	}
	// get object from server via object input stream
	public Object getObject(Socket socket) throws Exception{
		
		Object object ;
		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		// wait until object is received from server
		while((object=  objectInputStream.readObject())==null){
			
		}
		return object;
	}
	
	// get message from server via object input stream
	public String getMessage(Socket socket) throws Exception{
		
		String message;
		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		// wait until message is received from server
		while(( message=  objectInputStream.readUTF())==null){
			
		}

		return message;
	}
	// main method of running this program
	public static void main(String args[]) throws Exception{
		new ClientSocket().play();
	}
}
