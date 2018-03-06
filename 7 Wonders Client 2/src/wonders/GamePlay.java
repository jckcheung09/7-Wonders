package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// main game play class
public class GamePlay implements Serializable {
	// serialization needed for communicating with server

	private static final long serialVersionUID = 3L;
	// list of wonder boards
	public ArrayList<WonderBoard> boards = new ArrayList<WonderBoard>();
	// number of players
	public int numberOfPlayers = 0;
	// the cards still left for play
	public ArrayList<Card> cardsToPlay = new ArrayList<Card>();
	// the cards discarded already
	public ArrayList<Card> cardsDiscarded = new ArrayList<Card>();
	// list of ages
	public ArrayList<Age> ages = new ArrayList<Age>();
	// current age
	public int age = 0;

	// initialize game with player names
	public void initialize(ArrayList<String> playerNames) {
		// set number of players with size of number of players
		numberOfPlayers = playerNames.size();
		// initialize wonder boards
		initializeBoards(numberOfPlayers);
		// initialize players
		for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
			// create a new player with a wonder board of board list
			Player player = new Player(playerNames.get(playerIndex), boards.get(playerIndex));
			// give reference of player,player property to board
			boards.get(playerIndex).player = player;
			boards.get(playerIndex).playerProperty = new PlayerProperty();
			// add produced resources of wonder board to resource map of player
			// property
			boards.get(playerIndex).initializeResource();

		}
		// decide neighborhood of players
		resolveNeighbours();
	}

	// initialize board
	private void initializeBoards(int numberOfPlayers) {
		// index numbers [1,2,3,4,5]
		ArrayList<Integer> allIndex = new ArrayList<Integer>();
		for (int i = 1; i <= 5; i++) {
			allIndex.add(i);
		}
		// shuffle indexes
		Collections.shuffle(allIndex);
		// initialize board object with random index and random side ('A' or
		// 'B')
		for (int i = 0; i < numberOfPlayers; i++) {
			int index = allIndex.get(i);
			Random random = new Random();
			// create random side
			int sideDecider = random.nextInt(2);
			char side = 'A';

			if (sideDecider == 1)
				side = 'B';
			// create wonder board with the random attributes
			WonderBoard wonderBoard = new WonderBoard(index, side);
			// add the board to game play and give reference of gameplay to
			// board
			wonderBoard.gamePlay = this;
			boards.add(wonderBoard);

		}

	}

	// decide neighborhood of players
	void resolveNeighbours() {
		// each consecutive players are right-left neighbors with each other in
		// a circular way
		for (int index = 0; index < numberOfPlayers; index++) {
			Player player1 = boards.get(index).player;
			Player player2 = boards.get((index + 1) % numberOfPlayers).player;
			player1.rightNeighbor = player2;
			player2.leftNeighbor = player1;

		}

	}

	// shift cards of each hand after completion of every move
	@SuppressWarnings("unchecked")
	public void ShiftCards(int age) {
		// if age is odd then shift from right to left order
		if (age % 2 == 1) {

			// get cards of hand of first player
			ArrayList<Card> temporaryList = (ArrayList<Card>) boards.get(0).player.cardsOnHand.clone();
			// serially shift cards left direction by one index from player
			// first to last
			for (int playerIndex = 0; playerIndex < numberOfPlayers - 1; playerIndex++) {
				Player player = boards.get(playerIndex).player;
				// right neighbor
				Player nextPlayer = boards.get(playerIndex + 1).player;
				// shift cards to left from right
				player.setCardsOnHand(nextPlayer.getCardsOnHand());

			}
			// the last player gets the cards of first player as it is like a
			// circle
			boards.get(numberOfPlayers - 1).player.cardsOnHand = temporaryList;

		} // if age is even then shift from left to right order
		else {
			// get cards of last player
			ArrayList<Card> temporaryList = (ArrayList<Card>) boards.get(numberOfPlayers - 1).player.cardsOnHand
					.clone();

			// serially shift cards right direction by one index from player
			// last to first
			for (int playerIndex = numberOfPlayers - 1; playerIndex > 0; playerIndex--) {
				Player player = boards.get(playerIndex).player;
				// left neighbor
				Player nextPlayer = boards.get(playerIndex - 1).player;
				// shift cards to right
				player.cardsOnHand = nextPlayer.cardsOnHand;

			}
			// the first player gets the cards of last player as it is like a
			// circle 
			boards.get(0).player.cardsOnHand = temporaryList;

		}
	}
// get number of players
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
// set number of players
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
// get details text of gameplay
	@Override
	public String toString() {
		return "GamePlay [ " + " boards=" + boards + ", numberOfPlayers=" + numberOfPlayers + ", cardsToPlay="
				+ cardsToPlay + ", cardsDiscarded=" + cardsDiscarded + ", ages=" + ages + "]";
	}
// count military win between neighborhoods
	public void resolveConflicts(int age) {
		// military point gain for age 1 is 1, age 2 is 3, and age 3 is 5
		int agePoint = 0;
		if (age == 1)
			agePoint = 1;
		else if (age == 2)
			agePoint = 3;
		else
			agePoint = 5;
		// for each player
		for (int index = 0; index < numberOfPlayers; index++) {
			// get own military point
			Player player = boards.get(index).player;
			int ownMilitaryPoint = boards.get(index).playerProperty.militaryPoint;
			// get military points of left neighbor
			int leftMilitaryPoint = boards
					.get((index - 1 + numberOfPlayers) % numberOfPlayers).playerProperty.militaryPoint;
			// get military points of right neighbor				
			int rightMilitaryPoint = boards.get((index + 1) % numberOfPlayers).playerProperty.militaryPoint;
			// if own military point is greater than left then add age point(+1, +3 or +5) to own
			if (ownMilitaryPoint > leftMilitaryPoint) {

									player.wonderBoard.playerProperty.conflictTokens.add(1);
				player.wonderBoard.playerProperty.victoryPoint += agePoint;
				player.wonderBoard.playerProperty.victoryPointByMilitaryConflict += agePoint;
			} else if (ownMilitaryPoint < leftMilitaryPoint) {
				// if own military point is less than left then add age point(-1) to own
				
				player.wonderBoard.playerProperty.conflictTokens.add(new Integer(-1));
				player.wonderBoard.playerProperty.victoryPoint--;
				player.wonderBoard.playerProperty.victoryPointByMilitaryConflict--;
			}

			if (ownMilitaryPoint > rightMilitaryPoint) {
				// if own military point is greater than right then add age point(+1, +3 or +5) to own
				
				player.wonderBoard.playerProperty.conflictTokens.add(1);
				player.wonderBoard.playerProperty.victoryPoint += agePoint;
				player.wonderBoard.playerProperty.victoryPointByMilitaryConflict += agePoint;
			} else if (ownMilitaryPoint < rightMilitaryPoint) {
			
				// if own military point is less than right then add age point(-1) to own
				player.wonderBoard.playerProperty.conflictTokens.add(new Integer(-1));
				player.wonderBoard.playerProperty.victoryPoint--;
				player.wonderBoard.playerProperty.victoryPointByMilitaryConflict--;
			}
	
		}

	}

}
