package org.parabot.reta.dhkiller.core;

import org.parabot.reta.dhkiller.data.Variables;
import org.parabot.reta.dhkiller.strategies.Kill;
import org.parabot.reta.dhkiller.strategies.Loot;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;

/**
 * @author: reta
 */
@ScriptManifest(author = "reta", category = Category.COMBAT, description = "Kills Dharok on Chaos Realm and loots items", 
                name = "DharokKiller", servers = {"Chaos Realm"}, version = 1.1)
        
public class Core extends Script {
        public boolean onExecute() {
                Variables.setStrategy(new Kill());
                Variables.setStrategy(new Loot());
        provide(Variables.getStrategyArray());
        return true;
    }

    public void onFinish() {

    }
}
