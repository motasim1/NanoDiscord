package me.motasim.nanocraftdiscordlinked.db;

import me.motasim.nanocraftdiscordlinked.NanoCraftDiscordLinked;
import me.motasim.nanocraftdiscordlinked.models.PlayerProfile;
import me.motasim.nanocraftdiscordlinked.utils.Token;
import org.bukkit.plugin.Plugin;

import java.sql.*;

public class Database {

    private final NanoCraftDiscordLinked plugin;

    public Database(NanoCraftDiscordLinked plugin) {
        this.plugin = plugin;
    }

    private Connection connection;

    String url;

    public Connection getConnection() throws SQLException {
        String host = this.plugin.getConfig().getString("mysql.host");
        String port = this.plugin.getConfig().getString("mysql.port");
        String dbname = this.plugin.getConfig().getString("mysql.dbname");
        String username = this.plugin.getConfig().getString("mysql.username");
        String password = this.plugin.getConfig().getString("mysql.password");

        if(connection != null) {
            return connection;
        }

        url = "jdbc:mysql://" + host + ":" + port+ "/" + dbname;

        this.connection = DriverManager.getConnection(url, username, password);

        return this.connection;
    }

    public PlayerProfile findPlayerProfile(String uuid) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM players WHERE uuid = ?");
        statement.setString(1, uuid);
        ResultSet results = statement.executeQuery();
        if(results.next()) {
            PlayerProfile profile = new PlayerProfile(uuid, results.getString("discordid"), results.getString("token"), results.getBoolean("verified"));
            statement.close();
            return profile;
        }
        statement.close();
        return null;
    }

    public void createPlayerProfile(PlayerProfile profile) throws SQLException {
        String token = profile.getToken();
        String uuid = profile.getUuid();

        PreparedStatement statement = this.getConnection().prepareStatement("INSERT INTO players (uuid, discordid, token) VALUES (?, ?, ?)");
        statement.setString(1, uuid);
        statement.setString(2, profile.getDiscordId());
        statement.setString(3, token);

        statement.executeUpdate();
        statement.close();
    }

    public void verifyPlayerProfile(PlayerProfile profile) throws SQLException {
        String uuid = profile.getUuid();

        PreparedStatement statement = this.getConnection().prepareStatement("UPDATE players SET verified = true WHERE uuid = ?");
        statement.setString(1, uuid);
        statement.executeUpdate();
        statement.close();
    }

    public Boolean isVerified(PlayerProfile profile) throws SQLException {
        boolean verified = false;
        String uuid = profile.getUuid();

        PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM players WHERE uuid = ?");
        statement.setString(1, uuid);
        ResultSet result = statement.executeQuery();

        if(result.next()) {
            verified = result.getBoolean("verified");
        }
        statement.close();
        return verified;
    }
}
