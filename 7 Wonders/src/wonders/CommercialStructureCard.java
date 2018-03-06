
package wonders;

import java.io.Serializable;
import java.util.ArrayList;

public class CommercialStructureCard extends Card implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 17L;
	boolean left;
	boolean right;
	boolean own;
	boolean criteriaOfStageBuilding;
	boolean rawMaterialsTrade = false;
	boolean manufacturedGoodsTrade = false;
	int coinsProduced = 0;
	ArrayList<String> criteriaColors = new ArrayList<String>();
	int victoryPointsByCriteria = 0;
	boolean anyManufacturedGood = false;
	boolean anyRawMaterial = false;
	int coinsProducedByCriteria = 0;
	public CommercialStructureCard(int id, String name, int minimumPlayers, boolean left, boolean right, boolean own) {
		this.id = id;
		this.name = name;
		this.minimumPlayersRequired = minimumPlayers;
		this.left = left;
		this.right = right;
		this.own = own;
		this.color="yellow";
	}
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
	@Override
	public String toString() {
		return "Commercial Card: "+name;
		
	}
	@Override
	public void playCard(WonderBoard wonderBoard) {
		
			wonderBoard.playerProperty.coins += coinsProduced;
			if(criteriaOfStageBuilding){
				wonderBoard.playerProperty.coins += 3*wonderBoard.stagesBuilt;
				wonderBoard.playerProperty.victoryPoint += wonderBoard.stagesBuilt;
			}else if(anyRawMaterial){
				AlternativeResource alternativeResource = new AlternativeResource();
				alternativeResource.names.add("wood");
				alternativeResource.names.add("clay");
				alternativeResource.names.add("ore");
				alternativeResource.names.add("stone");
				wonderBoard.playerProperty.mapOfResourceToName.put("alternate",alternativeResource);
				
				wonderBoard.resourcesProduced.add(alternativeResource);
			}else if(anyManufacturedGood){
				AlternativeResource alternativeResource = new AlternativeResource();
				alternativeResource.names.add("loom");
				alternativeResource.names.add("glass");
				alternativeResource.names.add("papyrus");
				wonderBoard.playerProperty.mapOfResourceToName.put("alternate",alternativeResource);
				
				wonderBoard.resourcesProduced.add(alternativeResource);
			}else if(rawMaterialsTrade){
				wonderBoard.playerProperty.rawMaterialsTradeEnabled = true;
			}else if(manufacturedGoodsTrade){
				wonderBoard.playerProperty.manufacturedGoodsTradeEnabled = true;
			}else {
					for (String color : criteriaColors) {
						int victoryPoint = (left ? getVictoryPointOfColour(wonderBoard.player.leftNeighbor, color) : 0)
								+ (right ? getVictoryPointOfColour(wonderBoard.player.rightNeighbor, color) : 0)
								+ (own ? getVictoryPointOfColour(wonderBoard.player, color) : 0);
						wonderBoard.playerProperty.victoryPoint += victoryPoint;
						
						int earnedCoin = (left ? getCoinsOfColour(wonderBoard.player.leftNeighbor, color) : 0)
								+ (right ? getCoinsOfColour(wonderBoard.player.rightNeighbor, color) : 0)
								+ (own ? getCoinsOfColour(wonderBoard.player, color) : 0);
						
						wonderBoard.playerProperty.coins += earnedCoin;
					}

				}
			
			
			
		}
	private int getVictoryPointOfColour(Player player, String color) {
		int victoryPoint = 0;
		for (Card playedCard : player.cardsPlayed) {
			if (playedCard.color.equals(color)) {
				victoryPoint+=victoryPointsByCriteria;
				
			}
		}
		return victoryPoint;
	}
	private int getCoinsOfColour(Player player, String color) {
		int coin = 0;
		for (Card playedCard : player.cardsPlayed) {
			if (playedCard.color.equals(color)) {
				coin+=coinsProducedByCriteria;
				
			}
		}
		return coin;
	}
}

