
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class GuildCard extends Card implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 14L;
	boolean left;
	boolean right;
	boolean own;
	boolean criteriaOfStageBuilding;
	boolean criteriaOfMilitaryWin;
	boolean scientistsGuild;
	ArrayList<String> criteriaColors = new ArrayList<String>();
	int victoryPointsByCriteria = 0;

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
	public String printDetails(){
		return " [left=" + left + ", right=" + right + ", own=" + own + ", criteriaOfStageBuilding="
				+ criteriaOfStageBuilding + ", criteriaOfMilitaryWin=" + criteriaOfMilitaryWin + ", scientistsGuild="
				+ scientistsGuild + ", criteriaColors=" + criteriaColors + ", victoryPoints=" + victoryPointsByCriteria
				+ ", name=" + name + ", color=" + color + ", cost=" + cost + ", resourcesRequired=" + resourcesRequired
				+ ", structureRequired=" + structureRequired + ", futureStructures=" + futureStructures + "]";
	
	}
	@Override
	public String toString() {
		return "GuildCard:  "+name;
				
	}

	@Override
	public void playCard(WonderBoard wonderBoard) {
		
		
		if (criteriaOfStageBuilding) {
			
			wonderBoard.playerProperty.victoryPoint+= wonderBoard.stagesBuilt;
		} else if (criteriaOfMilitaryWin) {
			Player myself = wonderBoard.player;
			Player leftNeighbour = myself.leftNeighbor;
			Player rightNeighbour = myself.rightNeighbor;

			for (int conflictToken : leftNeighbour.conflictTokens) {
				victoryPointsByCriteria += conflictToken;
			}
			for (int conflictToken : rightNeighbour.conflictTokens) {
				victoryPointsByCriteria += conflictToken;
			}
			wonderBoard.playerProperty.victoryPoint += victoryPointsByCriteria;
		} else if (scientistsGuild) {
			AlternativeResource alternativeResource = new AlternativeResource();

			alternativeResource.names.add("scienceStar");
			alternativeResource.names.add("scienceCircle");
			alternativeResource.names.add("scienceWheel");
			wonderBoard.resourcesProduced.add(alternativeResource);
		} else {
			for (String color : criteriaColors) {
				int victoryPoint = (left ? getVictoryPointOfColour(wonderBoard.player.leftNeighbor, color) : 0)
						+ (right ? getVictoryPointOfColour(wonderBoard.player.rightNeighbor, color) : 0)
						+ (own ? getVictoryPointOfColour(wonderBoard.player, color) : 0);
				wonderBoard.playerProperty.victoryPoint += victoryPoint;
			}

		}
	}

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
