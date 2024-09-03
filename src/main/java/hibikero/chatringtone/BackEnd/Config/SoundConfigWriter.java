package hibikero.chatringtone.BackEnd.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class SoundConfigWriter {
    private final Plugin plugin;
    private static File configFile;
    private static FileConfiguration config;

    public SoundConfigWriter(Plugin plugin) {
        this.plugin = plugin;

        plugin.saveResource("SoundSettings.yml", false);
        configFile = new File(plugin.getDataFolder(), "SoundSettings.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}
