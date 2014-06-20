package org.reta.dhkiller.strategies;

import org.reta.dhkiller.data.Constants;
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

public boolean lootCheck() {
		return GroundItems.getNearest(new Filter<GroundItem>() {
			@Override
			public boolean accept(GroundItem i) {
				return i.distanceTo() < 5 && (i.getId() == 4717 
						|| i.getId() == 4719 || i.getId() == 4721 
						|| i.getId() == 4723 || i.getId() == 995 
						|| i.getId() == 1631);
			}
		}).length > 0;
	}

public class Loot implements Strategy {
        @Override
        public boolean activate() {
            return lootCheck() && !Players.getMyPlayer().isInCombat();
        }

        @Override
        public void execute() {
        	GroundItem[] floorItems = GroundItems.getNearest(DHAROK_ITEM_IDS);
        	for(GroundItem j : floorItems){
        		Menu.take(j);
        		Time.sleep(2000, 3000);
        	}
        	Time.sleep(1000, 2000);
        }
        
}
