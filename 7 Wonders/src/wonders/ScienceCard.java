
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class ScienceCard extends Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	ArrayList<Resource> producedResources = new ArrayList<Resource>();

	ScienceCard(int id, String name, int minimumPlayer, int type) {
		this.id = id;
		this.name = name;
		this.minimumPlayersRequired = minimumPlayer;
		this.color = "green";
		if (type == 1) {
			this.producedResources.add(new Resource("ScienceStar", 1));
		} else if (type == 2) {
			this.producedResources.add(new Resource("ScienceWheel", 1));

		} else {
			this.producedResources.add(new Resource("ScienceCircle", 1));

		}
	}
	@Override
	public String printDetails(){
		return   "\nCost = " + cost + "\nResources required = " + resourcesRequired.toString()
		+ "\nStructures required=" + structureRequired.toString() + "\nFuture structures possibble = "
		+ futureStructures;
		
		
	}
	@Override
	public String toString() {
		return "ScienceCard: "+name ;
	}

	@Override
	public void playCard(WonderBoard wonderBoard) {
	
		
				addResource(wonderBoard);

	}
	
	private void addResource(WonderBoard wonderBoard) {
		Map<String, Resource> resourceMap = wonderBoard.playerProperty.mapOfResourceToName;
		for (Resource resource : producedResources) {
			if (resourceMap.containsKey(resource.name)) {
				resourceMap.get(resource.name).quantity += resource.quantity;
			} else {
				resourceMap.put(resource.name, resource);
			}
		}
	}
}
