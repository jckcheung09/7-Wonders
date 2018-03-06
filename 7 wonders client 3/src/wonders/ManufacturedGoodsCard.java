
package wonders;

import java.io.Serializable;
import java.util.*;

public class ManufacturedGoodsCard extends Card implements Serializable {
	// serialization needed for communicating with server
	private static final long serialVersionUID = 13L;
	// list of goods manufactured
	ArrayList<Resource> manufacturedGoods = new ArrayList<Resource>();
	// if only one type of resource is allowed to use of list of resources
	// manufactured
	boolean singleProductionRule = false;

	// constructor using fields like id, name, minimum number of players
	ManufacturedGoodsCard(int id, String name, int minimumPlayer) {
		this.id = id;
		this.name = name;
		this.minimumPlayersRequired = minimumPlayer;
		this.color = "grey";
	}

	// overriding print details method for printing the card details
	@Override
	public String printDetails() {
		return "\nProduced goods=" + manufacturedGoods.toString() + ", color=" + color + ", cost=" + cost
				+ "\nSingle production criteria = " + singleProductionRule + "\nResources required=" + resourcesRequired
				+ "\nStructure required=" + structureRequired.toString() + "\nFuture structures = "
				+ futureStructures.toString();

	}

	// overriding to string method for printing the card name
	@Override
	public String toString() {
		return "ManufacturedGoodsCard: " + name;
	}

	// play card
	@Override
	public void playCard(WonderBoard wonderBoard) {

		// add resources produced by the card to resource map
		addResource(wonderBoard);

	}

	// add resources produced by the card to resource map
	private void addResource(WonderBoard wonderBoard) {
		// if single production of resource is enabled
		if (singleProductionRule) {
			// random number generator
			Random rand = new Random();
			// create an alternative resource with goods manufactured list of the card
			AlternativeResource alternativeResource = new AlternativeResource();
			for (Resource resource : manufacturedGoods) {
				alternativeResource.names.add(resource.name);
			}
			// add the alternative resource to wonder board and resource name to resource map 
			// with random number as key
			wonderBoard.resourcesProduced.add(alternativeResource);
			wonderBoard.playerProperty.mapOfResourceToName.put(rand.nextDouble() + "", alternativeResource);
		} else {
			// if single production not enabled
			// get resource map from player property
			Map<String, Resource> resourceMap = wonderBoard.playerProperty.mapOfResourceToName;
			// add each resource of manufactured goods list to 
			// resource map
			for (Resource resource : manufacturedGoods) {
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
}
