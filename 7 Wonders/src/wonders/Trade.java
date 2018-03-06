package wonders;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Trade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 22L;
	Resource resource;
	String neighbor;
	int coinsNeeded=0;
	boolean isUsed = false;
	public Trade(Resource resource, String neighbor) {

		this.resource = resource;
		this.neighbor = neighbor;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(String neighbor) {
		this.neighbor = neighbor;
	}

	public boolean isTradePossible(WonderBoard wonderBoard) {
		Player player = wonderBoard.player;
		Player neighborPlayer;
		
		boolean specialTradeAllowed = false;
		int availableQuantity=0;
		if(neighbor.equals("left"))
			{
				neighborPlayer = player.leftNeighbor;
				if(resource.name.equals("loom")|| resource.name.equals("glass")|| resource.name.equals("papyrus")){
					specialTradeAllowed=wonderBoard.playerProperty.leftManufacturedGoodsTrade;
				}
				else{
					specialTradeAllowed =wonderBoard.playerProperty.leftRawMaterialsTrade;
				}
			}
		else
			{
				neighborPlayer = player.rightNeighbor;
				if(resource.name.equals("loom")|| resource.name.equals("glass")|| resource.name.equals("papyrus")){
					specialTradeAllowed=wonderBoard.playerProperty.rightManufacturedGoodsTrade;
				}
				else{
					specialTradeAllowed =wonderBoard.playerProperty.rightRawMaterialsTrade;
				}
			}
		if(specialTradeAllowed){
			coinsNeeded = resource.quantity;
		}
		else{
			coinsNeeded = resource.quantity*2;
		}
		WonderBoard neighborBoard = neighborPlayer.wonderBoard;
		for (Resource resourceAvailable : neighborBoard.resourcesProduced) {
			if (resourceAvailable.name.equals(resource.name)) {
				availableQuantity += resourceAvailable.quantity;
				
			}
		}
		
		if(resource.quantity>availableQuantity){
			JOptionPane.showMessageDialog(null, "Sorry resource not available from neighbor");
			return false;
		}
		
		else if(coinsNeeded>wonderBoard.playerProperty.coins)
		{
			JOptionPane.showMessageDialog(null, "Sorry not enough coins to make this trade");
			return false;
		}
	
		
		
		return true;
	}

	@Override
	public String toString() {
		return "Trade: " + resource + ", from=" + neighbor ;
	}
	
	
	
}
