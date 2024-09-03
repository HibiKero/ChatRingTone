package hibikero.chatringtone.FrontEnd.Command_Panel;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Damageable;
import org.bukkit.inventory.meta.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;



import java.util.List;
import java.util.Map;

import static hibikero.chatringtone.BackEnd.Config.PriceConfigReader.getSoundPrice;
import static hibikero.chatringtone.BackEnd.Config.SoundConfigReader.*;
import static hibikero.chatringtone.ChatRingtone.getInstance;
import static hibikero.chatringtone.Date.Sql.SqlReader.getFalseAttributes;
import static hibikero.chatringtone.FrontEnd.Command_Panel.VCPanelCommand.createMenuItem2;

public class PurchaseVoiceMenu {


    public static Inventory purchaseMenu;

    public static void openPurchaseMenu(Player player) {
        // 创建 54 格的菜单
        Inventory  purchaseMenu = Bukkit.createInventory(player, 54, ChatColor.GOLD + "购买铃声");

        // 填充购买铃声参数
        fillPurchaseMenu(player, purchaseMenu, getFalseAttributes(player.getUniqueId().toString()), 1);

        // 打开购买铃声菜单
        player.openInventory(purchaseMenu);


    }









    public static void fillPurchaseMenu(Player player, Inventory inventory, List<String> falseAttributes, int page) {
        int pageSize =45 ; // 每页最多显示 45 个铃声
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, falseAttributes.size());

        for (int i = startIndex; i < endIndex; i++) {
            String attribute = falseAttributes.get(i);

            String Block=getrSoundBlock(attribute);
            String name=getSoundName(attribute);
            String description=getSoundDescription(attribute);
            double price =getSoundPrice(attribute);
            ItemStack item = createMenuItem2(Material.getMaterial(Block),name,description,price);


            inventory.setItem(i - startIndex, item);
        }


        if (endIndex < falseAttributes.size()) {
            ItemStack nextPageItem = createNextPageItem(page + 1);
            inventory.setItem(54, nextPageItem);
        }


        if (page > 1) {
            ItemStack prevPageItem = createPrevPageItem(page - 1);
            inventory.setItem(46, prevPageItem);
        }
    }

    private static ItemStack createNextPageItem(int nextPage) {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "下一页");
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack createPrevPageItem(int prevPage) {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "上一页");
        item.setItemMeta(meta);
        return item;
    }

    public static Inventory GetInv(){
        return purchaseMenu;
    }




}
