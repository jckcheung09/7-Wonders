package wonders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
// age class
public class Age implements Serializable{
// serialization needed for communicating with server
	private static final long serialVersionUID =  5L;
// age number
	int age;
// total cards of age
	ArrayList<Card> cards = new ArrayList<Card>();
// player list
	ArrayList<Player> players;
// constructor
	public Age(int i) {
	// set age number
		this.age = i;
	// initialize cards and players
		initialize();
	}

	
	// initialize cards and players
	private void initialize() {
		// initialize age 1 cards
		if (age == 1) {
			int id = 1;
			RawMaterialsCard card = new RawMaterialsCard(id++, "Lumber Yard", 3, 0, false);
			card.producedResources.add(new Resource("wood", 1));
			cards.add(card);

			RawMaterialsCard lumber = new RawMaterialsCard(id++, "Lumber Yard", 4, 0, false);
			lumber.producedResources.add(new Resource("wood", 1));
			cards.add(lumber);

			RawMaterialsCard stone_pit = new RawMaterialsCard(id++, "Stone Pit", 3, 0, false);
			stone_pit.producedResources.add(new Resource("stone", 1));
			cards.add(stone_pit);

			RawMaterialsCard stone_pit2 = new RawMaterialsCard(id++, "Stone Pit", 5, 0, false);
			stone_pit2.producedResources.add(new Resource("stone", 1));
			cards.add(stone_pit2);

			RawMaterialsCard clay_pool = new RawMaterialsCard(id++, "Clay Pool", 3, 0, false);
			clay_pool.producedResources.add(new Resource("clay", 1));
			cards.add(clay_pool);

			RawMaterialsCard clay_pool2 = new RawMaterialsCard(id++, "Clay Pool", 5, 0, false);
			clay_pool2.producedResources.add(new Resource("clay", 1));
			cards.add(clay_pool2);

			RawMaterialsCard ore_vein = new RawMaterialsCard(id++, "Ore Vein", 3, 0, false);
			ore_vein.producedResources.add(new Resource("ore", 1));
			cards.add(ore_vein);

			RawMaterialsCard ore_vein2 = new RawMaterialsCard(id++, "Ore Vein", 4, 0, false);
			ore_vein2.producedResources.add(new Resource("ore", 1));
			cards.add(ore_vein2);

			RawMaterialsCard tree_farm = new RawMaterialsCard(id++, "Tree Farm", 6, 1, true);
			AlternativeResource treeResource = new AlternativeResource();			
			tree_farm.producedResources.add(new Resource("wood", 1));
			tree_farm.producedResources.add(new Resource("clay", 1));
			cards.add(tree_farm);

			RawMaterialsCard excavation = new RawMaterialsCard(id++, "Excavation", 4, 1, true);
			excavation.producedResources.add(new Resource("stone", 1));
			excavation.producedResources.add(new Resource("clay", 1));
			cards.add(excavation);
			

			RawMaterialsCard clay_pit = new RawMaterialsCard(id++, "Clay Pit", 3, 1, true);
			clay_pit.producedResources.add(new Resource("ore", 1));
			clay_pit.producedResources.add(new Resource("clay", 1));
			cards.add(clay_pit);

			RawMaterialsCard timber_yard = new RawMaterialsCard(id++, "Timber Yard", 3, 1, true);
			timber_yard.producedResources.add(new Resource("stone", 1));
			timber_yard.producedResources.add(new Resource("wood", 1));
			cards.add(timber_yard);

			RawMaterialsCard forest_cave = new RawMaterialsCard(id++, "Forest Cave", 5, 1, true);
			forest_cave.producedResources.add(new Resource("ore", 1));
			forest_cave.producedResources.add(new Resource("wood", 1));
			cards.add(forest_cave);

			RawMaterialsCard mine = new RawMaterialsCard(id++, "Mine", 6, 1, true);
			mine.producedResources.add(new Resource("ore", 1));
			mine.producedResources.add(new Resource("stone", 1));
			cards.add(mine);

			ManufacturedGoodsCard loom = new ManufacturedGoodsCard(id++, "Loom", 3);
			loom.manufacturedGoods.add(new Resource("loom", 1));
			cards.add(loom);

			ManufacturedGoodsCard loom2 = new ManufacturedGoodsCard(id++, "Loom", 6);
			loom2.manufacturedGoods.add(new Resource("loom", 1));
			cards.add(loom2);

			ManufacturedGoodsCard glassworks = new ManufacturedGoodsCard(id++, "Glassworks", 3);
			glassworks.manufacturedGoods.add(new Resource("glass", 1));
			cards.add(glassworks);

			ManufacturedGoodsCard glassworks2 = new ManufacturedGoodsCard(id++, "Glassworks", 6);
			glassworks2.manufacturedGoods.add(new Resource("glass", 1));
			cards.add(glassworks2);

			ManufacturedGoodsCard press = new ManufacturedGoodsCard(id++, "Press", 3);
			press.manufacturedGoods.add(new Resource("papyrus", 1));
			cards.add(press);

			ManufacturedGoodsCard press2 = new ManufacturedGoodsCard(id++, "Press", 6);
			press2.manufacturedGoods.add(new Resource("papyrus", 1));
			cards.add(press2);

			CivilianStructureCard pawnShop = new CivilianStructureCard(id++, "PawnShop", 4, 3);
			cards.add(pawnShop);

			cards.add(new CivilianStructureCard(id++, "PawnShop", 7, 3));

			CivilianStructureCard baths = new CivilianStructureCard(id++, "Baths", 3, 3);
			baths.resourcesRequired.add(new Resource("stone", 1));
			baths.futureStructures.add("Aqueduct");
			cards.add(baths);

			CivilianStructureCard baths2 = new CivilianStructureCard(id++, "Baths", 7, 3);
			baths2.resourcesRequired.add(new Resource("stone", 1));
			baths2.futureStructures.add("Aqueduct");
			cards.add(baths2);

			CivilianStructureCard altar = new CivilianStructureCard(id++, "Altar", 3, 2);
			altar.futureStructures.add("Temple");
			cards.add(altar);

			CivilianStructureCard altar2 = new CivilianStructureCard(id++, "Altar", 5, 2);
			altar2.futureStructures.add("Temple");
			cards.add(altar2);

			CivilianStructureCard theater = new CivilianStructureCard(id++, "Theater", 3, 2);
			theater.futureStructures.add("Statue");
			cards.add(theater);

			CivilianStructureCard theater2 = new CivilianStructureCard(id++, "Theater", 6, 2);
			theater2.futureStructures.add("Statue");
			cards.add(theater2);

			MilitaryStructureCard stockade = new MilitaryStructureCard(id++, "Stockade", 3, 1);
			stockade.resourcesRequired.add(new Resource("wood", 1));
			cards.add(stockade);

			MilitaryStructureCard stockade2 = new MilitaryStructureCard(id++, "Stockade", 7, 1);
			stockade2.resourcesRequired.add(new Resource("wood", 1));
			cards.add(stockade2);

			MilitaryStructureCard barracks = new MilitaryStructureCard(id++, "Barracks", 3, 1);
			barracks.resourcesRequired.add(new Resource("ore", 1));
			cards.add(barracks);

			MilitaryStructureCard barracks2 = new MilitaryStructureCard(id++, "Barracks", 5, 1);
			barracks2.resourcesRequired.add(new Resource("ore", 1));
			cards.add(barracks2);

			MilitaryStructureCard guardTower = new MilitaryStructureCard(id++, "Guard Tower", 3, 1);
			guardTower.resourcesRequired.add(new Resource("clay", 1));
			cards.add(guardTower);

			MilitaryStructureCard guardTower2 = new MilitaryStructureCard(id++, "Guard Tower", 4, 1);
			guardTower2.resourcesRequired.add(new Resource("clay", 1));
			cards.add(guardTower2);

			ScienceCard apothecary = new ScienceCard(id++, "Apothecary", 3, 1);
			apothecary.resourcesRequired.add(new Resource("loom", 1));
			apothecary.futureStructures.add("Stables");
			apothecary.futureStructures.add("Dispensary");
			cards.add(apothecary);

			ScienceCard apothecary2 = new ScienceCard(id++, "Apothecary", 5, 1);
			apothecary2.resourcesRequired.add(new Resource("loom", 1));
			apothecary2.futureStructures.add("Stables");
			apothecary2.futureStructures.add("Dispensary");
			cards.add(apothecary2);

			ScienceCard workshop = new ScienceCard(id++, "Workshop", 3, 2);
			workshop.resourcesRequired.add(new Resource("glass", 1));
			workshop.futureStructures.add("Archery Range");
			workshop.futureStructures.add("Laboratory");
			cards.add(workshop);

			ScienceCard workshop2 = new ScienceCard(id++, "Workshop", 7, 2);
			workshop2.resourcesRequired.add(new Resource("glass", 1));
			workshop2.futureStructures.add("Archery Range");
			workshop2.futureStructures.add("Laboratory");
			cards.add(workshop2);

			ScienceCard scriptorium = new ScienceCard(id++, "Scriptorium", 3, 3);
			scriptorium.resourcesRequired.add(new Resource("papyrus", 1));
			scriptorium.futureStructures.add("Court House");
			scriptorium.futureStructures.add("Library");
			cards.add(scriptorium);

			ScienceCard scriptorium2 = new ScienceCard(id++, "Scriptorium", 4, 3);
			scriptorium2.resourcesRequired.add(new Resource("papyrus", 1));
			scriptorium2.futureStructures.add("Court House");
			scriptorium2.futureStructures.add("Library");
			cards.add(scriptorium2);

			CommercialStructureCard tavern = new CommercialStructureCard(id++, "Tavern", 4, false, false, false);
			tavern.coinsProduced = 5;
			cards.add(tavern);

			CommercialStructureCard tavern2 = new CommercialStructureCard(id++, "Tavern", 5, false, false, false);
			tavern2.coinsProduced = 5;
			cards.add(tavern2);

			CommercialStructureCard tavern3 = new CommercialStructureCard(id++, "Tavern", 7, false, false, false);
			tavern3.coinsProduced = 5;
			cards.add(tavern3);

			CommercialStructureCard eastTrading = new CommercialStructureCard(id++, "East Trading Post", 3, false, true,
					false);
			eastTrading.rawMaterialsTrade = true;
			eastTrading.futureStructures.add("Forum");
			cards.add(eastTrading);

			CommercialStructureCard eastTrading2 = new CommercialStructureCard(id++, "East Trading Post", 7, false,
					true, false);
			eastTrading2.rawMaterialsTrade = true;
			eastTrading2.futureStructures.add("Forum");
			cards.add(eastTrading2);

			CommercialStructureCard westTrading = new CommercialStructureCard(id++, "West Trading Post", 3, true, false,
					false);
			westTrading.rawMaterialsTrade = true;
			westTrading.futureStructures.add("Forum");
			cards.add(westTrading);

			CommercialStructureCard westTrading2 = new CommercialStructureCard(id++, "West Trading Post", 7, true,
					false, false);
			westTrading2.rawMaterialsTrade = true;
			westTrading2.futureStructures.add("Forum");
			cards.add(westTrading2);

			CommercialStructureCard marketPlace = new CommercialStructureCard(id++, "Market Place", 3, true, true,
					false);
			marketPlace.manufacturedGoodsTrade = true;
			marketPlace.futureStructures.add("Caravansery");
			cards.add(marketPlace);

			CommercialStructureCard marketPlace2 = new CommercialStructureCard(id++, "Market Place", 6, true, true,
					false);
			marketPlace2.manufacturedGoodsTrade = true;
			marketPlace2.futureStructures.add("Caravansery");
			cards.add(marketPlace2);

		} else if (age == 2) {
			// initialize age 2 cards
			int id = 101;
			RawMaterialsCard sawmill = new RawMaterialsCard(id++, "Sawmill", 3, 1, false);
			sawmill.producedResources.add(new Resource("wood", 2));
			cards.add(sawmill);

			RawMaterialsCard sawmill2 = new RawMaterialsCard(id++, "Sawmill", 4, 1, false);
			sawmill2.producedResources.add(new Resource("wood", 2));
			cards.add(sawmill2);

			RawMaterialsCard quarry = new RawMaterialsCard(id++, "Quarry", 3, 1, false);
			quarry.producedResources.add(new Resource("stone", 2));
			cards.add(quarry);

			RawMaterialsCard quarry2 = new RawMaterialsCard(id++, "Quarry", 4, 1, false);
			quarry2.producedResources.add(new Resource("stone", 2));
			cards.add(quarry2);

			RawMaterialsCard brick_yard = new RawMaterialsCard(id++, "Brick Yard", 3, 1, false);
			brick_yard.producedResources.add(new Resource("clay", 2));
			cards.add(brick_yard);

			RawMaterialsCard brick_yard2 = new RawMaterialsCard(id++, "Brick Yard", 4, 1, false);
			brick_yard2.producedResources.add(new Resource("clay", 2));
			cards.add(brick_yard2);

			RawMaterialsCard foundry = new RawMaterialsCard(id++, "Foundry", 3, 1, false);
			foundry.producedResources.add(new Resource("ore", 2));
			cards.add(foundry);

			RawMaterialsCard foundry2 = new RawMaterialsCard(id++, "Foundry", 4, 1, false);
			foundry2.producedResources.add(new Resource("ore", 2));
			cards.add(foundry2);

			ManufacturedGoodsCard loom = new ManufacturedGoodsCard(id++, "Loom", 3);
			loom.manufacturedGoods.add(new Resource("loom", 1));
			cards.add(loom);

			ManufacturedGoodsCard loom2 = new ManufacturedGoodsCard(id++, "Loom", 5);
			loom2.manufacturedGoods.add(new Resource("loom", 1));
			cards.add(loom2);

			ManufacturedGoodsCard glassworks = new ManufacturedGoodsCard(id++, "Glassworks", 3);
			glassworks.manufacturedGoods.add(new Resource("glass", 1));
			cards.add(glassworks);

			ManufacturedGoodsCard glassworks2 = new ManufacturedGoodsCard(id++, "Glassworks", 5);
			glassworks2.manufacturedGoods.add(new Resource("glass", 1));
			cards.add(glassworks2);

			ManufacturedGoodsCard press = new ManufacturedGoodsCard(id++, "Press", 3);
			press.manufacturedGoods.add(new Resource("papyrus", 1));
			cards.add(press);

			ManufacturedGoodsCard press2 = new ManufacturedGoodsCard(id++, "Press", 5);
			press2.manufacturedGoods.add(new Resource("papyrus", 1));
			cards.add(press2);

			CivilianStructureCard aqueduct = new CivilianStructureCard(id++, "Aqueduct", 3, 5);
			aqueduct.structureRequired.add("Baths");
			aqueduct.resourcesRequired.add(new Resource("stone", 3));
			cards.add(aqueduct);

			CivilianStructureCard aqueduct2 = new CivilianStructureCard(id++, "Aqueduct", 7, 5);
			aqueduct2.structureRequired.add("Baths");
			aqueduct2.resourcesRequired.add(new Resource("stone", 3));
			cards.add(aqueduct2);

			CivilianStructureCard temple = new CivilianStructureCard(id++, "Temple", 3, 3);
			temple.structureRequired.add("Altar");
			temple.resourcesRequired.add(new Resource("wood", 1));
			temple.resourcesRequired.add(new Resource("clay", 1));
			temple.resourcesRequired.add(new Resource("glass", 1));
			temple.futureStructures.add("Pantheon");
			cards.add(temple);

			CivilianStructureCard temple2 = new CivilianStructureCard(id++, "Temple", 6, 3);
			temple2.structureRequired.add("Altar");
			temple2.resourcesRequired.add(new Resource("wood", 1));
			temple2.resourcesRequired.add(new Resource("clay", 1));
			temple2.resourcesRequired.add(new Resource("glass", 1));
			temple2.futureStructures.add("Pantheon");
			cards.add(temple2);

			CivilianStructureCard statue = new CivilianStructureCard(id++, "Statue", 3, 4);
			statue.structureRequired.add("Theater");
			statue.resourcesRequired.add(new Resource("wood", 1));
			statue.resourcesRequired.add(new Resource("ore", 2));
			statue.futureStructures.add("Gardens");
			cards.add(statue);

			CivilianStructureCard statue2 = new CivilianStructureCard(id++, "Statue", 7, 4);
			statue2.structureRequired.add("Theater");
			statue2.resourcesRequired.add(new Resource("wood", 1));
			statue2.resourcesRequired.add(new Resource("ore", 2));
			statue2.futureStructures.add("Gardens");
			cards.add(statue2);

			MilitaryStructureCard walls = new MilitaryStructureCard(id++, "Walls", 3, 2);
			walls.resourcesRequired.add(new Resource("stone", 3));
			walls.futureStructures.add("Fortifications");
			cards.add(walls);

			MilitaryStructureCard walls2 = new MilitaryStructureCard(id++, "Walls", 7, 2);
			walls2.resourcesRequired.add(new Resource("stone", 3));
			walls2.futureStructures.add("Fortifications");
			cards.add(walls2);

			MilitaryStructureCard trainingGround = new MilitaryStructureCard(id++, "Training Ground", 4, 2);
			trainingGround.resourcesRequired.add(new Resource("wood", 1));
			trainingGround.resourcesRequired.add(new Resource("ore", 2));
			trainingGround.futureStructures.add("Circus");
			cards.add(trainingGround);

			MilitaryStructureCard trainingGround2 = new MilitaryStructureCard(id++, "Training Ground", 6, 2);
			trainingGround2.resourcesRequired.add(new Resource("wood", 1));
			trainingGround2.resourcesRequired.add(new Resource("ore", 2));
			trainingGround2.futureStructures.add("Circus");
			cards.add(trainingGround2);

			MilitaryStructureCard trainingGround3 = new MilitaryStructureCard(id++, "Training Ground", 7, 2);
			trainingGround3.resourcesRequired.add(new Resource("wood", 1));
			trainingGround3.resourcesRequired.add(new Resource("ore", 2));
			trainingGround3.futureStructures.add("Circus");
			cards.add(trainingGround3);

			MilitaryStructureCard stables = new MilitaryStructureCard(id++, "Stables", 3, 2);
			stables.resourcesRequired.add(new Resource("wood", 1));
			stables.resourcesRequired.add(new Resource("ore", 1));
			stables.resourcesRequired.add(new Resource("clay", 1));
			stables.structureRequired.add("Apothecary");
			cards.add(stables);

			MilitaryStructureCard stables2 = new MilitaryStructureCard(id++, "Stables", 5, 2);
			stables2.resourcesRequired.add(new Resource("wood", 1));
			stables2.resourcesRequired.add(new Resource("ore", 1));
			stables2.resourcesRequired.add(new Resource("clay", 1));
			stables2.structureRequired.add("Apothecary");
			cards.add(stables2);

			MilitaryStructureCard archeryRange = new MilitaryStructureCard(id++, "Archery Range", 3, 2);
			archeryRange.resourcesRequired.add(new Resource("wood", 2));
			archeryRange.resourcesRequired.add(new Resource("ore", 1));
			archeryRange.structureRequired.add("Workshop");
			cards.add(archeryRange);

			MilitaryStructureCard archeryRange2 = new MilitaryStructureCard(id++, "Archery Range", 6, 2);
			archeryRange2.resourcesRequired.add(new Resource("wood", 2));
			archeryRange2.resourcesRequired.add(new Resource("ore", 1));
			archeryRange2.structureRequired.add("Workshop");
			cards.add(archeryRange2);

			CivilianStructureCard courtHouse = new CivilianStructureCard(id++, "Court House", 3, 4);
			courtHouse.structureRequired.add("Scriptorium");
			courtHouse.resourcesRequired.add(new Resource("clay", 2));
			courtHouse.resourcesRequired.add(new Resource("loom", 1));
			cards.add(courtHouse);

			CivilianStructureCard courtHouse2 = new CivilianStructureCard(id++, "Court House", 5, 4);
			courtHouse2.structureRequired.add("Scriptorium");
			courtHouse2.resourcesRequired.add(new Resource("clay", 2));
			courtHouse2.resourcesRequired.add(new Resource("loom", 1));
			cards.add(courtHouse2);

			ScienceCard dispensary = new ScienceCard(id++, "Dispensary", 3, 1);
			dispensary.resourcesRequired.add(new Resource("ore", 2));
			dispensary.resourcesRequired.add(new Resource("glass", 1));
			dispensary.futureStructures.add("Lodge");
			dispensary.futureStructures.add("Arena");
			dispensary.structureRequired.add("Apothecary");
			cards.add(dispensary);

			ScienceCard dispensary2 = new ScienceCard(id++, "Dispensary", 4, 1);
			dispensary2.resourcesRequired.add(new Resource("ore", 2));
			dispensary2.resourcesRequired.add(new Resource("glass", 1));
			dispensary2.futureStructures.add("Lodge");
			dispensary2.futureStructures.add("Arena");
			dispensary2.structureRequired.add("Apothecary");
			cards.add(dispensary2);

			ScienceCard laboratory = new ScienceCard(id++, "Laboratory", 3, 2);
			laboratory.resourcesRequired.add(new Resource("clay", 2));
			laboratory.resourcesRequired.add(new Resource("papyrus", 1));
			laboratory.futureStructures.add("Observatory");
			laboratory.futureStructures.add("Siege Workshop");
			laboratory.structureRequired.add("Workshop");
			cards.add(laboratory);

			ScienceCard laboratory2 = new ScienceCard(id++, "Laboratory", 5, 2);
			laboratory2.resourcesRequired.add(new Resource("clay", 2));
			laboratory2.resourcesRequired.add(new Resource("papyrus", 1));
			laboratory2.futureStructures.add("Observatory");
			laboratory2.futureStructures.add("Siege Workshop");
			laboratory2.structureRequired.add("Workshop");
			cards.add(laboratory2);

			ScienceCard library = new ScienceCard(id++, "Library", 3, 3);
			library.resourcesRequired.add(new Resource("stone", 2));
			library.resourcesRequired.add(new Resource("loom", 1));
			library.futureStructures.add("University");
			library.futureStructures.add("Senate");
			library.structureRequired.add("Scriptorium");
			cards.add(library);

			ScienceCard library2 = new ScienceCard(id++, "Library", 6, 3);
			library2.resourcesRequired.add(new Resource("stone", 2));
			library2.resourcesRequired.add(new Resource("loom", 1));
			library2.futureStructures.add("University");
			library2.futureStructures.add("Senate");
			library2.structureRequired.add("Scriptorium");
			cards.add(library2);

			ScienceCard school = new ScienceCard(id++, "School", 3, 3);
			school.resourcesRequired.add(new Resource("wood", 1));
			school.resourcesRequired.add(new Resource("papyrus", 1));
			school.futureStructures.add("Study");
			school.futureStructures.add("Academy");
			cards.add(school);

			ScienceCard school2 = new ScienceCard(id++, "School", 7, 3);
			school2.resourcesRequired.add(new Resource("wood", 1));
			school2.resourcesRequired.add(new Resource("papyrus", 1));
			school2.futureStructures.add("Study");
			school2.futureStructures.add("Academy");
			cards.add(school2);

			CommercialStructureCard forum = new CommercialStructureCard(id++, "Forum", 3, false, false, false);
			forum.anyManufacturedGood = true;
			forum.resourcesRequired.add(new Resource("clay", 2));
			forum.structureRequired.add("East Trading Post");
			forum.structureRequired.add("West Trading Post");
			forum.futureStructures.add("Haven");
			cards.add(forum);

			CommercialStructureCard forum2 = new CommercialStructureCard(id++, "Forum", 6, false, false, false);
			forum2.anyManufacturedGood = true;
			forum2.resourcesRequired.add(new Resource("clay", 2));
			forum2.structureRequired.add("East Trading Post");
			forum2.structureRequired.add("West Trading Post");
			forum2.futureStructures.add("Haven");
			cards.add(forum2);

			CommercialStructureCard forum3 = new CommercialStructureCard(id++, "Forum", 7, false, false, false);
			forum3.anyManufacturedGood = true;
			forum3.resourcesRequired.add(new Resource("clay", 2));
			forum3.structureRequired.add("East Trading Post");
			forum3.structureRequired.add("West Trading Post");
			forum3.futureStructures.add("Haven");
			cards.add(forum3);

			CommercialStructureCard caravansery = new CommercialStructureCard(id++, "Caravansery", 3, false, false,
					false);
			caravansery.anyRawMaterial = true;
			caravansery.resourcesRequired.add(new Resource("wood", 2));
			caravansery.structureRequired.add("Market Place");
			caravansery.futureStructures.add("Light House");
			cards.add(caravansery);

			CommercialStructureCard caravansery2 = new CommercialStructureCard(id++, "Caravansery", 5, false, false,
					false);
			caravansery2.anyRawMaterial = true;
			caravansery2.resourcesRequired.add(new Resource("wood", 2));
			caravansery2.structureRequired.add("Market Place");
			caravansery2.futureStructures.add("Light House");
			cards.add(caravansery2);

			CommercialStructureCard caravansery3 = new CommercialStructureCard(id++, "Caravansery", 6, false, false,
					false);
			caravansery3.anyRawMaterial = true;
			caravansery3.resourcesRequired.add(new Resource("wood", 2));
			caravansery3.structureRequired.add("Market Place");
			caravansery3.futureStructures.add("Light House");
			cards.add(caravansery3);

			CommercialStructureCard vineyard = new CommercialStructureCard(id++, "Vineyard", 3, true, true, true);
			vineyard.criteriaColors.add("brown");
			vineyard.coinsProducedByCriteria = 1;
			cards.add(vineyard);

			CommercialStructureCard vineyard2 = new CommercialStructureCard(id++, "Vineyard", 6, true, true, true);
			vineyard2.criteriaColors.add("brown");
			vineyard2.coinsProducedByCriteria = 1;
			cards.add(vineyard2);

			CommercialStructureCard bazar = new CommercialStructureCard(id++, "Bazar", 4, true, true, true);
			bazar.criteriaColors.add("grey");
			bazar.coinsProducedByCriteria = 2;
			cards.add(bazar);

			CommercialStructureCard bazar2 = new CommercialStructureCard(id++, "Bazar", 7, true, true, true);
			bazar2.criteriaColors.add("grey");
			bazar2.coinsProducedByCriteria = 2;
			cards.add(bazar2);

		} else {
			// initialize age 3 cards
			int id = 201;
			CivilianStructureCard pantheon = new CivilianStructureCard(id++, "Pantheon", 3, 7);
			pantheon.structureRequired.add("Temple");
			pantheon.resourcesRequired.add(new Resource("ore", 1));
			pantheon.resourcesRequired.add(new Resource("clay", 2));
			pantheon.resourcesRequired.add(new Resource("glass", 1));
			pantheon.resourcesRequired.add(new Resource("loom", 1));
			pantheon.resourcesRequired.add(new Resource("papyrus", 1));
			cards.add(pantheon);

			CivilianStructureCard pantheon2 = new CivilianStructureCard(id++, "Pantheon", 6, 7);
			pantheon2.structureRequired.add("Temple");
			pantheon2.resourcesRequired.add(new Resource("ore", 1));
			pantheon2.resourcesRequired.add(new Resource("clay", 2));
			pantheon2.resourcesRequired.add(new Resource("glass", 1));
			pantheon2.resourcesRequired.add(new Resource("loom", 1));
			pantheon2.resourcesRequired.add(new Resource("papyrus", 1));
			cards.add(pantheon2);

			CivilianStructureCard gardens = new CivilianStructureCard(id++, "Gardens", 3, 5);
			gardens.structureRequired.add("Statue");
			gardens.resourcesRequired.add(new Resource("wood", 1));
			gardens.resourcesRequired.add(new Resource("clay", 2));
			cards.add(gardens);

			CivilianStructureCard gardens2 = new CivilianStructureCard(id++, "Gardens", 4, 5);
			gardens2.structureRequired.add("Statue");
			gardens2.resourcesRequired.add(new Resource("wood", 1));
			gardens2.resourcesRequired.add(new Resource("clay", 2));
			cards.add(gardens2);

			CivilianStructureCard townhall = new CivilianStructureCard(id++, "Townhall", 3, 6);
			townhall.resourcesRequired.add(new Resource("glass", 1));
			townhall.resourcesRequired.add(new Resource("ore", 1));
			townhall.resourcesRequired.add(new Resource("stone", 2));
			cards.add(townhall);

			CivilianStructureCard townhall2 = new CivilianStructureCard(id++, "Townhall", 5, 6);
			townhall2.resourcesRequired.add(new Resource("glass", 1));
			townhall2.resourcesRequired.add(new Resource("ore", 1));
			townhall2.resourcesRequired.add(new Resource("stone", 2));
			cards.add(townhall2);

			CivilianStructureCard townhall3 = new CivilianStructureCard(id++, "Townhall", 6, 6);
			townhall3.resourcesRequired.add(new Resource("glass", 1));
			townhall3.resourcesRequired.add(new Resource("ore", 1));
			townhall3.resourcesRequired.add(new Resource("stone", 2));
			cards.add(townhall3);

			CivilianStructureCard palace = new CivilianStructureCard(id++, "Palace", 3, 8);
			palace.resourcesRequired.add(new Resource("ore", 1));
			palace.resourcesRequired.add(new Resource("clay", 1));
			palace.resourcesRequired.add(new Resource("wood", 1));
			palace.resourcesRequired.add(new Resource("stone", 1));
			palace.resourcesRequired.add(new Resource("glass", 1));
			palace.resourcesRequired.add(new Resource("loom", 1));
			palace.resourcesRequired.add(new Resource("papyrus", 1));
			cards.add(palace);

			CivilianStructureCard palace2 = new CivilianStructureCard(id++, "Palace", 7, 8);
			palace2.resourcesRequired.add(new Resource("ore", 1));
			palace2.resourcesRequired.add(new Resource("clay", 1));
			palace2.resourcesRequired.add(new Resource("wood", 1));
			palace2.resourcesRequired.add(new Resource("stone", 1));
			palace2.resourcesRequired.add(new Resource("glass", 1));
			palace2.resourcesRequired.add(new Resource("loom", 1));
			palace2.resourcesRequired.add(new Resource("papyrus", 1));
			cards.add(palace2);

			MilitaryStructureCard fortifications = new MilitaryStructureCard(id++, "Fortifications", 3, 3);
			fortifications.resourcesRequired.add(new Resource("stone", 1));
			fortifications.resourcesRequired.add(new Resource("ore", 3));
			fortifications.structureRequired.add("Walls");
			cards.add(fortifications);

			MilitaryStructureCard fortifications2 = new MilitaryStructureCard(id++, "Fortifications", 7, 3);
			fortifications2.resourcesRequired.add(new Resource("stone", 1));
			fortifications2.resourcesRequired.add(new Resource("ore", 3));
			fortifications2.structureRequired.add("Walls");
			cards.add(fortifications2);

			MilitaryStructureCard circus = new MilitaryStructureCard(id++, "Circus", 4, 3);
			circus.resourcesRequired.add(new Resource("stone", 3));
			circus.resourcesRequired.add(new Resource("ore", 1));
			circus.structureRequired.add("Training Ground");
			cards.add(circus);

			MilitaryStructureCard circus2 = new MilitaryStructureCard(id++, "Circus", 5, 3);
			circus2.resourcesRequired.add(new Resource("stone", 3));
			circus2.resourcesRequired.add(new Resource("ore", 1));
			circus2.structureRequired.add("Training Ground");
			cards.add(circus2);

			MilitaryStructureCard circus3 = new MilitaryStructureCard(id++, "Circus", 6, 3);
			circus3.resourcesRequired.add(new Resource("stone", 3));
			circus3.resourcesRequired.add(new Resource("ore", 1));
			circus3.structureRequired.add("Training Ground");
			cards.add(circus3);

			MilitaryStructureCard arsenal = new MilitaryStructureCard(id++, "Arsenal", 3, 3);
			arsenal.resourcesRequired.add(new Resource("wood", 2));
			arsenal.resourcesRequired.add(new Resource("ore", 1));
			arsenal.resourcesRequired.add(new Resource("loom", 1));
			cards.add(arsenal);

			MilitaryStructureCard arsenal2 = new MilitaryStructureCard(id++, "Arsenal", 4, 3);
			arsenal2.resourcesRequired.add(new Resource("wood", 2));
			arsenal2.resourcesRequired.add(new Resource("ore", 1));
			arsenal2.resourcesRequired.add(new Resource("loom", 1));
			cards.add(arsenal2);

			MilitaryStructureCard arsenal3 = new MilitaryStructureCard(id++, "Arsenal", 7, 3);
			arsenal3.resourcesRequired.add(new Resource("wood", 2));
			arsenal3.resourcesRequired.add(new Resource("ore", 1));
			arsenal3.resourcesRequired.add(new Resource("loom", 1));
			cards.add(arsenal3);

			MilitaryStructureCard siege = new MilitaryStructureCard(id++, "Siege Workshop", 3, 3);
			siege.resourcesRequired.add(new Resource("wood", 1));
			siege.resourcesRequired.add(new Resource("clay", 3));
			siege.structureRequired.add("Laboratory");
			cards.add(siege);

			MilitaryStructureCard siege2 = new MilitaryStructureCard(id++, "Siege Workshop", 5, 3);
			siege2.resourcesRequired.add(new Resource("wood", 1));
			siege2.resourcesRequired.add(new Resource("clay", 3));
			siege2.structureRequired.add("Laboratory");
			cards.add(siege2);

			CivilianStructureCard senate = new CivilianStructureCard(id++, "Senate", 3, 6);
			senate.resourcesRequired.add(new Resource("wood", 2));
			senate.resourcesRequired.add(new Resource("ore", 1));
			senate.resourcesRequired.add(new Resource("stone", 1));
			senate.structureRequired.add("Library");
			cards.add(senate);

			CivilianStructureCard senate2 = new CivilianStructureCard(id++, "Senate", 5, 6);
			senate2.resourcesRequired.add(new Resource("wood", 2));
			senate2.resourcesRequired.add(new Resource("ore", 1));
			senate2.resourcesRequired.add(new Resource("stone", 1));
			senate2.structureRequired.add("Library");
			cards.add(senate2);

			ScienceCard lodge = new ScienceCard(id++, "Lodge", 3, 1);
			lodge.resourcesRequired.add(new Resource("clay", 2));
			lodge.resourcesRequired.add(new Resource("loom", 1));
			lodge.resourcesRequired.add(new Resource("papyrus", 1));
			lodge.structureRequired.add("Dispensary");
			cards.add(lodge);

			ScienceCard lodge2 = new ScienceCard(id++, "Lodge", 6, 1);
			lodge2.resourcesRequired.add(new Resource("clay", 2));
			lodge2.resourcesRequired.add(new Resource("loom", 1));
			lodge2.resourcesRequired.add(new Resource("papyrus", 1));
			lodge2.structureRequired.add("Dispensary");
			cards.add(lodge2);

			ScienceCard observatory = new ScienceCard(id++, "Observatory", 3, 2);
			observatory.resourcesRequired.add(new Resource("ore", 2));
			observatory.resourcesRequired.add(new Resource("loom", 1));
			observatory.resourcesRequired.add(new Resource("glass", 1));
			observatory.structureRequired.add("Laboratory");
			cards.add(observatory);

			ScienceCard observatory2 = new ScienceCard(id++, "Observatory", 7, 2);
			observatory2.resourcesRequired.add(new Resource("ore", 2));
			observatory2.resourcesRequired.add(new Resource("loom", 1));
			observatory2.resourcesRequired.add(new Resource("glass", 1));
			observatory2.structureRequired.add("Laboratory");
			cards.add(observatory2);

			ScienceCard university = new ScienceCard(id++, "University", 3, 3);
			university.resourcesRequired.add(new Resource("wood", 2));
			university.resourcesRequired.add(new Resource("papyrus", 1));
			university.resourcesRequired.add(new Resource("glass", 1));
			university.structureRequired.add("Library");
			cards.add(university);

			ScienceCard university2 = new ScienceCard(id++, "University", 4, 3);
			university2.resourcesRequired.add(new Resource("wood", 2));
			university2.resourcesRequired.add(new Resource("papyrus", 1));
			university2.resourcesRequired.add(new Resource("glass", 1));
			university2.structureRequired.add("Library");
			cards.add(university2);

			ScienceCard academy = new ScienceCard(id++, "Academy", 4, 1);
			academy.resourcesRequired.add(new Resource("stone", 3));
			academy.resourcesRequired.add(new Resource("glass", 1));
			academy.structureRequired.add("School");
			cards.add(academy);

			ScienceCard academy2 = new ScienceCard(id++, "Academy", 7, 1);
			academy2.resourcesRequired.add(new Resource("stone", 3));
			academy2.resourcesRequired.add(new Resource("glass", 1));
			academy2.structureRequired.add("School");
			cards.add(academy2);

			ScienceCard study = new ScienceCard(id++, "Study", 3, 2);
			study.resourcesRequired.add(new Resource("wood", 1));
			study.resourcesRequired.add(new Resource("papyrus", 1));
			study.resourcesRequired.add(new Resource("loom", 1));
			study.structureRequired.add("School");
			cards.add(study);

			ScienceCard study2 = new ScienceCard(id++, "Study", 5, 2);
			study2.resourcesRequired.add(new Resource("wood", 1));
			study2.resourcesRequired.add(new Resource("papyrus", 1));
			study2.resourcesRequired.add(new Resource("loom", 1));
			study2.structureRequired.add("School");
			cards.add(study2);

			GuildCard workersGuild = new GuildCard(id++, "Workers Guild", true, true, false, 1);
			workersGuild.criteriaColors.add("brown");
			workersGuild.resourcesRequired.add(new Resource("ore", 2));
			workersGuild.resourcesRequired.add(new Resource("stone", 1));
			workersGuild.resourcesRequired.add(new Resource("wood", 1));
			workersGuild.resourcesRequired.add(new Resource("clay", 1));
			cards.add(workersGuild);

			GuildCard craftsmensGuild = new GuildCard(id++, "Craftsmens Guild", true, true, false, 2);
			craftsmensGuild.criteriaColors.add("grey");
			craftsmensGuild.resourcesRequired.add(new Resource("ore", 2));
			craftsmensGuild.resourcesRequired.add(new Resource("stone", 2));
			cards.add(craftsmensGuild);

			GuildCard tradersGuild = new GuildCard(id++, "Traders Guild", true, true, false, 1);
			tradersGuild.criteriaColors.add("yellow");
			tradersGuild.resourcesRequired.add(new Resource("loom", 1));
			tradersGuild.resourcesRequired.add(new Resource("papyrus", 2));
			tradersGuild.resourcesRequired.add(new Resource("glass", 2));
			cards.add(tradersGuild);

			GuildCard philosophersGuild = new GuildCard(id++, "Philosophers Guild", true, true, false, 1);
			philosophersGuild.criteriaColors.add("green");
			philosophersGuild.resourcesRequired.add(new Resource("loom", 1));
			philosophersGuild.resourcesRequired.add(new Resource("papyrus", 1));
			philosophersGuild.resourcesRequired.add(new Resource("clay", 3));
			cards.add(philosophersGuild);

			GuildCard spiesGuild = new GuildCard(id++, "Spies Guild", true, true, false, 1);
			spiesGuild.criteriaColors.add("red");
			spiesGuild.resourcesRequired.add(new Resource("glass", 1));
			spiesGuild.resourcesRequired.add(new Resource("clay", 3));
			cards.add(spiesGuild);

			GuildCard strategistsGuild = new GuildCard(id++, "Strategists Guild", true, true, false, 1);
			strategistsGuild.criteriaOfMilitaryWin = true;
			strategistsGuild.resourcesRequired.add(new Resource("loom", 1));
			strategistsGuild.resourcesRequired.add(new Resource("stone", 1));
			strategistsGuild.resourcesRequired.add(new Resource("ore", 2));
			cards.add(strategistsGuild);

			GuildCard shipOwnersGuild = new GuildCard(id++, "Ship Owners Guild", false, false, true, 1);
			shipOwnersGuild.resourcesRequired.add(new Resource("wood", 3));
			shipOwnersGuild.resourcesRequired.add(new Resource("papyrus", 1));
			shipOwnersGuild.resourcesRequired.add(new Resource("glass", 1));
			shipOwnersGuild.criteriaColors.add("brown");
			shipOwnersGuild.criteriaColors.add("grey");
			shipOwnersGuild.criteriaColors.add("purple");
			cards.add(shipOwnersGuild);

			GuildCard scientistsGuild = new GuildCard(id++, "Scientists Guild", false, false, false, 1);
			scientistsGuild.scientistsGuild = true;
			scientistsGuild.resourcesRequired.add(new Resource("papyrus", 1));
			scientistsGuild.resourcesRequired.add(new Resource("wood", 2));
			scientistsGuild.resourcesRequired.add(new Resource("ore", 2));
			cards.add(scientistsGuild);

			GuildCard magistratesGuild = new GuildCard(id++, "Magistrates Guild", true, true, false, 1);
			magistratesGuild.criteriaColors.add("blue");
			magistratesGuild.resourcesRequired.add(new Resource("loom", 1));
			magistratesGuild.resourcesRequired.add(new Resource("stone", 1));
			magistratesGuild.resourcesRequired.add(new Resource("wood", 3));
			cards.add(magistratesGuild);

			GuildCard buildersGuild = new GuildCard(id++, "Builders Guild", true, true, true, 1);
			buildersGuild.criteriaOfStageBuilding = true;
			buildersGuild.resourcesRequired.add(new Resource("clay", 2));
			buildersGuild.resourcesRequired.add(new Resource("stone", 2));
			buildersGuild.resourcesRequired.add(new Resource("glass", 1));
			cards.add(buildersGuild);

			CommercialStructureCard haven = new CommercialStructureCard(id++, "Haven", 4, false, false, true);
			haven.criteriaColors.add("brown");
			haven.coinsProducedByCriteria = 1;
			haven.victoryPointsByCriteria = 1;
			haven.resourcesRequired.add(new Resource("loom", 1));
			haven.resourcesRequired.add(new Resource("ore", 1));
			haven.resourcesRequired.add(new Resource("wood", 1));
			haven.structureRequired.add("Forum");
			cards.add(haven);

			CommercialStructureCard haven2 = new CommercialStructureCard(id++, "Haven", 3, false, false, true);
			haven2.criteriaColors.add("brown");
			haven2.coinsProducedByCriteria = 1;
			haven2.victoryPointsByCriteria = 1;
			haven2.resourcesRequired.add(new Resource("loom", 1));
			haven2.resourcesRequired.add(new Resource("ore", 1));
			haven2.resourcesRequired.add(new Resource("wood", 1));
			haven2.structureRequired.add("Forum");
			cards.add(haven2);

			CommercialStructureCard lightHouse = new CommercialStructureCard(id++, "Light House", 3, false, false,
					true);
			lightHouse.criteriaColors.add("brown");
			lightHouse.coinsProducedByCriteria = 1;
			lightHouse.victoryPointsByCriteria = 1;
			lightHouse.resourcesRequired.add(new Resource("glass", 1));
			lightHouse.resourcesRequired.add(new Resource("stone", 1));
			lightHouse.structureRequired.add("Caravansery");
			cards.add(lightHouse);

			CommercialStructureCard lightHouse2 = new CommercialStructureCard(id++, "Light House", 6, false, false,
					true);
			lightHouse2.criteriaColors.add("brown");
			lightHouse2.coinsProducedByCriteria = 1;
			lightHouse2.victoryPointsByCriteria = 1;
			lightHouse2.resourcesRequired.add(new Resource("glass", 1));
			lightHouse2.resourcesRequired.add(new Resource("stone", 1));
			lightHouse2.structureRequired.add("Caravansery");
			cards.add(lightHouse2);

			CommercialStructureCard chamberOfCommerce = new CommercialStructureCard(id++, "Chamber of Commerce", 4,
					false, false, true);
			chamberOfCommerce.criteriaColors.add("grey");
			chamberOfCommerce.coinsProducedByCriteria = 2;
			chamberOfCommerce.victoryPointsByCriteria = 2;
			chamberOfCommerce.resourcesRequired.add(new Resource("clay", 2));
			chamberOfCommerce.resourcesRequired.add(new Resource("papyrus", 1));
			cards.add(chamberOfCommerce);

			CommercialStructureCard chamberOfCommerce2 = new CommercialStructureCard(id++, "Chamber of Commerce", 6,
					false, false, true);
			chamberOfCommerce2.criteriaColors.add("grey");
			chamberOfCommerce2.coinsProducedByCriteria = 2;
			chamberOfCommerce2.victoryPointsByCriteria = 2;
			chamberOfCommerce2.resourcesRequired.add(new Resource("clay", 2));
			chamberOfCommerce2.resourcesRequired.add(new Resource("papyrus", 1));
			cards.add(chamberOfCommerce2);

			CommercialStructureCard arena = new CommercialStructureCard(id++, "Arena", 3, false, false, true);
			arena.criteriaOfStageBuilding = true;
			arena.coinsProducedByCriteria = 3;
			arena.victoryPointsByCriteria = 1;
			arena.resourcesRequired.add(new Resource("stone", 2));
			arena.resourcesRequired.add(new Resource("ore", 1));
			arena.structureRequired.add("Dispensary");
			cards.add(arena);

			CommercialStructureCard arena2 = new CommercialStructureCard(id++, "Arena", 5, false, false, true);
			arena2.criteriaOfStageBuilding = true;
			arena2.coinsProducedByCriteria = 3;
			arena2.victoryPointsByCriteria = 1;
			arena2.resourcesRequired.add(new Resource("stone", 2));
			arena2.resourcesRequired.add(new Resource("ore", 1));
			arena2.structureRequired.add("Dispensary");
			cards.add(arena2);

			CommercialStructureCard arena3 = new CommercialStructureCard(id++, "Arena", 7, false, false, true);
			arena3.criteriaOfStageBuilding = true;
			arena3.coinsProducedByCriteria = 3;
			arena3.victoryPointsByCriteria = 1;
			arena3.resourcesRequired.add(new Resource("stone", 2));
			arena3.resourcesRequired.add(new Resource("ore", 1));
			arena3.structureRequired.add("Dispensary");
			cards.add(arena3);

		}

	}
	// if minimum number of players required for a card
	// exceeds number of players of game play then discard those cards
	public void discardIrrelevantCards(int numberOfPlayers) {
		
		int size = cards.size();
		for (int index = 0; index < size; index++) {
			Card card = cards.get(index);

			if (card.minimumPlayersRequired > numberOfPlayers) {

				cards.remove(card);
				size--;
				index--;

			}

		}

	}
	// randomly distribute relevant cards among players
	public void sampleCards(int numberOfPlayers, GamePlay game) {
		// initialize player list from game play object
		for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++){
			players.add(game.boards.get(playerIndex).player);
		}
		// generate number array [0,1,2,... number of cards]
		int size = cards.size();
		ArrayList<Integer> allIndex = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			allIndex.add(i);
		}
		// shuffle the number array
		Collections.shuffle(allIndex);
		// now distribute 7 cards of those random index of shuffled array to players
		int id = 0;
		for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
			Player player = players.get(playerIndex);
			player.cardsOnHand.clear();
			for (int cardIndex = 0; cardIndex < 7; cardIndex++) {
				Card card = cards.get(allIndex.get(id++));
				
				player.cardsOnHand.add(card);
				System.out.println( player.name + " got " + card.name );

			}
		}
	}
}
