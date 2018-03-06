
package wonders;

import java.io.Serializable;
import java.util.*;

public class ManufacturedGoodsCard extends Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 13L;
	ArrayList<Resource> manufacturedGoods = new ArrayList<Resource>();
	boolean singleProductionRule;

	ManufacturedGoodsCard(int id, String name, int minimumPlayer) {
		this.id = id;
		this.name = name;
		this.minimumPlayersRequired = minimumPlayer;
		this.color = "grey";
	}
	@Override
	public String printDetails(){
		return "\nProduced goods=" + manufacturedGoods.toString() + ", color=" + color + ", cost="
				+ cost + "\nSingle production criteria = " + singleProductionRule + "\nResources required="
				+ resourcesRequired + "\nStructure required=" + structureRequired.toString() + "\nFuture structures = "
				+ futureStructures.toString();
		
		
	}
	@Override
	public String toString() {
		return "ManufacturedGoodsCard: "+name;
	}

	@Override
	public void playCard(WonderBoard wonderBoard) {
		

		
		addResource(wonderBoard);

	}

	private void addResource(WonderBoard wonderBoard) {
		Map<String, Resource> resourceMap = wonderBoard.playerProperty.mapOfResourceToName;
		for (Resource resource : manufacturedGoods) {
			if (resourceMap.containsKey(resource.name)) {
				resourceMap.get(resource.name).quantity += resource.quantity;
			} else {
				resourceMap.put(resource.name, resource);
			}
		}
	}
}
