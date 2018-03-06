
package wonders;

import java.io.Serializable;
import java.util.ArrayList;


public class Player implements Serializable{
	// serialization needed for communicating with server
	private static final long serialVersionUID = 4L;
	// player name
	String name;
	// list of cards played
    ArrayList<Card> cardsPlayed= new ArrayList<Card>();
    // list of cards on hand
    ArrayList<Card> cardsOnHand= new ArrayList<Card>();
   
    // wonder board reference
    WonderBoard wonderBoard;
    // left neighbor
    Player leftNeighbor;
    // right neighbor
    Player rightNeighbor;
    
    //  constructor using fields like  name,
    // minimum number of players, wonder board
    
    Player(String name, WonderBoard board){
    	this.name = name;
    	this.wonderBoard = board;
    
    }
    
    
    
    // getter of name
    
    public String getName() {
		return name;
	}

    // setter of name
	public void setName(String name) {
		this.name = name;
	}

	 // getter of cards played
	public ArrayList<Card> getCardsPlayed() {
		return cardsPlayed;
	}
// setter of cards played
	public void setCardsPlayed(ArrayList<Card> cardsPlayed) {
		this.cardsPlayed = cardsPlayed;
	}
	 // getter of cards on hand
	public ArrayList<Card> getCardsOnHand() {
		return cardsOnHand;
	}

	 // setter of cards on hand
public void setCardsOnHand(ArrayList<Card> cardsOnHand) {
		this.cardsOnHand = cardsOnHand;
	}


// getter of wonder board


	public WonderBoard getWonderBoard() {
		return wonderBoard;
	}
// set wonder board
	public void setWonderBoard(WonderBoard wonderBoard) {
		this.wonderBoard = wonderBoard;
	}

// getter of left neighbor
	public Player getLeftNeighbor() {
		return leftNeighbor;
	}
// setter of left neighbor
	public void setLeftNeighbor(Player leftNeighbor) {
		this.leftNeighbor = leftNeighbor;
	}



	// getter of right neighbor


	public Player getRightNeighbor() {
		return rightNeighbor;
	}





	// setter of right neighbor

	public void setRightNeighbor(Player rightNeighbor) {
		this.rightNeighbor = rightNeighbor;
	}
	
	// overriding to string method for printing the card name
	@Override
	public String toString() {
		return "Player \nName=" + name + "\nCards played = " + cardsPlayed + "\nCards on hand = " + cardsOnHand
				 + "\nWonderBoard = " + wonderBoard ; 
	}
	
 
}
