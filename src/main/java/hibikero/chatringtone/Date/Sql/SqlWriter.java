package hibikero.chatringtone.Date.Sql;

import hibikero.chatringtone.ChatRingtone;

import java.sql.*;

public class SqlWriter {

    public static void setPlayerDefaultMode(String uuid, boolean defaultMode) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        try (Connection connection = DriverManager.getConnection(databaseUrl)) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE player_permissions SET DefaultMode = ? WHERE uuid = ?")) {
                statement.setBoolean(1, defaultMode);
                statement.setString(2, uuid);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setPlayerMuteMode(String uuid, boolean muteMode) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        try (Connection connection = DriverManager.getConnection(databaseUrl)) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE player_permissions SET MuteMode = ? WHERE uuid = ?")) {
                statement.setBoolean(1, muteMode);
                statement.setString(2, uuid);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setPlayerCurrentVoice(String uuid, String currentVoice) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        try (Connection connection = DriverManager.getConnection(databaseUrl)) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE player_permissions SET CurrentVoice = ? WHERE uuid = ?")) {
                statement.setString(1, currentVoice);
                statement.setString(2, uuid);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setPlayerVoicePermission(String uuid, String voice) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        try (Connection connection = DriverManager.getConnection(databaseUrl)) {
            try (PreparedStatement setStatement = connection.prepareStatement("UPDATE player_permissions SET " + voice + " = TRUE WHERE uuid = ?")) {
                setStatement.setString(1, uuid);
                setStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
