package hibikero.chatringtone.Date.Sql;

import hibikero.chatringtone.ChatRingtone;

import java.sql.*;

public class SqlTool {

    public static void addPlayerToDatabase(String uuid) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        try (Connection connection = DriverManager.getConnection(databaseUrl)) {
            // 检查并添加玩家UUID到数据库
            try (Statement statement = connection.createStatement()) {
                String query = "CREATE TABLE IF NOT EXISTS player_permissions ("
                        + "uuid VARCHAR(36) PRIMARY KEY,"
                        + "DefaultMode BOOLEAN DEFAULT FALSE,"
                        + "MuteMode BOOLEAN DEFAULT FALSE,"
                        + "CurrentVoice VARCHAR(255) DEFAULT 'Defa'"
                        + ")";
                statement.executeUpdate(query);

                String insertQuery = "INSERT OR IGNORE INTO player_permissions (uuid, DefaultMode, MuteMode) VALUES ('" + uuid + "', FALSE, FALSE)";
                statement.executeUpdate(insertQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
