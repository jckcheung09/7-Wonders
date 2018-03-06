
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class ScienceCard extends Card implements Serializable{
	// serialization needed for communicating with server
	private static final long serialVersionUID = 7L;
	// list of goods produced
	ArrayList<Resource> producedResources = new ArrayList<Resource>();
	// constructor using fields like id, name, minimum number of players, science resource type
	ScienceCard(int id, String name, int minimumPlayer, int type) {
		this.id = id;
		this.name = name;
		this.minimumPlayersRequired = minimumPlayer;
		// set color as green
		this.color = "green";
		// if type is 1 then add resource science star to produced resource list
		if (type == 1) {
			this.producedResources.add(new Resource("ScienceStar", 1));
		}	// if type is 2 then add resource science wheel to produced resource list
		 else if (type == 2) {
			this.producedResources.add(new Resource("ScienceWheel", 1));

		}	// if type is 3 then add resource science circle to produced resource list
			 else {
			this.producedResources.add(new Resource("ScienceCircle", 1));

		}
	}
	// overriding print details method for printing the card details
	@Override
	public String printDetails(){
		return   "\nCost = " + cost + "\nResources required = " + resourcesRequired.toString()
		+ "\nStructures required=" + structureRequired.toString() + "\nFuture structures possibble = "
		+ futureStructures;
		
		
	}
	  // get details text of this resource
	@Override
	public String toString() {
		return "ScienceCard: "+name ;
	}

	@Override
	public void playCard(WonderBoard wonderBoard) {
	
		// add resources to resource map
				addResource(wonderBoard);

	}
	
	private void addResource(WonderBoard wonderBoard) {
		// get resource map from player property
					Map<String, Resource> resourceMap = wonderBoard.playerProperty.mapOfResourceToName;
					// add each resource of manufactured goods list to 
					// resource map
					for (Resource resource : producedResources) {
						// if already exists then increase quantity
						if (resourceMap.containsKey(resource.name)) {
							resourceMap.get(resource.name).quantity += resource.quantity;
						} else {
							// else put as a new entry to map
							resourceMap.put(resource.name, resource);
						}
					}
	}
}
