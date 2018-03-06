
package wonders;

import java.io.Serializable;

public class MilitaryStructureCard extends Card implements Serializable{
	// serialization needed for communicating with server
	private static final long serialVersionUID = 11L;
// military points gained by the card
	int militaryPoint;
	// constructor using fields like id, name, minimum number of players, military points gained
    MilitaryStructureCard(int id, String name, int minimumPlayer, int point) {
        this.id=id;
        this.name=name;
        this.minimumPlayersRequired=minimumPlayer;
        this.militaryPoint=point;
        this.color="red";
    }
 // overriding print details method for printing the card details
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
	// increase military points of wonder board
	@Override
	public void playCard(WonderBoard wonderBoard) {
		wonderBoard.playerProperty.militaryPoint += militaryPoint;
	}
}
