package hibikero.chatringtone.BackEnd.ECO;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class ECOManager {

    private static Economy economy;
    public static boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }

        economy = rsp.getProvider();
        return economy != null;
    }

    // 扣除玩家的游戏币
    public static boolean deductPlayerBalance(Player player, double amount) {
        if (economy == null) {
            getLogger().warning("无法找到经济插件");
            return false;
        }

        double balance = economy.getBalance(player);
        if (balance < amount) {
            // 玩家的游戏币不足
            player.sendMessage(ChatColor.RED + "你的游戏币不足！");
            return false;
        }

        economy.withdrawPlayer(player, amount);
        return true;
    }
}
