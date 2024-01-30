package me.motasim.nanocraftdiscordlinked.commands;

import me.motasim.nanocraftdiscordlinked.NanoCraftDiscordLinked;
import me.motasim.nanocraftdiscordlinked.models.PlayerProfile;
import me.motasim.nanocraftdiscordlinked.utils.Token;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.sql.SQLException;

public class LinkCommand implements CommandExecutor
{
    private final NanoCraftDiscordLinked plugin;

    public LinkCommand(NanoCraftDiscordLinked plugin) {
        this.plugin = plugin;
    }

    private final NanoCraftDiscordLinked instance = NanoCraftDiscordLinked.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {


        if(!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players to link their discord accounts.");
            return true;
        }

        String token = Token.randomString(8);

        Player p = (Player) sender;

        PlayerProfile profile;

        try {
            profile = this.plugin.getDatabase().findPlayerProfile(p.getUniqueId().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(profile != null) {
            try {
                if(!this.plugin.getDatabase().isVerified(profile)) {
                    if(profile.getDiscordId() != null) { // Reward players for linking accounts.
                        // reward players and also update the verified.
                        this.plugin.getDatabase().verifyPlayerProfile(profile);
                        p.sendMessage("You claimed your rewards for linking your accounts.");
                    } else { // player has to use the link command in discord.
                        p.sendMessage("Please use this command in the discord server:");
                        p.sendMessage("/link " + profile.getToken());
                    }
                } else {
                    p.sendMessage("You already have an account linked and it seems like you've already claimed the rewards.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            profile = new PlayerProfile(p.getUniqueId().toString(), null, token, false);

            try {
                this.plugin.getDatabase().createPlayerProfile(profile);
                p.sendMessage("Please use this command in the discord server:");
                p.sendMessage("/link " + profile.getToken());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }
}
