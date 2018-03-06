
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class RawMaterialsCard extends Card implements Serializable{
	// serialization needed for communicating with server
	private static final long serialVersionUID = 10L;
	// list of goods produced
	ArrayList<Resource> producedResources= new ArrayList<Resource>();
	// if only one type of resource is allowed to use of list of resources
	// produced
    boolean singleProductionEnabled;
 // constructor using fields like id, name, minimum number of players, single production indicator
    RawMaterialsCard(int i, String name, int minPlayer, int cost, boolean singleProduction) {
        
        this.id=i;
        this.name=name;
        this.minimumPlayersRequired=minPlayer;
        this.cost = cost;
        this.singleProductionEnabled=singleProduction;
        // setting color as brown
        this.color = "brown";
    }
 // overriding print details method for printing the card details
    @Override
    public String printDetails(){
    	return "\nProduced resources=" + producedResources.toString() + "\nSingle production criteria = "
  				+ singleProductionEnabled+ ", color=" + color + ", cost=" + cost
  				+ "\nResources required=" + resourcesRequired.toString() + "\nStructure required=" + structureRequired.toString()
  				+ "\nFuture structures = " + futureStructures.toString() ;
    	
    }
 // overriding to string method for printing the card name
    @Override
  	public String toString() {
  		return "RawMaterialsCard: "+name;
  	}
// play card
	@Override
	public void playCard(WonderBoard wonderBoard) {
		// pay cost of the card
			payCost(wonderBoard);
			// add resources produced by the card to resource map
			addResource(wonderBoard);
		
	}
	// add resources produced by the card to resource map
	private void addResource(WonderBoard wonderBoard) {
		// if single production of resource is enabled
		if (singleProductionEnabled) {
			// random number generator
			Random rand = new Random();
			// create an alternative resource with goods produced list of the card
			AlternativeResource alternativeResource = new AlternativeResource();
			for(Resource resource:producedResources){
				alternativeResource.names.add(resource.name);
			}
			// add the alternative resource to wonder board and resource name to resource map 
			// with random number as key
			wonderBoard.resourcesProduced.add(alternativeResource);
			wonderBoard.playerProperty.mapOfResourceToName.put( rand.nextDouble()+"",alternativeResource);
		} else {
			// if single production not enabled
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

}
