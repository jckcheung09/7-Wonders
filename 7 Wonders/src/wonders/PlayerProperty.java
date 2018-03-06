package wonders;
import java.io.Serializable;
import java.util.*;
public class PlayerProperty implements Serializable{
	
	private static final long serialVersionUID = 2L;
	public int coins = 3;
	public int militaryPoint=0;
	public int victoryPoint=0;
	public Map<String,Resource> mapOfResourceToName = new HashMap<String, Resource>();
	public List<String> playedCardList = new ArrayList<String>();
	public ArrayList<Integer> conflictTokens= new ArrayList<Integer>();  
	public boolean rawMaterialsTradeEnabled = false;
	public boolean leftRawMaterialsTrade = false;
	public boolean rightRawMaterialsTrade = false;
	public boolean leftManufacturedGoodsTrade = false;
	public boolean rightManufacturedGoodsTrade = false;
	public boolean manufacturedGoodsTradeEnabled = false;
	public boolean freeCardUseAllowed=false;
	public boolean seventhCardUseAllowed = false;
	public boolean discardedCardUseAllowed =false;
	public boolean specialRawMaterialTrade=false;
	public int victoryPointByMilitaryConflict=0;
	public int victoryPointByCivilianStructure=0;
	public int victoryPointByCommercialCard=0;
	public int victoryPointByScientificStructure=0;
	public int victoryPointByTreasuryContent=0;
	public int victoryPointByStageBuilding=0;
	public int victoryPointByGuilds=0;
	
}
