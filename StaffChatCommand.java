package me.motasim.nanocraftdiscordlinked.commands;

import me.motasim.nanocraftdiscordlinked.NanoCraftDiscordLinked;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StaffChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(!p.hasPermission("nanocraft.staffchat")) {
                p.sendMessage(NanoCraftDiscordLinked.prefix + ChatColor.RED + " You do not have the permissions to use this command.");
                return true;
            }
            List<String> configuredChats = (List<String>) NanoCraftDiscordLinked.getInstance().getConfig().getList("staffchats");

        }



        return true;
    }
}
