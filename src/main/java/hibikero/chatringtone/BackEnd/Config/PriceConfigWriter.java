package hibikero.chatringtone.BackEnd.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class PriceConfigWriter {
    private final Plugin plugin;
    private static File configFile;
    private static FileConfiguration config;

    public PriceConfigWriter(Plugin plugin) {
        this.plugin = plugin;

        plugin.saveResource("SoundPrice.yml", false);
        configFile = new File(plugin.getDataFolder(), "SoundPrice.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }



}
