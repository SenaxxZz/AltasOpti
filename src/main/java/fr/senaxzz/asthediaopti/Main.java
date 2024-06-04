package fr.senaxzz.asthediaopti;

import fr.senaxzz.asthediaopti.commandes.ClearEntitiesCommandes;
import fr.senaxzz.asthediaopti.commandes.StatsCommandes;
import fr.senaxzz.asthediaopti.commandes.UnloadChunksCommandes;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("stats").setExecutor(new StatsCommandes(this)); 
        getCommand("UnloadChunks").setExecutor(new UnloadChunksCommandes());
        getCommand("ClearEntities").setExecutor(new ClearEntitiesCommandes());
    }

    @Override
    public void onDisable() {
    }

    public static Main getInstance() {
        return instance;
    }
}
