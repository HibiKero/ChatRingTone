package hibikero.chatringtone.FrontEnd.Listener;

import hibikero.chatringtone.FrontEnd.Command_Panel.PurchaseVoiceMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static hibikero.chatringtone.FrontEnd.Command_Panel.PurchaseVoiceMenu.openPurchaseMenu;
import static hibikero.chatringtone.FrontEnd.Command_Panel.SystemSettingMenu.openSystemSettingMenu;

public class MenuClickListener implements Listener {



    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem != null && clickedItem.hasItemMeta()) {
            ItemMeta itemMeta = clickedItem.getItemMeta();
            if (itemMeta != null && itemMeta.hasDisplayName()) {
                String itemName = itemMeta.getDisplayName();
                switch (itemName) {
                    case "购买铃声":
                        openPurchaseMenu(player);
                        break;
                    case "设置铃声":
                        // 处理设置铃声功能
                        player.sendMessage(ChatColor.GREEN + "设置铃声功能尚未实现！");
                        break;
                    case "系统设置":
                        openSystemSettingMenu(player);
                        break;
                    default:
                        break;
                }
                event.setCancelled(true);
            }
        }
    }
}