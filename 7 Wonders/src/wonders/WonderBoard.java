
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class WonderBoard implements Serializable {
	@Override
	public String toString() {
		return "WonderBoard [name=" + name + ", stagesBuilt=" + stagesBuilt + ", numberOfStages=" + numberOfStages
				+ ", side=" + side  + ", playerProperty=" + playerProperty + ", gamePlay="
				+ gamePlay + ", resourcesProduced=" + resourcesProduced + ", stages=" + stages + "]";
	}

	private static final long serialVersionUID = 1L;
	String name;
	Player player;
	int stagesBuilt;
	int numberOfStages;
	char side;
	public int identityIndex=0;
	public PlayerProperty playerProperty;
	public GamePlay gamePlay;
	ArrayList<Resource> resourcesProduced = new ArrayList<Resource>();
	ArrayList<Stage> stages = new ArrayList<Stage>();
	public ArrayList<Card> playedCardsList = new ArrayList<Card>();
	public ArrayList<Trade> bufferedTradeList =new ArrayList<Trade>();

	public WonderBoard(int index, char side) {
		this.side = side;
		if (index == 1) {
			this.name = "The Colossus of Rhodes";
			this.resourcesProduced.add(new Resource("ore", 1));

			if (side == 'A') {

				this.numberOfStages = 3;
				Stage firstStage = new Stage(1, 0, 3, 0);
				firstStage.resourcesRequired.add(new Resource("wood", 2));
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 0, 0, 2);
				secondStage.resourcesRequired.add(new Resource("clay", 3));
				this.stages.add(secondStage);

				Stage thirdStage = new Stage(3, 0, 7, 0);
				thirdStage.resourcesRequired.add(new Resource("ore", 4));
				this.stages.add(thirdStage);
			} else {

				this.numberOfStages = 2;
				Stage firstStage = new Stage(1, 3, 3, 1);
				firstStage.resourcesRequired.add(new Resource("stone", 3));
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 4, 4, 1);
				secondStage.resourcesRequired.add(new Resource("ore", 4));
				this.stages.add(secondStage);
			}
		} else if (index == 2) {

			this.name = "The Lighthouse of Alexandria";

			this.resourcesProduced.add(new Resource("glass", 1));

			if (this.side == 'A') {
				this.numberOfStages = 3;
				Stage firstStage = new Stage(1, 0, 3, 0);
				firstStage.resourcesRequired.add(new Resource("stone", 2));
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 0, 0, 0);
				AlternativeResource alt = new AlternativeResource();
				alt.names.add("clay");
				alt.names.add("wood");
				alt.names.add("stone");
				alt.names.add("ore");
				secondStage.resourcesProduced.add(alt);
				secondStage.resourcesRequired.add(new Resource("ore", 2));
				this.stages.add(secondStage);

				Stage thirdStage = new Stage(3, 0, 7, 0);
				thirdStage.resourcesRequired.add(new Resource("glass", 2));
				this.stages.add(thirdStage);

			} else {

				this.numberOfStages = 3;

				Stage firstStage = new Stage(1, 0, 0, 0);
				AlternativeResource alt = new AlternativeResource();
				alt.names.add("clay");
				alt.names.add("wood");
				alt.names.add("stone");
				alt.names.add("ore");
				firstStage.resourcesProduced.add(alt);
				firstStage.resourcesRequired.add(new Resource("clay", 2));
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 0, 0, 0);
				AlternativeResource alter = new AlternativeResource();
				alter.names.add("glass");
				alter.names.add("loom");
				alter.names.add("papyrus");
				secondStage.resourcesProduced.add(alter);
				secondStage.resourcesRequired.add(new Resource("wood", 2));
				this.stages.add(secondStage);

				Stage thirdStage = new Stage(3, 0, 7, 0);
				thirdStage.resourcesRequired.add(new Resource("stone", 3));
				this.stages.add(thirdStage);
			}
		} else if (index == 3) {
			this.name = "The Hanging Gardens of Babylon";
			this.resourcesProduced.add(new Resource("clay", 1));
			if (this.side == 'A') {
				this.numberOfStages = 3;
				Stage firstStage = new Stage(1, 0, 3, 0);
				firstStage.resourcesRequired.add(new Resource("clay", 2));
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 0, 0, 0);
				secondStage.resourcesRequired.add(new Resource("wood", 3));
				AlternativeResource alter = new AlternativeResource();
				alter.names.add("scienceStar");
				alter.names.add("scienceCircle");
				alter.names.add("scienceWheel");
				secondStage.resourcesProduced.add(alter);
				this.stages.add(secondStage);

				Stage thirdStage = new Stage(3, 0, 7, 0);
				thirdStage.resourcesRequired.add(new Resource("clay", 4));
				this.stages.add(thirdStage);

			} else {
				this.numberOfStages = 3;
				Stage firstStage = new Stage(1, 0, 3, 0);
				firstStage.resourcesRequired.add(new Resource("clay", 1));
				firstStage.resourcesRequired.add(new Resource("loom", 1));
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 0, 3, 0);
				secondStage.resourcesRequired.add(new Resource("wood", 2));
				secondStage.resourcesRequired.add(new Resource("glass", 1));
				secondStage.seventhCardUseAllowed = true;
				this.stages.add(secondStage);

				Stage thirdStage = new Stage(3, 0, 0, 0);
				thirdStage.resourcesRequired.add(new Resource("clay", 3));
				thirdStage.resourcesRequired.add(new Resource("papyrus", 1));
				AlternativeResource alter = new AlternativeResource();
				alter.names.add("scienceStar");
				alter.names.add("scienceWheel");
				alter.names.add("scienceCircle");
				thirdStage.resourcesProduced.add(alter);
				this.stages.add(thirdStage);
			}

		} else if (index == 4) {
			this.name = "The Statue of Zeus in Olympia";
			this.resourcesProduced.add(new Resource("wood", 1));
			if (side == 'A') {
				this.numberOfStages = 3;
				Stage firstStage = new Stage(1, 0, 3, 0);
				firstStage.resourcesRequired.add(new Resource("wood", 2));
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 0, 0, 0);
				secondStage.resourcesRequired.add(new Resource("stone", 2));
				secondStage.discardedCardUseAllowed = true;
				this.stages.add(secondStage);

				Stage thirdStage = new Stage(3, 0, 7, 0);
				thirdStage.resourcesRequired.add(new Resource("ore", 2));
				this.stages.add(thirdStage);

			} else {
				this.numberOfStages = 3;

				Stage firstStage = new Stage(1, 0, 0, 0);
				firstStage.resourcesRequired.add(new Resource("wood", 2));
				firstStage.specialRawMaterialTrade = true;
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 0, 5, 0);
				secondStage.resourcesRequired.add(new Resource("stone", 2));
				this.stages.add(secondStage);

				Stage thirdStage = new Stage(3, 0, 7, 0);
				thirdStage.resourcesRequired.add(new Resource("ore", 2));
				this.stages.add(thirdStage);

			}

		} else {
			this.name = "The Pyramids of Giza";
			this.resourcesProduced.add(new Resource("stone", 1));
			if (side == 'A') {
				this.numberOfStages = 3;
				Stage firstStage = new Stage(1, 0, 3, 0);
				firstStage.resourcesRequired.add(new Resource("stone", 2));
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 0, 5, 0);
				secondStage.resourcesRequired.add(new Resource("wood", 3));
				this.stages.add(secondStage);

				Stage thirdStage = new Stage(3, 0, 7, 0);
				thirdStage.resourcesRequired.add(new Resource("stone", 4));
				this.stages.add(thirdStage);

			} else {
				this.numberOfStages = 4;

				Stage firstStage = new Stage(1, 0, 3, 0);
				firstStage.resourcesRequired.add(new Resource("wood", 2));
				this.stages.add(firstStage);

				Stage secondStage = new Stage(2, 0, 5, 0);
				secondStage.resourcesRequired.add(new Resource("stone", 3));
				this.stages.add(secondStage);

				Stage thirdStage = new Stage(3, 0, 5, 0);
				thirdStage.resourcesRequired.add(new Resource("clay", 3));
				this.stages.add(thirdStage);

				Stage fourthStage = new Stage(4, 0, 7, 0);
				fourthStage.resourcesRequired.add(new Resource("stone", 4));
				fourthStage.resourcesRequired.add(new Resource("papyrus", 1));
				this.stages.add(fourthStage);
			}

		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getStagesBuilt() {
		return stagesBuilt;
	}

	public void setStagesBuilt(int stagesBuilt) {
		this.stagesBuilt = stagesBuilt;
	}

	public int getNumberOfStages() {
		return numberOfStages;
	}

	public void setNumberOfStages(int numberOfStages) {
		this.numberOfStages = numberOfStages;
	}

	public char getSide() {
		return side;
	}

	public void setSide(char side) {
		this.side = side;
	}

	

	public PlayerProperty getPlayerProperty() {
		return playerProperty;
	}

	public void setPlayerProperty(PlayerProperty playerProperty) {
		this.playerProperty = playerProperty;
	}

	public GamePlay getGamePlay() {
		return gamePlay;
	}

	public void setGamePlay(GamePlay gamePlay) {
		this.gamePlay = gamePlay;
	}

	public ArrayList<Resource> getResourcesProduced() {
		return resourcesProduced;
	}

	public void setResourcesProduced(ArrayList<Resource> resourcesProduced) {
		this.resourcesProduced = resourcesProduced;
	}

	public ArrayList<Stage> getStages() {
		return stages;
	}

	public void setStages(ArrayList<Stage> stages) {
		this.stages = stages;
	}

	public boolean nextStagePossible(ArrayList<Trade> tradeList) {
		Stage nextStage = stages.get(stagesBuilt + 1);

		if (nextStage.canPayResource(this, tradeList))
			return true;

		return false;
	}

	

	public void buildNextStage(Card card) {
		Stage nextStage = stages.get(stagesBuilt + 1);
		nextStage.build(this);

	}
	public void initializeResource() {
		
			Map<String, Resource> resourceMap = playerProperty.mapOfResourceToName;
			for (Resource resource : resourcesProduced) {
				if (resourceMap.containsKey(resource.name)) {
					resourceMap.get(resource.name).quantity += resource.quantity;
				} else {
					resourceMap.put(resource.name, resource);
				}
			}
		
	}

}
