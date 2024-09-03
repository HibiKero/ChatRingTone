package hibikero.chatringtone.BackEnd.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class PriceConfigReader {
    private final Plugin plugin;
    private File configFile;
    private static FileConfiguration config;

    public PriceConfigReader(Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        configFile = new File(plugin.getDataFolder(), "SoundPrice.yml");

        if (!configFile.exists()) {
            plugin.getLogger().warning("未发现CONFIG文件");
            plugin.saveResource("SoundPrice.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public static double getSoundPrice(String soundV){
        return config.getDouble("voice." + soundV,999999);
    }
    public static double getSoundMulti(){
        return config.getDouble("multi",1.0);
    }
}
