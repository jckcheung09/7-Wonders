
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
// child class of card class
public class CommercialStructureCard extends Card implements Serializable{
	// serialization needed for communicating with server
	private static final long serialVersionUID = 17L;
	// if criteria from left neighbor is enabled
	boolean left;
	// if criteria from right neighbor is enabled
	boolean right;
	//  if criteria from own is enabled
	boolean own;
	//  if criteria of stage building is enabled
	boolean criteriaOfStageBuilding;
	// if any type of raw materials trade is made cheap
	boolean rawMaterialsTrade = false;
	// if any type of manufactured goods trade is made cheap
	boolean manufacturedGoodsTrade = false;
	// number of coins produced
	int coinsProduced = 0;
	// the list of color of card which is the criteria
	ArrayList<String> criteriaColors = new ArrayList<String>();
	// victory points gained by criteria
	int victoryPointsByCriteria = 0;
	// if any type of manufactured goods is produced
	boolean anyManufacturedGood = false;
	// if any type of raw material is produced
	boolean anyRawMaterial = false;
	// coins gained by criteria
	int coinsProducedByCriteria = 0;
	// constructor with parameters: id, name, minimum number of players, left/right/own criteria
	public CommercialStructureCard(int id, String name, int minimumPlayers, boolean left, boolean right, boolean own) {
		this.id = id;
		this.name = name;
		this.minimumPlayersRequired = minimumPlayers;
		this.left = left;
		this.right = right;
		this.own = own;
		// setting color yellow
		this.color="yellow";
	}
	
	 // overriding print details  method for printing the card details
	   
	@Override
	public String printDetails(){
		
	return	" \nLeft = " + left + "\nRight = " + right + "\nOwn=" + own
		+ "\ncriteriaOfStageBuilding = " + criteriaOfStageBuilding + "\nRawMaterialsTrade=" + rawMaterialsTrade
		+ "\nManufacturedGoodsTrade = " + manufacturedGoodsTrade + "\nCoinsProduced=" + coinsProduced
		+ "\nCriteria Colors = " + criteriaColors + "\nVictory points by criteria = " + victoryPointsByCriteria
		+ "\nAnyManufacturedGood = " + anyManufacturedGood + "\nAny raw material=" + anyRawMaterial
		+ "\nCoins produced by criteria=" + coinsProducedByCriteria + "\nCost = " + cost
		+ "\nResources required=" + resourcesRequired + "\nStructure required=" + structureRequired
		+ "\nFutureStructures=" + futureStructures + "]";
		
	}
	 // overriding to string  method for printing the card name
	   
	@Override
	public String toString() {
		return "Commercial Card: "+name;
		
	}
	// overriding play method
	@Override
	public void playCard(WonderBoard wonderBoard) {
		// random number generator	
		Random random = new Random();
			// increase coins of player if coins produced
			wonderBoard.playerProperty.coins += coinsProduced;
			// if criteria of stage building is enabled then add coins as multiple of 3 by stages built
			if(criteriaOfStageBuilding){
				wonderBoard.playerProperty.coins += 3*wonderBoard.stagesBuilt;
				// update victory points and victory points by commercial structure
				wonderBoard.playerProperty.victoryPoint += wonderBoard.stagesBuilt;
				wonderBoard.playerProperty.victoryPointByCommercialCard+=wonderBoard.stagesBuilt;
			}// if single production of any raw material is enabled
			else if(anyRawMaterial){
				// add an alternative resource to player property like wood/clay/ore/stone
				AlternativeResource alternativeResource = new AlternativeResource();
				alternativeResource.names.add("wood");
				alternativeResource.names.add("clay");
				alternativeResource.names.add("ore");
				alternativeResource.names.add("stone");
				wonderBoard.playerProperty.mapOfResourceToName.put(random.nextInt()+"",alternativeResource);
				
				wonderBoard.resourcesProduced.add(alternativeResource);
			}// if single production of any raw manufactured good is enabled
			
			else if(anyManufacturedGood){
				// add an alternative resource to player property like loom/glass/papyrus
				
				AlternativeResource alternativeResource = new AlternativeResource();
				alternativeResource.names.add("loom");
				alternativeResource.names.add("glass");
				alternativeResource.names.add("papyrus");
				wonderBoard.playerProperty.mapOfResourceToName.put(random.nextInt()+"",alternativeResource);
					
				wonderBoard.resourcesProduced.add(alternativeResource);
			}else if(rawMaterialsTrade){
				// enable if raw materials trade of any kind is there
				wonderBoard.playerProperty.rawMaterialsTradeEnabled = true;
			}else if(manufacturedGoodsTrade){
				// enable if manufactured goods trade of any kind is there
				
				wonderBoard.playerProperty.manufacturedGoodsTradeEnabled = true;
			}else {
				// for each criteria color
					for (String color : criteriaColors) {
						// increase victory points available from own,left,right neighbor if those conditions are ok
						int victoryPoint = (left ? getVictoryPointOfColour(wonderBoard.player.leftNeighbor, color) : 0)
								+ (right ? getVictoryPointOfColour(wonderBoard.player.rightNeighbor, color) : 0)
								+ (own ? getVictoryPointOfColour(wonderBoard.player, color) : 0);
						wonderBoard.playerProperty.victoryPoint += victoryPoint;
						wonderBoard.playerProperty.victoryPointByCommercialCard += victoryPoint;
						// increase coins by own,left,right neighbor if those conditions are ok					
						int earnedCoin = (left ? getCoinsOfColour(wonderBoard.player.leftNeighbor, color) : 0)
								+ (right ? getCoinsOfColour(wonderBoard.player.rightNeighbor, color) : 0)
								+ (own ? getCoinsOfColour(wonderBoard.player, color) : 0);
						
						wonderBoard.playerProperty.coins += earnedCoin;
					}

				}
			
			
			
		}
	// calculate victory points of any particular neighbor of any particular color
	private int getVictoryPointOfColour(Player player, String color) {
		int victoryPoint = 0;
		// traverse all the cards of neighbor
		for (Card playedCard : player.cardsPlayed) {
			// if color matches with criteria then increase victory points
			if (playedCard.color.equals(color)) {
				victoryPoint+=victoryPointsByCriteria;
				
			}
		}
		return victoryPoint;
	}
	
	// calculate coins of any particular neighbor of any particular color

	private int getCoinsOfColour(Player player, String color) {
		int coin = 0;
		// traverse all the cards of neighbor		
		for (Card playedCard : player.cardsPlayed) {
			if (playedCard.color.equals(color)) {
				// if color matches with criteria then increase victory points
				coin+=coinsProducedByCriteria;
				
			}
		}
		return coin;
	}
}

