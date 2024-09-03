package hibikero.chatringtone.BackEnd.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class SoundConfigReader {
    private final Plugin plugin;
    private File configFile;
    private static FileConfiguration config;

    public SoundConfigReader(Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        configFile = new File(plugin.getDataFolder(), "SoundSettings.yml");

        if (!configFile.exists()) {
            plugin.getLogger().warning("未发现CONFIG文件");
            plugin.saveResource("SoundSettings.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static String getSoundName(String soundV){
        return config.getString(soundV+".Name");
    }
    public static String getrSoundBlock(String soundV){return config.getString(soundV+".Block");}

    public static String getSoundDescription(String soundV){return config.getString(soundV+".Description");}

}
