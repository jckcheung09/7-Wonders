package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GamePlay implements Serializable {

	private static final long serialVersionUID = 3L;

	public ArrayList<WonderBoard> boards = new ArrayList<WonderBoard>();
	public int numberOfPlayers = 0;

	public ArrayList<Card> cardsToPlay = new ArrayList<Card>();
	public ArrayList<Card> cardsDiscarded = new ArrayList<Card>();
	public ArrayList<Age> ages = new ArrayList<Age>();
	public int age = 0;

	public void initialize(ArrayList<String> playerNames) {

		numberOfPlayers = playerNames.size();
		initializeBoards(numberOfPlayers);
		for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {

			Player player = new Player(playerNames.get(playerIndex), boards.get(playerIndex));

			boards.get(playerIndex).player = player;
			boards.get(playerIndex).playerProperty = new PlayerProperty();
			boards.get(playerIndex).initializeResource();

		}

		resolveNeighbours();
	}

	private void initializeBoards(int numberOfPlayers) {
		ArrayList<Integer> allIndex = new ArrayList<Integer>();
		for (int i = 1; i <= 5; i++) {
			allIndex.add(i);
		}
		Collections.shuffle(allIndex);

		for (int i = 0; i < numberOfPlayers; i++) {
			int index = allIndex.get(i);
			Random random = new Random();
			int sideDecider = random.nextInt(2);
			char side = 'A';

			if (sideDecider == 1)
				side = 'B';
			WonderBoard wonderBoard = new WonderBoard(index, side);
			wonderBoard.gamePlay = this;
			boards.add(wonderBoard);

		}

	}

	void resolveNeighbours() {
		for (int index = 0; index < numberOfPlayers; index++) {
			Player player1 = boards.get(index).player;
			Player player2 = boards.get((index + 1) % numberOfPlayers).player;
			player1.rightNeighbor = player2;
			player2.leftNeighbor = player1;

		}

	}

	@SuppressWarnings("unchecked")
	public void ShiftCards(int age) {
		if (age % 2 == 1) {
			System.out.println("before change");
			for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
				System.out.println(boards.get(playerIndex).player.cardsOnHand.toString());
			}
			ArrayList<Card> temporaryList = (ArrayList<Card>) boards.get(0).player.cardsOnHand.clone();
			for (int playerIndex = 0; playerIndex < numberOfPlayers - 1; playerIndex++) {
				Player player = boards.get(playerIndex).player;
				Player nextPlayer = boards.get(playerIndex + 1).player;
				System.out.println(player.name + nextPlayer.name);

				player.setCardsOnHand(nextPlayer.getCardsOnHand());

			}

			boards.get(numberOfPlayers - 1).player.cardsOnHand = temporaryList;

			System.out.println("after change");
			for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
				System.out.println(boards.get(playerIndex).player.cardsOnHand.toString());
			}
		} else {
			System.out.println("before change");
			for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
				System.out.println(boards.get(playerIndex).player.cardsOnHand.toString());
			}
		

			ArrayList<Card> temporaryList = (ArrayList<Card>) boards.get(numberOfPlayers - 1).player.cardsOnHand
					.clone();

			for (int playerIndex = numberOfPlayers - 1; playerIndex > 0; playerIndex--) {
				Player player = boards.get(playerIndex).player;
				Player nextPlayer = boards.get(playerIndex-1).player;
				player.cardsOnHand = nextPlayer.cardsOnHand;

			}
			boards.get(0).player.cardsOnHand = temporaryList;
			
			System.out.println("after change");
			for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
				System.out.println(boards.get(playerIndex).player.cardsOnHand.toString());
			}

		}
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	@Override
	public String toString() {
		return "GamePlay [ " + " boards=" + boards + ", numberOfPlayers=" + numberOfPlayers + ", cardsToPlay="
				+ cardsToPlay + ", cardsDiscarded=" + cardsDiscarded + ", ages=" + ages + "]";
	}

	public void resolveConflicts(int age) {
		int agePoint = 0;
		if (age == 1)
			agePoint = 1;
		else if (age == 2)
			agePoint = 3;
		else
			agePoint = 5;

		for (int index = 0; index < numberOfPlayers; index++) {

			Player player = boards.get(index).player;
			int ownMilitaryPoint = boards.get(index).playerProperty.militaryPoint;
			int leftMilitaryPoint =  boards.get((index-1+numberOfPlayers)%numberOfPlayers).playerProperty.militaryPoint;
			int rightMilitaryPoint =  boards.get((index+1)%numberOfPlayers).playerProperty.militaryPoint;
			System.out.println("own "+ownMilitaryPoint+" left "+leftMilitaryPoint+" right "+rightMilitaryPoint);
			if (ownMilitaryPoint > leftMilitaryPoint) {

				player.wonderBoard.playerProperty.conflictTokens.add(1);
				player.wonderBoard.playerProperty.victoryPoint += agePoint;
				player.wonderBoard.playerProperty.victoryPointByMilitaryConflict += agePoint;
			} else if (ownMilitaryPoint < leftMilitaryPoint) {
			//	System.out.println("minus left ");
				player.wonderBoard.playerProperty.conflictTokens.add(new Integer(-1));
				player.wonderBoard.playerProperty.victoryPoint--;
				player.wonderBoard.playerProperty.victoryPointByMilitaryConflict--;
			}

			if (ownMilitaryPoint > rightMilitaryPoint) {

				player.wonderBoard.playerProperty.conflictTokens.add(1);
				player.wonderBoard.playerProperty.victoryPoint += agePoint;
				player.wonderBoard.playerProperty.victoryPointByMilitaryConflict += agePoint;
			} else if (ownMilitaryPoint < rightMilitaryPoint) {
			//	System.out.println("minus right");
				player.wonderBoard.playerProperty.conflictTokens.add(new Integer(-1));
				player.wonderBoard.playerProperty.victoryPoint--;
				player.wonderBoard.playerProperty.victoryPointByMilitaryConflict--;
			}
		//	System.out.println(player.wonderBoard.playerProperty.conflictTokens.toArray());
			System.out.println(player.wonderBoard.playerProperty.conflictTokens.toString());

		}

	}

	public void commitTrades() {
		for(int index=0; index<numberOfPlayers; index++){
			WonderBoard board = boards.get(index);
			
			for(Trade trade: board.bufferedTradeList){
				
				if(trade.neighbor.equals("left")){
					boards.get((index-1+numberOfPlayers)%numberOfPlayers).playerProperty.coins +=trade.coinsNeeded;
					board.playerProperty.coins-=trade.coinsNeeded;
				
				}
				else{
				
						boards.get((index+1)%numberOfPlayers).playerProperty.coins +=trade.coinsNeeded;
						board.playerProperty.coins-=trade.coinsNeeded;
					
					
				}
			}
			
		}
	}

}
