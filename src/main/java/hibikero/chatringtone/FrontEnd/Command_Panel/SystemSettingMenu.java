package hibikero.chatringtone.FrontEnd.Command_Panel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static hibikero.chatringtone.Date.Sql.SqlReader.getPlayerDefaultMode;
import static hibikero.chatringtone.Date.Sql.SqlReader.getPlayerMuteMode;
import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerDefaultMode;
import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerMuteMode;

public class SystemSettingMenu {

    public static void openSystemSettingMenu(Player player) {
        // 创建一个一行的系统设置菜单
        Inventory systemSettingsMenu = Bukkit.createInventory(player, 9, "系统设置");

        // 创建默认模式设置项
        ItemStack defaultModeItem = createToggleItem(Material.LEVER, "默认模式", "点击切换默认模式状态");
        // 创建静音模式设置项
        ItemStack muteModeItem = createToggleItem(Material.NOTE_BLOCK, "静音模式", "点击切换静音模式状态");

        // 将设置项放置到系统设置菜单中
        systemSettingsMenu.setItem(3, defaultModeItem); // 第一行第二个格子
        systemSettingsMenu.setItem(5, muteModeItem); // 第一行第四个格子

        // 打开系统设置菜单
        player.openInventory(systemSettingsMenu);
    }

    private static ItemStack createToggleItem(Material material, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        List<String> itemLore = new ArrayList<>();
        itemLore.add(lore);
        meta.setLore(itemLore);
        item.setItemMeta(meta);
        return item;
    }

    // 点击事件处理程序

}
