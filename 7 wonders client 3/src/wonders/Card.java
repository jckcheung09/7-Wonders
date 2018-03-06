
package wonders;

import java.io.Serializable;
import java.util.*;

import javax.swing.JOptionPane;

public abstract class Card implements Serializable {
	// serialization needed for communicating with server
	private static final long serialVersionUID = 20L;
	// general attributes of card
	int age;
	int id;
	public String name;
	String type;
	public String color;
	
	int cost;
	int minimumPlayersRequired;
	// resource list required for playing this card
	ArrayList<Resource> resourcesRequired = new ArrayList<Resource>();
	// structure list required for playing this card
	ArrayList<String> structureRequired = new ArrayList<String>();
	// future structures available after playing this card
	ArrayList<String> futureStructures = new ArrayList<String>();
// two methods play card and print details of the card
	//is overridden in child classes of this class
	abstract public void playCard(WonderBoard wonderBoard);
	abstract public String printDetails();
	// if the structures required for this card is built already then 
	// allow player to play it as free
	public boolean iSFree(WonderBoard wonderBoard) {
		boolean isFree = false;
		for (String structuredRequired : structureRequired) {

			for (String cardName : wonderBoard.playerProperty.playedCardList) {
				if (cardName.equals(structuredRequired)) {
					isFree = true;
					break;
				}
			}
		}
		return isFree;
	}
	
	// if the cost of the card is greater than coins available then invalidate playing it
	
	public boolean canPayCost(WonderBoard wonderBoard) {
			
		return cost <= wonderBoard.playerProperty.coins;
	}
// pay cost of card
	public void payCost(WonderBoard wonderBoard) {
		// if can not pay cost then show alert 
		if (!canPayCost(wonderBoard)) {
			JOptionPane.showMessageDialog(null, "Insufficient cois");
			throw new RuntimeException("Insufficient coins");
		}
		else
			{
			// decrease coins of player as cost of the card
				wonderBoard.playerProperty.coins -= cost;
			}
	}

	public void discard(WonderBoard wonderBoard) {
		wonderBoard.playerProperty.coins += 3;
		wonderBoard.gamePlay.cardsDiscarded.add(this);
	}
	// checking validity of playing this card
	public boolean canPayResource(WonderBoard wonderBoard, List<Trade> tradeList) {
		
		// if trade list is invalid due to coins then show alert and invalidate
		if(!tradeCheck(wonderBoard, (ArrayList<Trade>) tradeList))return false;
		// get resource map of player
		Map<String, Resource> resourceMap = wonderBoard.playerProperty.mapOfResourceToName;
		// deficit resources after exploring own resource map
		List<Resource> deficitList = new ArrayList<Resource>();
		// clone resource list for avoiding mutation
		ArrayList <Resource>resourcesRequiredClone= new ArrayList<Resource>();
		resourcesRequiredClone=myClone(resourcesRequired);
		
		
		
		
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
			for(Trade trade: tradeList){
				if(deficitResource.name.equals(trade.resource.name)){
					deficitResource.quantity-=trade.resource.quantity;
					trade.isUsed=true;
					wonderBoard.bufferedTradeList.add(trade);
					if(deficitResource.quantity<=0)break;
				}
			}
			// after all techniques if still some quantity of deficit resource is positive then invalidate playing of this card
			if(deficitResource.quantity>0){
				
				return false;
				
			}
		}
		// refreshed the alternate resource to unused as their use is over for this turn
		for (Resource wonderBoardResource : wonderBoard.playerProperty.mapOfResourceToName.values()){
			if (wonderBoardResource instanceof AlternativeResource){
				AlternativeResource alternativeResource = (AlternativeResource) wonderBoardResource;
				alternativeResource.isUsed=false;
			}
		}
		return true;
	}
// get quantity of resources available from neighbor
	public int getResourceQuantityFromNeighbour(WonderBoard wonderBoard, Resource deficitResource) {
		int quantity = 0;
		for (Resource resource : wonderBoard.resourcesProduced) {
			if (deficitResource.name.equals(resource.name)) {
				quantity += resource.quantity;
			}
		}
		return quantity;
	}
	// cloning arrayList of resource to get rid of mutation problem
	private ArrayList<Resource> myClone(ArrayList<Resource> resourcesRequired) {
		ArrayList<Resource>newList = new ArrayList<Resource>();
		// copy details of each resource into new resource objects and push them to new list
		for(Resource resource: resourcesRequired){
			Resource newResource = new Resource();
			newResource.name = resource.name;
			newResource.quantity = resource.quantity;
			newList.add(newResource);
		}
		
		return newList;
	}
	
	 // overriding to string  method for printing the card details
	   
	@Override
	public String toString() {
		return "Card [age=" + age + ", id=" + id + ", name=" + name + ", type=" + type + ", color=" + color + ", cost="
				+ cost + ", minimumPlayersRequired=" + minimumPlayersRequired + ", resourcesRequired="
				+ resourcesRequired + ", structureRequired=" + structureRequired + ", futureStructures="
				+ futureStructures + "]";
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
