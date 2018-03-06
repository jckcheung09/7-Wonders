package wonders;

import java.io.Serializable;
import java.util.*;

public class PlayerProperty implements Serializable {
	// serialization needed for communicating with server
	private static final long serialVersionUID = 2L;
	// number of coins
	public int coins = 3;
	// present military point of the board
	public int militaryPoint = 0;
	// present victory point of the board
	public int victoryPoint = 0;
	// resource map
	public Map<String, Resource> mapOfResourceToName = new HashMap<String, Resource>();
	// list of played cards
	public List<String> playedCardList = new ArrayList<String>();
	// list of conflict tokens
	public ArrayList<Integer> conflictTokens = new ArrayList<Integer>();
	// if any raw materials is allowed to trade with 1 coin
	public boolean rawMaterialsTradeEnabled = false;
	// if any raw materials from left neighbor is allowed to trade with 1 coin
	public boolean leftRawMaterialsTrade = false;
	// if any raw materials from right neighbor is allowed to trade with 1 coin
	public boolean rightRawMaterialsTrade = false;
	// if any manufactured good from left neighbor is allowed to trade with 1
	// coin
	public boolean leftManufacturedGoodsTrade = false;
	// if any manufactured good from right neighbor is allowed to trade with 1  coin
	public boolean rightManufacturedGoodsTrade = false;
	// if any manufactured good is allowed to trade with 1 coin
	public boolean manufacturedGoodsTradeEnabled = false;
	// if use of a free card per age is allowed
	public boolean freeCardUseAllowed = false;
	//  if use of the seventh card per age is allowed
	public boolean seventhCardUseAllowed = false;
	// if discarded card can be used
	public boolean discardedCardUseAllowed = false;
	// if special raw material trade is allowed
	public boolean specialRawMaterialTrade = false;
	// victory points by military conflicts
	public int victoryPointByMilitaryConflict = 0;
	// victory points by civilian structure
	public int victoryPointByCivilianStructure = 0;
	
	// victory points by commercial structure
	public int victoryPointByCommercialCard = 0;
	//  victory points by scientific structure
	public int victoryPointByScientificStructure = 0;
	// victory points by coins
	public int victoryPointByTreasuryContent = 0;
	// victory points by building stages of wonder board
	public int victoryPointByStageBuilding = 0;
	// victory points by guild cards
	public int victoryPointByGuilds = 0;
	// overriding to string method for printing the player properties
	@Override
	public String toString() {
		return "PlayerProperty [coins=" + coins + ", militaryPoint=" + militaryPoint + ", victoryPoint=" + victoryPoint
				+ ", mapOfResourceToName=" + mapOfResourceToName + ", playedCardList=" + playedCardList
				+ ", conflictTokens=" + conflictTokens + ", rawMaterialsTradeEnabled=" + rawMaterialsTradeEnabled
				+ ", leftRawMaterialsTrade=" + leftRawMaterialsTrade + ", rightRawMaterialsTrade="
				+ rightRawMaterialsTrade + ", leftManufacturedGoodsTrade=" + leftManufacturedGoodsTrade
				+ ", rightManufacturedGoodsTrade=" + rightManufacturedGoodsTrade + ", manufacturedGoodsTradeEnabled="
				+ manufacturedGoodsTradeEnabled + ", freeCardUseAllowed=" + freeCardUseAllowed
				+ ", seventhCardUseAllowed=" + seventhCardUseAllowed + ", discardedCardUseAllowed="
				+ discardedCardUseAllowed + ", specialRawMaterialTrade=" + specialRawMaterialTrade
				+ ", victoryPointByMilitaryConflict=" + victoryPointByMilitaryConflict
				+ ", victoryPointByCivilianStructure=" + victoryPointByCivilianStructure
				+ ", victoryPointByCommercialCard=" + victoryPointByCommercialCard
				+ ", victoryPointByScientificStructure=" + victoryPointByScientificStructure
				+ ", victoryPointByTreasuryContent=" + victoryPointByTreasuryContent + ", victoryPointByStageBuilding="
				+ victoryPointByStageBuilding + ", victoryPointByGuilds=" + victoryPointByGuilds + "]";
	}

}
