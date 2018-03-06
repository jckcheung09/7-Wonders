package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	ArrayList<Resource> resourcesRequired = new ArrayList<Resource>();
	int stageNumber ;
	int coinsEarned =0;
	int victoryPointsEarned =0;
	int millitaryPointsEarned=0;
	boolean freeCardUseAllowed = false;
	boolean seventhCardUseAllowed = false;
	boolean discardedCardUseAllowed =false;
	boolean specialRawMaterialTrade=false;
	boolean copyGuildCard=false;
	ArrayList<Resource> resourcesProduced = new ArrayList<Resource>();
	public Stage(int stageNumber, int coinsEarned, int victoryPointsEarned, int millitaryPointsEarned) {
	
		this.stageNumber = stageNumber;
		this.coinsEarned = coinsEarned;
		this.victoryPointsEarned = victoryPointsEarned;
	}
	public void build(WonderBoard wonderBoard) {
			wonderBoard.playerProperty.coins+=coinsEarned;
			wonderBoard.playerProperty.victoryPoint+=victoryPointsEarned;
			wonderBoard.stagesBuilt++;
			wonderBoard.playerProperty.militaryPoint+=millitaryPointsEarned;
			wonderBoard.playerProperty.freeCardUseAllowed= freeCardUseAllowed;
			wonderBoard.playerProperty.discardedCardUseAllowed=discardedCardUseAllowed;
			wonderBoard.playerProperty.seventhCardUseAllowed=seventhCardUseAllowed;
			if(resourcesProduced.size()>0){
				AlternativeResource alternativeResource = new AlternativeResource();
				alternativeResource.names.add("scienceWheel");
				alternativeResource.names.add("scienceStar");
				alternativeResource.names.add("scienceCircle");
				wonderBoard.resourcesProduced.add(alternativeResource);
						
			}
	}
	public boolean canPayResource(WonderBoard wonderBoard, ArrayList<Trade>tradeList) {
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
	@Override
	public String toString() {
		return "Stage [resourcesRequired=" + resourcesRequired + ", stageNumber=" + stageNumber + ", coinsEarned="
				+ coinsEarned + ", victoryPointsEarned=" + victoryPointsEarned + ", millitaryPointsEarned="
				+ millitaryPointsEarned + ", freeCardUseAllowed=" + freeCardUseAllowed + ", seventhCardUseAllowed="
				+ seventhCardUseAllowed + ", discardedCardUseAllowed=" + discardedCardUseAllowed
				+ ", specialRawMaterialTrade=" + specialRawMaterialTrade + ", copyGuildCard=" + copyGuildCard
				+ ", resourcesProduced=" + resourcesProduced + "]";
	}

	
	
}
