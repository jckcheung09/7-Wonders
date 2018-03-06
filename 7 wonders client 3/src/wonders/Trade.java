package wonders;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Trade implements Serializable {
	// serialization needed for communicating with server
	private static final long serialVersionUID = 22L;
	
	// resource to be traded
	Resource resource;
	// neighbor string indicating neighbor player
	String neighbor;
	// coins needed for the trade
	int coinsNeeded = 0;
	// indicator if used
	boolean isUsed = false;
// constructor using resource and neighbor string
	public Trade(Resource resource, String neighbor) {

		this.resource = resource;
		this.neighbor = neighbor;
	}
// getter of resource
	public Resource getResource() {
		return resource;
	}
// setter of resource
	public void setResource(Resource resource) {
		this.resource = resource;
	}
//	 getter of neighbor
	public String getNeighbor() {
		return neighbor;
	}
//  setter of neighbor
	public void setNeighbor(String neighbor) {
		this.neighbor = neighbor;
	}

	public boolean isTradePossible(WonderBoard wonderBoard, GamePlay game) {
		// special trade with 1 coin indicator
		boolean specialTradeAllowed = false;
		// available quantity from neighbor
		int availableQuantity = 0;
		// wonder board of neighbor
		WonderBoard neighborBoard;
		// if neighbor is selected as left
		if (neighbor.equals("left")) {
			// get wonder board of left neighbor
			neighborBoard = game.boards
					.get((wonderBoard.identityIndex - 1 + game.numberOfPlayers) % game.numberOfPlayers);
			// check if special trade of manufactured goods from left neighbor is allowed
			if (resource.name.equals("loom") || resource.name.equals("glass") || resource.name.equals("papyrus")) {
				specialTradeAllowed = wonderBoard.playerProperty.leftManufacturedGoodsTrade;
			} else {
				// check if special trade of raw materials from left neighbor is allowed
				specialTradeAllowed = wonderBoard.playerProperty.leftRawMaterialsTrade;
			}
			
		}// if neighbor is selected as right 
		else {
			// get wonder board of right neighbor
			neighborBoard = game.boards.get((wonderBoard.identityIndex + 1) % game.numberOfPlayers);
			// check if special trade of manufactured goods from right neighbor is allowed			
			if (resource.name.equals("loom") || resource.name.equals("glass") || resource.name.equals("papyrus")) {
				specialTradeAllowed = wonderBoard.playerProperty.rightManufacturedGoodsTrade;
			} else {
				// check if special trade of raw materials from left neighbor is allowed				
				specialTradeAllowed = wonderBoard.playerProperty.rightRawMaterialsTrade;
			}
		}
		if (specialTradeAllowed) {
			// if special trade allowed then needed coins are equal to quantity (per quantity 1 coin)
			coinsNeeded = resource.quantity;
		} else {
			// if special trade allowed then needed coins are  twice quantity (per quantity 2 coin)
			coinsNeeded = resource.quantity * 2;
		}
		// check if resource and quantity is available in neighbor board
		for (Resource resourceAvailable : neighborBoard.playerProperty.mapOfResourceToName.values()) {
			// if alternate resource then increment resources available
			if (resourceAvailable instanceof AlternativeResource) {
				AlternativeResource resourceToCheck = (AlternativeResource) resourceAvailable;
				if (resourceToCheck.names.contains(resource.name))
					availableQuantity++;
			} else if (resourceAvailable.name.equals(resource.name)) {
				// otherwise increase quantity of available resource from resource map
				availableQuantity += resourceAvailable.quantity;

			}

		}
// if available quantity is less than needed then show alert
		if (resource.quantity > availableQuantity) {
			System.out.println("The available quantity is: " + availableQuantity + "\n culprit resource: " + resource);
			JOptionPane.showMessageDialog(null, "Sorry resource not available from neighbor");
			return false;
		}
// if player does not have coins needed for this trade then show alert
		else if (coinsNeeded > wonderBoard.playerProperty.coins) {
			JOptionPane.showMessageDialog(null, "Sorry not enough coins to make this trade");
			return false;
		}

		return true;
	}
// get detailed text of this trade
	@Override
	public String toString() {
		return "Trade: " + resource + ", from=" + neighbor;
	}

}
