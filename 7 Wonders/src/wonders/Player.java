
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Player implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	String name;
    ArrayList<Card> cardsPlayed= new ArrayList<Card>();
    ArrayList<Card> cardsOnHand= new ArrayList<Card>();
    ArrayList<Integer> conflictTokens= new ArrayList<Integer>();;   
    WonderBoard wonderBoard;
    Player leftNeighbor;
    Player rightNeighbor;
    
    
    Player(String name, WonderBoard board){
    	this.name = name;
    	this.wonderBoard = board;
    	board.player=this;
    
    }
    
    
    
    
    
    public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public ArrayList<Card> getCardsPlayed() {
		return cardsPlayed;
	}





	public void setCardsPlayed(ArrayList<Card> cardsPlayed) {
		this.cardsPlayed = cardsPlayed;
	}





	public ArrayList<Card> getCardsOnHand() {
		return cardsOnHand;
	}





	public void setCardsOnHand(ArrayList<Card> cardsOnHand) {
		this.cardsOnHand = cardsOnHand;
	}





	public ArrayList<Integer> getConflictTokens() {
		return conflictTokens;
	}





	public void setConflictTokens(ArrayList<Integer> conflictTokens) {
		this.conflictTokens = conflictTokens;
	}





	public WonderBoard getWonderBoard() {
		return wonderBoard;
	}





	public void setWonderBoard(WonderBoard wonderBoard) {
		this.wonderBoard = wonderBoard;
	}





	public Player getLeftNeighbor() {
		return leftNeighbor;
	}





	public void setLeftNeighbor(Player leftNeighbor) {
		this.leftNeighbor = leftNeighbor;
	}





	public Player getRightNeighbor() {
		return rightNeighbor;
	}





	public void setRightNeighbor(Player rightNeighbor) {
		this.rightNeighbor = rightNeighbor;
	}





	
   
  
 
  
	
    @Override
	public String toString() {
		return "Player \nName=" + name + "\nCards played = " + cardsPlayed + "\nCards on hand = " + cardsOnHand
				+ "\nConflict tokens = " + conflictTokens + "\nWonderBoard = " + wonderBoard ; 
	}
	public void useFreeCard(Card card){
    
    }
    

    public void useSpecialFacilityOfBoard(){
  	  
  	  
    }
}
