package gui;
import javax.swing.*;

import wonders.AlternativeResource;
import wonders.GamePlay;
import wonders.PlayerProperty;
import wonders.Resource;
import wonders.WonderBoard;


import java.util.Collections;
import java.util.Comparator; 
// gui for showing the final result
public class ResultTable {  
   // frame that shows final result
	JFrame f; 
    // constructor using game play object
    ResultTable(GamePlay game){ 
    // initializing the frame	
    f=new JFrame("Final Standings");  

    // calculate victory points of each player classified of eight types
    for(WonderBoard board: game.boards){
    	// number of types of science cards 
    	int scienceCardTypeCount=0;
    	// victory points gained by science cards
    	int scinceCardPoint=0;
    	// finding minimum number of science resource sets of 3 types 
    	// (science star, science wheel, science circle) can be formed of played card list 
    	int minimumScienceSet=Integer.MAX_VALUE;
    	PlayerProperty property = board.playerProperty;
    	for(Resource resource: property.mapOfResourceToName.values()){
    		if(!(resource instanceof AlternativeResource) && resource.name.startsWith("science")){
    			scienceCardTypeCount++;
    			// squaring number of science cards of each type
    			// to calculate victory points earned by science cards
    			scinceCardPoint+= Math.pow(resource.quantity,2);
    			if(resource.quantity<minimumScienceSet)
    			{
    				// finding minimum number of science resource sets of 3 types 
    		    	// (science star, science wheel, science circle) can be formed of played card list 
    		    
    				minimumScienceSet=resource.quantity;
    				
    			}
    		}
    		
    		
    	}
    	// if 3 types of science resources can be formed as a set (science star, science wheel, science circle)
    // then multiply science card set count with 7 to calculate victory points
    	if(scienceCardTypeCount==3){
    		scinceCardPoint+= minimumScienceSet*7;
    		property.victoryPointByScientificStructure=scinceCardPoint;
    	}
    	
    	// victory points by treasury is calculated by final amount of coins divided by 3
    	property.victoryPointByTreasuryContent=property.coins/3;
    	
    	property.victoryPoint+=property.victoryPointByTreasuryContent+ property.victoryPointByScientificStructure;		
    	
    	
    }
    // sort players by count of total victory points
    Comparator<WonderBoard> comp = new Comparator<WonderBoard>() {
		
		@Override
		public int compare(WonderBoard o1, WonderBoard o2) {
			
			return o2.playerProperty.victoryPoint-o1.playerProperty.victoryPoint;
		}
	};
	Collections.sort(game.boards, comp);
    // initialize 2-d array of data to be shown in final result table
    String data[][]=new String[game.numberOfPlayers][9]; 
    
    for(int rowIndex=0; rowIndex< game.numberOfPlayers;rowIndex++){
    	// first column consists of player name
    	data[rowIndex][0]=game.boards.get(rowIndex).player.getName();
    	// second column consists of victory points by military conflicts
    	data[rowIndex][1]=""+game.boards.get(rowIndex).playerProperty.victoryPointByMilitaryConflict;   	
    	// third column consists of victory points by treasury contents      
    	data[rowIndex][2]=""+game.boards.get(rowIndex).playerProperty.victoryPointByTreasuryContent;
    	// fourth column consists of victory points by stage building
    	data[rowIndex][3]=""+game.boards.get(rowIndex).playerProperty.victoryPointByStageBuilding;
    	// fifth column consists of victory points by civilian structures
    	data[rowIndex][4]=""+game.boards.get(rowIndex).playerProperty.victoryPointByCivilianStructure;
    	// sixth column consists of victory points by science cards
    	data[rowIndex][5]=""+game.boards.get(rowIndex).playerProperty.victoryPointByScientificStructure;
    	// seventh column consists of victory points by commercial structures
    	data[rowIndex][6]=""+game.boards.get(rowIndex).playerProperty.victoryPointByCommercialCard;
    	// eighth column consists of victory points by guild cards
    	data[rowIndex][7]=""+game.boards.get(rowIndex).playerProperty.victoryPointByGuilds;
        // ninth column consists of total victory points
    	data[rowIndex][8]=""+game.boards.get(rowIndex).playerProperty.victoryPoint;
        
    	
    }

  // initializing column names  
    String column[]={"Player","Military Conflicts","Treasury Contents","Stage Building","Civilian Structure","Scientific Structure","Commercial Structure","Guilds","Total"};  
    // initializing table with data and column names  
    JTable jt=new JTable(data,column);  
    // the table is contained inside a scroll pane  
    JScrollPane sp=new JScrollPane(jt); 
    // finalize design parts of the frame
    f.getContentPane().add(sp);  
      
    f.setSize(1025,400);  
//   
    f.setVisible(true);  
}  
  
} 