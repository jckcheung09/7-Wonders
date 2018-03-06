
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;


public class RawMaterialsCard extends Card implements Serializable{
  

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	ArrayList<Resource> producedResources= new ArrayList<Resource>();
    boolean singleProductionEnabled;

    RawMaterialsCard(int i, String name, int minPlayer, int cost, boolean singleProduction) {
        
        this.id=i;
        this.name=name;
        this.minimumPlayersRequired=minPlayer;
        this.cost = cost;
        this.singleProductionEnabled=singleProduction;
        this.color = "brown";
    }
    @Override
    public String printDetails(){
    	return "\nProduced resources=" + producedResources.toString() + "\nSingle production criteria = "
  				+ singleProductionEnabled+ ", color=" + color + ", cost=" + cost
  				+ "\nResources required=" + resourcesRequired.toString() + "\nStructure required=" + structureRequired.toString()
  				+ "\nFuture structures = " + futureStructures.toString() ;
    	
    }
    @Override
  	public String toString() {
  		return "RawMaterialsCard: "+name;
  	}

	@Override
	public void playCard(WonderBoard wonderBoard) {
		
			payCost(wonderBoard);
			addResource(wonderBoard);
		
	}

	private void addResource(WonderBoard wonderBoard) {
		if (singleProductionEnabled) {
			AlternativeResource alternativeResource = new AlternativeResource();
			for(Resource resource:producedResources){
				alternativeResource.names.add(resource.name);
			}
			wonderBoard.resourcesProduced.add(alternativeResource);
		} else {
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

}
