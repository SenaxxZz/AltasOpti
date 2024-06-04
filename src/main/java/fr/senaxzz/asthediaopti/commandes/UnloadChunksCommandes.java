package fr.senaxzz.asthediaopti.commandes;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnloadChunksCommandes implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("permission.unloadchunks")) {
            sender.sendMessage("Vous n'avez pas la permission d'utiliser cette commande !");
            return true;
        }

        // Unload des chunks inutilisés
        int unloadedChunks = unloadUnusedChunks();

        sender.sendMessage("Nombre de chunks inutilisés déchargés : " + unloadedChunks);
        return true;
    }

    private int unloadUnusedChunks() {
        int unloadedChunks = 0;
        World world = Bukkit.getWorlds().get(0); // Obtient le monde principal
        for (Chunk chunk : world.getLoadedChunks()) {
            if (!chunk.isForceLoaded() && chunk.getEntities().length == 0) {
                chunk.unload(); // Décharge le chunk
                unloadedChunks++;
            }
        }
        return unloadedChunks;
    }
}
