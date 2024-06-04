package fr.senaxzz.asthediaopti.commandes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class StatsCommandes implements CommandExecutor {

    private final Plugin plugin;

    public StatsCommandes(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("permission.stats")) {
            sender.sendMessage("Vous n'avez pas la permission d'utiliser cette commande !");
            return true;
        }

        double[] tps = Bukkit.getServer().getTPS();
        double oneMinuteTPS = Math.round(tps[0] * 100.0) / 100.0;
        ChatColor color;

        if (oneMinuteTPS < 10) {
            color = ChatColor.RED; // Bas
        } else if (oneMinuteTPS < 16) {
            color = ChatColor.GOLD; // Moyen
        } else {
            color = ChatColor.GREEN; // Haut
        }

        sender.sendMessage(ChatColor.BOLD + "TPS (1m): " + color + String.format("%.2f", oneMinuteTPS));

        return true;
    }
}
