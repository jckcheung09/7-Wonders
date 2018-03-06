
package wonders;

import java.io.Serializable;

public class CivilianStructureCard extends Card implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 19L;
	int victoryPoint;

    CivilianStructureCard(int id, String name, int minimumPlayer, int point) {
        this.id=id;
        this.name=name;
        this.minimumPlayersRequired=minimumPlayer;
        this.victoryPoint=point;
        this.color="blue";
    }
    @Override
    public String printDetails(){
    	return  "[\n victory points gained = " + victoryPoint  + "\nCost = "
  				+ cost + "\nResources required = " + resourcesRequired.toString() + "\nStructures required = " + structureRequired.toString()
  				+ "\nFuture structures possibble = " + futureStructures ;
    	
    	
    }
    
    @Override
	public String toString() {
		return "CivilianStructureCard: "+name; 
	}
	@Override
	public void playCard(WonderBoard wonderBoard) {
		
		 wonderBoard.playerProperty.victoryPoint += victoryPoint;
		
	}
}
