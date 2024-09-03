package hibikero.chatringtone.BackEnd.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerDefaultMode;

public class ModeSetCommandDef implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 检查命令发送者是否为玩家

        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();

        // 检查是否提供了购买参数
        if (args.length < 1) {
            return true;
        }

        // 获取购买的声音参数
        String soundParameter = args[0];
        if(args[0].equals("true")){
            setPlayerDefaultMode(uuid,true);
            player.sendMessage("已开启默认铃声模式，你只会听到默认铃声");
        }
        else if(args[0].equals("false")){
            setPlayerDefaultMode(uuid,true);
            player.sendMessage("已关闭默认铃声模式，你将听到来自其他玩家不同种类的铃声");
        }
        else{
            player.sendMessage("装逼让你飞起来！");
        }




        return true;
    }


}
