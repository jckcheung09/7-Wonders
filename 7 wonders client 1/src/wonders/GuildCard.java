
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

// child of card class
public class GuildCard extends Card implements Serializable{

	// serialization needed for communicating with server
	private static final long serialVersionUID = 14L;
	// if criteria from left neighbor is enabled
	boolean left;
	// if criteria from right neighbor is enabled
	boolean right;
	//  if criteria from own is enabled
	boolean own;	
	//  if criteria of stage building is enabled
	boolean criteriaOfStageBuilding;
//  if criteria of military win is enabled
	boolean criteriaOfMilitaryWin;
// if any scientific resource production is enabled
	boolean scientistsGuild;
	// the list of color of card which is the criteria
	ArrayList<String> criteriaColors = new ArrayList<String>();
	// victory points gained by criteria
	int victoryPointsByCriteria = 0;
	// constructor with parameters: id, name, minimum number of players, left/right/own criteria
	public GuildCard(int id, String name, boolean left, boolean right, boolean own, int victoryPoints) {
		this.id = id;
		this.name = name;
		this.left = left;
		this.right = right;
		this.own = own;
		this.victoryPointsByCriteria = victoryPoints;
		this.color = "purple";
		this.minimumPlayersRequired = 3;
	}
	// overriding print details  method for printing the card details
	public String printDetails(){
		return " [left=" + left + ", right=" + right + ", own=" + own + ", criteriaOfStageBuilding="
				+ criteriaOfStageBuilding + ", criteriaOfMilitaryWin=" + criteriaOfMilitaryWin + ", scientistsGuild="
				+ scientistsGuild + ", criteriaColors=" + criteriaColors + ", victoryPoints=" + victoryPointsByCriteria
				+ ", name=" + name + ", color=" + color + ", cost=" + cost + ", resourcesRequired=" + resourcesRequired
				+ ", structureRequired=" + structureRequired + ", futureStructures=" + futureStructures + "]";
	
	}
	 // overriding to string  method for printing the card name
	@Override
	public String toString() {
		return "GuildCard:  "+name;
				
	}
	// overriding play method
	@Override
	public void playCard(WonderBoard wonderBoard) {
		// random number generator
		Random random = new Random();
		// if criteria of stage building enabled
		if (criteriaOfStageBuilding) {
			// increase victory points as number of stages built
			// update victory points by guild
			wonderBoard.playerProperty.victoryPoint+= wonderBoard.stagesBuilt;
			wonderBoard.playerProperty.victoryPointByGuilds+=wonderBoard.stagesBuilt;
		} else if (criteriaOfMilitaryWin) {
			// if criteria of military points is enabled then
			
			Player myself = wonderBoard.player;
			
			// get left and right neighbor
			Player leftNeighbour = myself.leftNeighbor;
			Player rightNeighbour = myself.rightNeighbor;
			// for each military win over 
			// left neighbor increase victory points
			for (int conflictToken : leftNeighbour.wonderBoard.playerProperty.conflictTokens) {
				victoryPointsByCriteria += conflictToken;
			}
			// for each military win over 
			// right neighbor increase victory points
			for (int conflictToken : rightNeighbour.wonderBoard.playerProperty.conflictTokens) {
				victoryPointsByCriteria += conflictToken;
			}
			wonderBoard.playerProperty.victoryPoint += victoryPointsByCriteria;
			wonderBoard.playerProperty.victoryPointByGuilds+=victoryPointsByCriteria;
		} else if (scientistsGuild) {
			// for scientsGuild card 
			// create an alternative resource of science
			// like scienceStar/scienceCircle/scienceWheel
			
			AlternativeResource alternativeResource = new AlternativeResource();

			alternativeResource.names.add("scienceStar");
			alternativeResource.names.add("scienceCircle");
			alternativeResource.names.add("scienceWheel");
			wonderBoard.playerProperty.mapOfResourceToName.put(random.nextInt()+"",alternativeResource);
				
			wonderBoard.resourcesProduced.add(alternativeResource);
		} else {
			// if criteria by card color is active then
			for (String color : criteriaColors) {
				// increase victory points by number of cards of neighbor 
				// with the criteria color
				int victoryPoint = (left ? getVictoryPointOfColour(wonderBoard.player.leftNeighbor, color) : 0)
						+ (right ? getVictoryPointOfColour(wonderBoard.player.rightNeighbor, color) : 0)
						+ (own ? getVictoryPointOfColour(wonderBoard.player, color) : 0);
				wonderBoard.playerProperty.victoryPoint += victoryPoint;
				wonderBoard.playerProperty.victoryPointByGuilds += victoryPoint;
				
			}

		}
	}
// calculate victory points gained by number of
	//cards played by neighbor of a particular color
	private int getVictoryPointOfColour(Player player, String color) {
		int victoryPoint = 0;
		for (Card playedCard : player.cardsPlayed) {
			if (playedCard.color.equals(color)) {
				victoryPoint+=victoryPointsByCriteria;
			}
		}
		return victoryPoint;
	}
}
