package hibikero.chatringtone.FrontEnd.Command_Panel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.eclipse.sisu.Hidden;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static hibikero.chatringtone.BackEnd.Config.PriceConfigReader.getSoundMulti;
import static hibikero.chatringtone.BackEnd.Config.SoundConfigReader.getSoundName;
import static hibikero.chatringtone.Date.Sql.SqlReader.*;

public class VCPanelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        // 创建一个3x9的主菜单界面
        Inventory mainMenu = Bukkit.createInventory(player, 27, "铃声面板");


        // 创建玩家状态头部
        ItemStack playerHead = getPlayerHead(player);
        ItemMeta playerHeadMeta = playerHead.getItemMeta();
        playerHeadMeta.setDisplayName("玩家状态");


        String CurrentVc = getPlayerCurrentVoice(uuid);
        String name = getSoundName(CurrentVc);

        List<String> playerHeadLore = new ArrayList<>();
        // 添加玩家状态信息，可以根据实际情况修改
        playerHeadLore.add("当前铃声："+name);
        playerHeadLore.add("默认铃声状态："+getDefaMode(uuid));
        playerHeadLore.add("静音状态："+getMuteMode(uuid));
        playerHeadMeta.setLore(playerHeadLore);
        playerHead.setItemMeta(playerHeadMeta);

        // 创建三个子菜单项
        ItemStack buySoundItem = createMenuItem(Material.DIAMOND, "购买铃声", "点击购买铃声");
        ItemStack setSoundItem = createMenuItem(Material.REDSTONE, "设置铃声", "点击设置铃声");
        ItemStack systemSettingsItem = createMenuItem(Material.COMPARATOR, "系统设置", "点击打开系统设置");

        // 将子菜单项放置到主菜单界面中
        mainMenu.setItem(4,playerHead);
        mainMenu.setItem(10, buySoundItem);
        mainMenu.setItem(13, setSoundItem);
        mainMenu.setItem(16, systemSettingsItem);

        // 打开主菜单界面
        player.openInventory(mainMenu);

        return true;
    }

    static ItemStack createMenuItem(Material material, String name, String lore) {
        double multi = getSoundMulti();
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        List<String> itemLore = new ArrayList<>();
        itemLore.add(lore);
        meta.setLore(itemLore);
        item.setItemMeta(meta);
        return item;
    }

    static ItemStack createMenuItem2(Material material, String name, String lore, double price) {
        double multi = getSoundMulti();
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        List<String> itemLore = new ArrayList<>();
        itemLore.add(lore);
        itemLore.add("价格:"+price*multi);
        meta.setLore(itemLore);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack getPlayerHead(Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(player);
        head.setItemMeta(meta);
        return head;
    }
    String getDefaMode(UUID uuid){
        boolean flag =getPlayerDefaultMode(uuid);
        if(flag){
            return "开启";
        }
        else{
            return "关闭";
        }
    }
    String getMuteMode(UUID uuid){
        boolean flag =getPlayerMuteMode(uuid);
        if(flag){
            return "开启";
        }
        else{
            return "关闭";
        }
    }

}
