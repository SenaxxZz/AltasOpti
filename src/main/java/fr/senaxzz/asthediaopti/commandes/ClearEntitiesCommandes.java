package fr.senaxzz.asthediaopti.commandes;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class ClearEntitiesCommandes implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("permission.clearentities")) {
            sender.sendMessage("Vous n'avez pas la permission d'utiliser cette commande !");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur !");
            return true;
        }

        Player player = (Player) sender;

        // Nettoyage des entités
        int removedEntities = clearEntities(player);

        sender.sendMessage("Nombre d'entités hostiles éloignées supprimées : " + removedEntities);
        return true;
    }

    private int clearEntities(Player player) {
        int removedEntities = 0;
        World world = player.getWorld(); // Obtient le monde du joueur
        for (Entity entity : world.getEntities()) {
            if (entity instanceof LivingEntity && entity instanceof Monster && !(entity instanceof Player)) {
                // Vérifie si l'entité est à plus de 100 blocs du joueur
                if (entity.getLocation().distanceSquared(player.getLocation()) > 100 * 100) {
                    entity.remove();
                    removedEntities++;
                }
            }
        }
        return removedEntities;
    }
}
