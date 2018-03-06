
package wonders;

import java.io.Serializable;

public class MilitaryStructureCard extends Card implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	int militaryPoint;
    MilitaryStructureCard(int id, String name, int minimumPlayer, int point) {
        this.id=id;
        this.name=name;
        this.minimumPlayersRequired=minimumPlayer;
        this.militaryPoint=point;
        this.color="red";
    }
    @Override
    public String printDetails(){
    	return "[ \n military points gained = " + militaryPoint  + "\nCost="
 				+ cost + "\nResources required = " + resourcesRequired.toString() + "\nStructures required = " + structureRequired.toString()
 				+ "\nFuture structures possibble = " + futureStructures ;
    	
    	
    }
    
	@Override
	public String toString() {
		return "MilitaryStructureCard: "+name  ;
	}
	@Override
	public void playCard(WonderBoard wonderBoard) {
		wonderBoard.playerProperty.militaryPoint += militaryPoint;
	}
}
