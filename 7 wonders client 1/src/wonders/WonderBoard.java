
package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class WonderBoard implements Serializable {
	
	// serialization needed for communicating with server
	private static final long serialVersionUID = 1L;
	// name of board
	String name;
	// player reference
	public Player player;
	// number of stages built
	int stagesBuilt;
	// total number of stages
	int numberOfStages;
	// side of the board
	char side;
	// player property of the player
	public PlayerProperty playerProperty ;
	// game play reference
	public GamePlay gamePlay;
	// list of resources produced
	ArrayList<Resource> resourcesProduced = new ArrayList<Resource>();
	// list of stages
	ArrayList<Stage> stages = new ArrayList<Stage>();
	// identity index of the board of the game
	public int identityIndex=0;
	// buffered trade list for transferring coins later after all players finished their moves
	public ArrayList<Trade> bufferedTradeList =new ArrayList<Trade>();
	// constructor using index number and side
	public WonderBoard(int index, char side) {
		this.playerProperty= new PlayerProperty();
		this.side = side;
		// create different wonder boards by indexes and sides
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
		initializePlayerPropertyResources();
	}
// put resources to resource map
	private void initializePlayerPropertyResources() {
		for(Resource resource: resourcesProduced){
			playerProperty.mapOfResourceToName.put(resource.name, resource);
		}
		
	}
// getter of name
	public String getName() {
		return name;
	}
// setter of name
	public void setName(String name) {
		this.name = name;
	}
	// getter of player
	public Player getPlayer() {
		return player;
	}
	// setter of player
	public void setPlayer(Player player) {
		this.player = player;
	}
	// getter of stages built
	public int getStagesBuilt() {
		return stagesBuilt;
	}
// setter of stages built
	public void setStagesBuilt(int stagesBuilt) {
		this.stagesBuilt = stagesBuilt;
	}
	// getter of number of stages
	public int getNumberOfStages() {
		return numberOfStages;
	}
	// setter of  number of stages
	public void setNumberOfStages(int numberOfStages) {
		this.numberOfStages = numberOfStages;
	}
// getter of side
	public char getSide() {
		return side;
	}
// setter of side
	public void setSide(char side) {
		this.side = side;
	}


	// getter of player property
	public PlayerProperty getPlayerProperty() {
		return playerProperty;
	}
	// setter of player property
	public void setPlayerProperty(PlayerProperty playerProperty) {
		this.playerProperty = playerProperty;
	}
	// getter of game play
	public GamePlay getGamePlay() {
		return gamePlay;
	}
	// setter of game play
	public void setGamePlay(GamePlay gamePlay) {
		this.gamePlay = gamePlay;
	}
	// getter of resources produced
	public ArrayList<Resource> getResourcesProduced() {
		return resourcesProduced;
	}
	// setter of resources produced
	public void setResourcesProduced(ArrayList<Resource> resourcesProduced) {
		this.resourcesProduced = resourcesProduced;
	}
	// getter of stages
	public ArrayList<Stage> getStages() {
		return stages;
	}
	// setter of stages
	public void setStages(ArrayList<Stage> stages) {
		this.stages = stages;
	}
// check if next stage is possible to built
	public boolean nextStagePossible(ArrayList<Trade> tradeList) {
	// get next stage
		Stage stage = stages.get(stagesBuilt);
	// if player can pay resource return true	
		if (stage.canPayResource(this, tradeList))
			return true;

		return false;
	}

	
// build next stage
	public void buildNextStage(Card card) {
	// get next stage
		Stage nextStage = stages.get(stagesBuilt);
	// build stage
		nextStage.build(this);

	}
	
	// put resources to resource map
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
	// get details as string
	@Override
	public String toString() {
		return "WonderBoard [name=" + name + ", stagesBuilt=" + stagesBuilt + ", numberOfStages=" + numberOfStages
				+ ", side=" + side + ", playerProperty=" + playerProperty + ", resourcesProduced=" + resourcesProduced + ", stages=" + stages + "]";
	}

}
