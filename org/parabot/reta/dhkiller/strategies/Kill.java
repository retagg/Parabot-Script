package org.parabot.reta.dhkiller.strategies;

import org.parabot.reta.dhkiller.data.Constants;
import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Tile;

public void traversePath(){
    	int goalX = START_LOCATION.getX();
        int goalY = START_LOCATION.getY();
        int currentX = Players.getMyPlayer().getLocation().getX();
        int currentY = Players.getMyPlayer().getLocation().getY();
        int difX = goalX - currentX;
        int difY = goalY - currentY;
        int walkToX;
        int walkToY;
        // Calculate where I need to walk to
        if (Math.abs(difX) < 3) {
                walkToX = goalX;
        } 
        else {
                walkToX = currentX + 3 * (difX > 0 ? 1 : -1);
        }
        if (Math.abs(difY) < 3) {
                walkToY = goalY;
        } else {
                walkToY = currentY + 3 * (difY > 0 ? 1 : -1);
        }
        Tile goal = new Tile(walkToX, walkToY);
        walkTo(Players.getMyPlayer().getLocation(), goal);
    }
    
public void walkTo(Tile from, Tile to){
        Loader.getClient().walkTo(0, 0, 0, 0, from.getRegionY(), 0, 0, to.getRegionY(), from.getRegionX(), true, to.getRegionX());
    	Time.sleep(1500, 2000);
}
    
public void walkToDharok(){
    	Mouse.getInstance().click(664, 148, true);
    	Time.sleep(10000, 15000);
}
    
public void teleportBack(){
    	if(Players.getMyPlayer().getLocation().getX() < 3400){
    		counter = 0;
    		Keyboard.getInstance().sendKeys("::barrows");
    		Time.sleep(5000, 7000);
    		walkToDharok();
    	}
}
    
public void toggleMeleePrayer(){
    	Menu.sendAction(169, 107347968, 457, 25036);
    	counter++;
}
    
public Npc getDharok() {
        try {
                return Npcs.getNearest(new Filter<Npc>() {
	        	@Override
	        	public boolean accept(Npc npc) {
	        		return (!npc.isInCombat())
		        		&& (npc.getDef().getId() == 2026 || npc.getDef().getId() == 14264);
	        	}
        	})[0]; 
	}
	catch(ArrayIndexOutOfBoundsException e){
		Time.sleep(5000);
		return null;
	}
}
	
public class Kill implements Strategy {
    	
        @Override
        public boolean activate() {
            return !lootCheck() && !Players.getMyPlayer().isInCombat();
        }

        @Override
        public void execute() {
        	teleportBack();
            Npc dharok = getDharok();
            if(dharok != null & dharok.getDef().getId() != 0 && dharok.distanceTo() < 10)
            	if(!dharok.isInCombat()){
            		dharok.interact(1);
            		Time.sleep(500, 600);
            		if(counter % 2 == 0)
            			toggleMeleePrayer();
            		Time.sleep(2000, 3000);
            		if(Players.getMyPlayer().isInCombat())
            			Time.sleep(1000, 1200);
            	}
            else
            	Time.sleep(2000);
            while(Players.getMyPlayer().isInCombat())
            	Time.sleep(200, 400);
            Time.sleep(500, 600);
        }
    }
