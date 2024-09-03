package hibikero.chatringtone.Date.Sql;

import hibikero.chatringtone.ChatRingtone;

import java.io.*;
import java.nio.file.*;
import java.sql.*;

import static hibikero.chatringtone.Date.Sql.AddVoiceToSql.addvoicetosql;
import static org.bukkit.Bukkit.getLogger;

public class SqlInitialize {

    static final String url = "jdbc:sqlite:";
    static final String databaseName = "/PlayerDate/Playerdate.db";

    // 初始化数据库
    public static void initializeDatabase(File dbFile) {
        // 如果数据库文件不存在，则创建一个新的数据库文件
        if (!dbFile.exists()) {
            try {

                Path tempDir = Files.createTempDirectory("plugin_database");
                Path tempFilePath = tempDir.resolve("Playerdate.db");


                InputStream inputStream = SqlInitialize.class.getResourceAsStream(databaseName);


                Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);

                getLogger().info("数据库文件创建成功：" + dbFile.getAbsolutePath());


                createPlayerPermissionsTable(getConnection(dbFile.getAbsolutePath()));
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                getLogger().severe("创建数据库文件时发生错误：" + e.getMessage());
            }
        }
    }

    // 创建玩家权限表
    static void createPlayerPermissionsTable(Connection connection) throws SQLException {
        getLogger().info("创建玩家权限表：");
        String query = "CREATE TABLE IF NOT EXISTS player_permissions ("
                + "uuid VARCHAR(36) PRIMARY KEY,"
                + "DefaultMode BOOLEAN DEFAULT FALSE,"
                + "MuteMode BOOLEAN DEFAULT FALSE,"
                + "CurrentVoice VARCHAR(255) DEFAULT 'Defa'"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            getLogger().info("玩家权限表创建成功\n");
        }
        addvoicetosql();
    }


    static Connection getConnection(String dbFilePath) throws SQLException {
        return DriverManager.getConnection(url + dbFilePath);
    }

    public static void checkAndAddColumn(String columnName) {
        String databaseUrl = "jdbc:sqlite:" + ChatRingtone.getInstance().getDataFolder().getPath() + "/PlayerDate/Playerdate.db";
        try (Connection connection = DriverManager.getConnection(databaseUrl)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, "player_permissions", columnName);
            if (!resultSet.next()) {
                // 如果数据库中不存在该属性，则添加
                try (Statement statement = connection.createStatement()) {
                    String query = "ALTER TABLE player_permissions ADD COLUMN " + columnName + " BOOLEAN DEFAULT FALSE";
                    statement.executeUpdate(query);
                    getLogger().info("成功添加属性：" + columnName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("检查或添加属性时出现错误：" + e.getMessage());
        }
    }











}

