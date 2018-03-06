
package wonders;

import java.io.Serializable;



//civilian structure card is a child class of card class

public class CivilianStructureCard extends Card implements Serializable{
	//serialization needed for communicating with server
	private static final long serialVersionUID = 19L;
	// victory point gained by this card
	int victoryPoint;
	// constructor containing id number, name, minimum number of players and victory points gained
    CivilianStructureCard(int id, String name, int minimumPlayer, int point) {
        this.id=id;
        this.name=name;
        this.minimumPlayersRequired=minimumPlayer;
        this.victoryPoint=point;
        this.color="blue";
    }
    //  get text containing card details

    public String printDetails(){
    	return  "[\n victory points gained = " + victoryPoint  + "\nCost = "
  				+ cost + "\nResources required = " + resourcesRequired.toString() + "\nStructures required = " + structureRequired.toString()
  				+ "\nFuture structures possibble = " + futureStructures ;
    	
    	
    }
    // overriding to string  method for printing the card details
    
    @Override
	public String toString() {
		return "CivilianStructureCard: "+name; 
	}
    // play card method 
	@Override
	public void playCard(WonderBoard wonderBoard) {
		// increase victory point of player
		 wonderBoard.playerProperty.victoryPoint += victoryPoint;
		 // increase victory point by civilian structure
		 wonderBoard.playerProperty.victoryPointByCivilianStructure+=victoryPoint;
	}
}
