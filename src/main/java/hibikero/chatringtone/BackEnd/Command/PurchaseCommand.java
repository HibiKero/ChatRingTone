package hibikero.chatringtone.BackEnd.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import hibikero.chatringtone.BackEnd.ECO.ECOManager;

import static hibikero.chatringtone.BackEnd.Config.PriceConfigReader.getSoundMulti;
import static hibikero.chatringtone.BackEnd.Config.PriceConfigReader.getSoundPrice;
import static hibikero.chatringtone.BackEnd.Config.SoundConfigReader.getSoundName;
import static hibikero.chatringtone.Date.Sql.SqlReader.hasPlayerVoicePermission;
import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerVoicePermission;

public class PurchaseCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();

        // 检查是否提供了购买参数
        if (args.length < 1) {
            player.sendMessage("用法: /sdpurchase <声音参数>");
            return true;
        }

        // 获取购买的声音参数
        String soundParameter = args[0];
        double price = getSoundPrice(soundParameter);
        double multi = getSoundMulti();
        String name = getSoundName(soundParameter);

        if(!hasPlayerVoicePermission(uuid,soundParameter)) {
            if (ECOManager.deductPlayerBalance(player,price*multi)) {
                setPlayerVoicePermission(uuid, soundParameter);
                player.sendMessage("成功购买铃声： " + name);
            }
        }
        else{
            player.sendMessage(ChatColor.RED+"你已经拥有该铃声了" );
        }


        return true;
    }
}
