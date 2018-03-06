package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class Stage implements Serializable {
	// serialization needed for communicating with server
	private static final long serialVersionUID = 6L;
	// list of resources required
	public ArrayList<Resource> resourcesRequired = new ArrayList<Resource>();
	// stage number
	int stageNumber;
	// coins earned
	int coinsEarned = 0;
	// victory points earned
	int victoryPointsEarned = 0;
	// military points earned
	int millitaryPointsEarned = 0;
	// if free card play is allowed
	boolean freeCardUseAllowed = false;
	// if seventh card play is allowed
	boolean seventhCardUseAllowed = false;
	// if playing any card from discarded card list is allowed
	boolean discardedCardUseAllowed = false;
	// if trade of any raw material is possible by 1 coin as price
	boolean specialRawMaterialTrade = false;
	// if copying guild card from neighbor is allowed
	boolean copyGuildCard = false;
	// list of resources produced
	ArrayList<Resource> resourcesProduced = new ArrayList<Resource>();

	// constructor using fields like stage Number,
	// coins Earned, victory points earned, military points earned
	public Stage(int stageNumber, int coinsEarned, int victoryPointsEarned, int millitaryPointsEarned) {

		this.stageNumber = stageNumber;
		this.coinsEarned = coinsEarned;
		this.victoryPointsEarned = victoryPointsEarned;
	}

	// build stage
	public void build(WonderBoard wonderBoard) {
		// increase coins if coin produced
		wonderBoard.playerProperty.coins += coinsEarned;
		// increase victory points
		wonderBoard.playerProperty.victoryPoint += victoryPointsEarned;
		// increase victory points by stage building
		wonderBoard.playerProperty.victoryPointByStageBuilding += victoryPointsEarned;
		// increment number of stages built
		if(wonderBoard.stagesBuilt<3)
		wonderBoard.stagesBuilt++;
		// increase military points 
		wonderBoard.playerProperty.militaryPoint += millitaryPointsEarned;
		// set boolean facilities true if true
		wonderBoard.playerProperty.freeCardUseAllowed = freeCardUseAllowed;
		wonderBoard.playerProperty.discardedCardUseAllowed = discardedCardUseAllowed;
		wonderBoard.playerProperty.seventhCardUseAllowed = seventhCardUseAllowed;
		// only one stage of one board produce 
		// alternate science resource like science star / science wheel/ science circle
		if (resourcesProduced.size() > 0) {
			AlternativeResource alternativeResource = new AlternativeResource();
			alternativeResource.names.add("scienceWheel");
			alternativeResource.names.add("scienceStar");
			alternativeResource.names.add("scienceCircle");
			// add the alternate resource to wonder board and resource map
			wonderBoard.resourcesProduced.add(alternativeResource);
			wonderBoard.playerProperty.mapOfResourceToName.put("" + Math.random(), alternativeResource);
		}
	}
// determine if player can pay the resources
	public boolean canPayResource(WonderBoard wonderBoard, List<Trade> tradeList) {
		// get resource map of player
		Map<String, Resource> resourceMap = wonderBoard.playerProperty.mapOfResourceToName;
		// deficit resources after exploring own resource map
		List<Resource> deficitList = new ArrayList<Resource>();
		// clone resource list for avoiding mutation
		ArrayList<Resource> resourcesRequiredClone = new ArrayList<Resource>();
		resourcesRequiredClone = myClone(resourcesRequired);
		
		
		// if trade list is invalid due to coins then show alert and invalidate
		if (!tradeCheck(wonderBoard, (ArrayList<Trade>) tradeList))
			return false;
		
		
		// for each resource of required resource list
		for (Resource resource : resourcesRequiredClone) {
			// if not alternate resource then decrease available quantity from it 
			// getting available quantity from resource map			
			if (!(resource instanceof AlternativeResource)) {
				if (resourceMap.containsKey(resource.name)) {
					resource.quantity -= resourceMap.get(resource.name).quantity;
				}
			// if available quantity is less than required then add the rest amount resource to deficit list								
				if (resource.quantity > 0) {
					deficitList.add(resource);
				}
			}
		}
		// for each resource of deficit list
		for (Resource deficitResource : deficitList) {
			// for each alternate resource of wonder board if it is in deficit list then decrease its amount and 
			// mark the resource as used as it is not allowed to use again			
			for (Resource wonderBoardResource : wonderBoard.playerProperty.mapOfResourceToName.values()) {
				
				if (wonderBoardResource instanceof AlternativeResource) {
					AlternativeResource alternativeResource = (AlternativeResource) wonderBoardResource;
					if (!alternativeResource.isUsed) {
						for (String alternativeResourceName : alternativeResource.names) {
							if (deficitResource.name.equals(alternativeResourceName)) {
								deficitResource.quantity--;
								alternativeResource.isUsed = true;
							}
						}
					}
					// if deficit quantity is fulfilled then break the loop for this deficit resource
					if (deficitResource.quantity == 0) {
						break;
					}
				}
			}
		}
		// for each resource of deficit list
		for (Resource deficitResource : deficitList) {
			// for each trade of trade list, decrease the quantity of deficit resource 
			// by trade resource quantity if their name matches
			// add the trade to buffered list as coin transaction is pending for this trade
			// mark the trade as used
			for (Trade trade : tradeList) {
				if (deficitResource.name.equals(trade.resource.name)) {
					deficitResource.quantity -= trade.resource.quantity;
					trade.isUsed = true;
					wonderBoard.bufferedTradeList.add(trade);
					if (deficitResource.quantity <= 0)
						break;
				}
			}	
			// after all techniques if still some quantity of deficit resource is positive then invalidate playing of this card			
			if (deficitResource.quantity > 0) {
				return false;
			}
		}
		// refreshed the alternate resource to unused as their use is over for this turn
		for (Resource wonderBoardResource : wonderBoard.playerProperty.mapOfResourceToName.values()) {
			if (wonderBoardResource instanceof AlternativeResource) {
				AlternativeResource alternativeResource = (AlternativeResource) wonderBoardResource;
				alternativeResource.isUsed = false;
			}
		}
		return true;
	}
	// cloning arrayList of resource to get rid of mutation problem
	private ArrayList<Resource> myClone(ArrayList<Resource> resourcesRequired) {
		ArrayList<Resource> newList = new ArrayList<Resource>();
		// copy details of each resource into new resource objects and push them to new list
		for (Resource resource : resourcesRequired) {
			Resource newResource = new Resource();
			newResource.name = resource.name;
			newResource.quantity = resource.quantity;
			newList.add(newResource);
		}
		return newList;
	}
// get text containing required resources of this stage
	public String requirementsText() {
		String text = "";
		for (Resource resource : resourcesRequired) {
			text += resource.toString() + "\n";

		}
		return text;

	}

	// check if the trades done are valid with coins available of player
	public boolean tradeCheck(WonderBoard wonderboard,ArrayList<Trade>tradeList){
		// calculate total coins needed by the player
		int totalCoins = 0;
		if(tradeList.size()==0)return true;
		for(Trade trade: tradeList){
			totalCoins+= trade.coinsNeeded;
		}
		// if total coins needed is greater than coins of the player then show alert
		if(totalCoins>wonderboard.playerProperty.coins){
			JOptionPane.showMessageDialog(null,"Insufficient coins for executing the trades");
			return false;
		}
	// if everything OK then validate the trades	
		return true;
	}

}
