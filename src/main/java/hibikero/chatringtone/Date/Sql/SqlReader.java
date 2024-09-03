package hibikero.chatringtone.Date.Sql;

import hibikero.chatringtone.ChatRingtone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SqlReader {

    public static boolean getPlayerMuteMode(UUID playerUUID) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        String query = "SELECT MuteMode FROM player_permissions WHERE uuid = ?";
        try (Connection connection = DriverManager.getConnection(databaseUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, playerUUID.toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("MuteMode");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean getPlayerDefaultMode(UUID playerUUID) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        String query = "SELECT DefaultMode FROM player_permissions WHERE uuid = ?";
        try (Connection connection = DriverManager.getConnection(databaseUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, playerUUID.toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("DefaultMode");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getPlayerCurrentVoice(UUID playerUUID) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        String query = "SELECT CurrentVoice FROM player_permissions WHERE uuid = ?";
        try (Connection connection = DriverManager.getConnection(databaseUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, playerUUID.toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("CurrentVoice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean hasPlayerVoicePermission(String uuid, String voice) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        boolean hasPermission = false;
        try (Connection connection = DriverManager.getConnection(databaseUrl)) {
            String query = "SELECT " + voice + " FROM player_permissions WHERE uuid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, uuid);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // 从结果集中获取整数值并转换为布尔值
                        int value = resultSet.getInt(voice);
                        hasPermission = (value == 1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 在发生异常时，做出适当处理，比如记录日志或者返回默认值
            hasPermission = false; // 返回默认值
        }
        return hasPermission;
    }

    public static List<String> getFalseAttributes(String uuid) {
        List<String> falseAttributes = new ArrayList<>();
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        try (Connection connection = DriverManager.getConnection(databaseUrl)) {
            String query = "SELECT * FROM player_permissions WHERE uuid = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, uuid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        boolean columnValue = resultSet.getBoolean(columnName);
                        if (!columnName.equals("uuid") && !columnName.equals("DefaultMode") &&
                                !columnName.equals("MuteMode") && !columnName.equals("CurrentVoice") &&
                                  !columnValue) {
                            falseAttributes.add(columnName);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return falseAttributes;
    }




}
