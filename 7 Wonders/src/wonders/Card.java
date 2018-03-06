
package wonders;

import java.io.Serializable;
import java.util.*;

public abstract class Card implements Serializable {

	private static final long serialVersionUID = 20L;
	int age;
	int id;
	String name;
	String type;
	String color;
	int cost;
	int minimumPlayersRequired;
	ArrayList<Resource> resourcesRequired = new ArrayList<Resource>();
	ArrayList<String> structureRequired = new ArrayList<String>();
	ArrayList<String> futureStructures = new ArrayList<String>();

	abstract public void playCard(WonderBoard wonderBoard);
	abstract public String printDetails();
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

	public boolean canPayCost(WonderBoard wonderBoard) {
		return cost <= wonderBoard.playerProperty.coins;
	}

	public void payCost(WonderBoard wonderBoard) {
		if (!canPayCost(wonderBoard)) {
			throw new RuntimeException("Insufficient coins");
		}
		wonderBoard.playerProperty.coins -= cost;
	}

	public void discard(WonderBoard wonderBoard) {
		wonderBoard.playerProperty.coins += 3;
		wonderBoard.gamePlay.cardsDiscarded.add(this);
	}

	public boolean canPayResource(WonderBoard wonderBoard, List<Trade> tradeListToBeSent) {
		Map<String, Resource> resourceMap = wonderBoard.playerProperty.mapOfResourceToName;
		List<Resource> deficitList = new ArrayList();
		for (Resource resource : resourcesRequired) {
			if (!(resource instanceof AlternativeResource)) {
				if (resourceMap.containsKey(resource.name)) {
					resource.quantity -= resourceMap.get(resource.name).quantity;
				}
				if (resource.quantity > 0) {
					deficitList.add(resource);
				}
			}
		}
		for (Resource deficitResource : deficitList) {
			for (Resource wonderBoardResource : wonderBoard.resourcesProduced) {
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
					if (deficitResource.quantity == 0) {
						break;
					}
				}
			}
		
			
			

			if (deficitResource.quantity > 0) {
				return false;
			}
		}
		return true;
	}

	private int getResourceQuantityFromNeighbour(WonderBoard wonderBoard, Resource deficitResource) {
		int quantity = 0;
		for (Resource resource : wonderBoard.resourcesProduced) {
			if (deficitResource.name.equals(resource.name)) {
				quantity += resource.quantity;
			}
		}
		return quantity;
	}

	@Override
	public String toString() {
		return "Card [age=" + age + ", id=" + id + ", name=" + name + ", type=" + type + ", color=" + color + ", cost="
				+ cost + ", minimumPlayersRequired=" + minimumPlayersRequired + ", resourcesRequired="
				+ resourcesRequired + ", structureRequired=" + structureRequired + ", futureStructures="
				+ futureStructures + "]";
	}
}
