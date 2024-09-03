package hibikero.chatringtone.FrontEnd.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static hibikero.chatringtone.Date.Sql.SqlReader.getFalseAttributes;
import static hibikero.chatringtone.FrontEnd.Command_Panel.PurchaseVoiceMenu.GetInv;
import static hibikero.chatringtone.FrontEnd.Command_Panel.PurchaseVoiceMenu.fillPurchaseMenu;

public class PurchaseClickListener {
    @EventHandler
    public void onPurchaseMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();


            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();
                String displayName = meta.getDisplayName();

                if (displayName != null && (displayName.equals(ChatColor.GREEN + "下一页") || displayName.equals(ChatColor.GREEN + "上一页"))) {
                    // 处理翻页
                    int currentPage = Integer.parseInt(ChatColor.stripColor(displayName.split(" ")[1]));
                    if (displayName.contains("下一页")) {
                        Inventory in= GetInv();
                        handlePageChange(player, in, currentPage + 1);
                    } else if (displayName.contains("上一页")) {
                        Inventory in= GetInv();
                        handlePageChange(player, in, currentPage - 1);
                    }
                } else {
                    // 处理购买铃声
                    // 这里根据玩家点击的铃声执行相应的操作
                    // 比如执行购买操作或者提示已拥有等等
                }
            }
        }
    public static void handlePageChange(Player player, Inventory inventory, int currentPage) {
        // 获取购买铃声参数
        List<String> falseAttributes = getFalseAttributes(player.getUniqueId().toString());

        // 填充购买铃声参数
        fillPurchaseMenu(player, inventory, falseAttributes, currentPage);
    }

    public static v

}


