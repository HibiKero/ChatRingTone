package hibikero.chatringtone.FrontEnd.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

import static hibikero.chatringtone.Date.Sql.SqlReader.getPlayerDefaultMode;
import static hibikero.chatringtone.Date.Sql.SqlReader.getPlayerMuteMode;
import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerDefaultMode;
import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerMuteMode;

public class SettingsClickListener implements Listener {

    @EventHandler
    public void onSettingsClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem != null && clickedItem.hasItemMeta()) {
            ItemMeta itemMeta = clickedItem.getItemMeta();

            if (itemMeta != null && itemMeta.hasDisplayName()) {
                String itemName = itemMeta.getDisplayName();
                switch (itemName) {
                    case "默认模式":

                        boolean DMD = getPlayerDefaultMode(uuid);
                        if(DMD){
                            setPlayerDefaultMode(String.valueOf(uuid),false);
                            player.closeInventory();
                            player.sendMessage(ChatColor.GREEN + "已关闭默认模式");
                            break;
                        }
                        else{
                            setPlayerDefaultMode(String.valueOf(uuid),true);
                            player.closeInventory();
                            player.sendMessage(ChatColor.GREEN + "已开启默认模式");
                            break;
                        }
                    case "静音模式":
                        boolean MMD = getPlayerMuteMode(uuid);
                        if(MMD){
                            setPlayerMuteMode(String.valueOf(uuid),false);
                            player.closeInventory();
                            player.sendMessage(ChatColor.GREEN + "已关闭静音模式");
                            break;
                        }
                        else{
                            setPlayerMuteMode(String.valueOf(uuid),true);
                            player.closeInventory();
                            player.sendMessage(ChatColor.GREEN + "已开启静音模式");
                            break;
                        }
                    default:
                        break;
                }
                event.setCancelled(true);
            }
        }
    }
}
