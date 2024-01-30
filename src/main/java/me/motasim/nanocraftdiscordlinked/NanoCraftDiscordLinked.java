package me.motasim.nanocraftdiscordlinked;

import me.motasim.nanocraftdiscordlinked.commands.LinkCommand;
import me.motasim.nanocraftdiscordlinked.db.Database;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class NanoCraftDiscordLinked extends JavaPlugin {

    public static String prefix = ChatColor.GOLD + "[NCD]" + ChatColor.RESET;

    public static NanoCraftDiscordLinked plugin;

    private Database database;

    public FileConfiguration config;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        config = getConfig();

        this.database = new Database(this);

        getServer().getConsoleSender().sendMessage(prefix + ChatColor.GREEN + " NanoCraftDiscord has started.");

        getServer().getConsoleSender().sendMessage(prefix + ChatColor.DARK_AQUA + " NanoCraftDiscord connecting to database...");

        getCommand("link").setExecutor(new LinkCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static NanoCraftDiscordLinked getInstance() {
        return plugin;
    }

    public Database getDatabase() {
        return database;
    }
}
