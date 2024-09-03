package hibikero.chatringtone.BackEnd.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerMuteMode;

public class ModeSetCommandMut implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 检查命令发送者是否为玩家

        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();


        if (args.length < 1) {
            return true;
        }


        String soundParameter = args[0];
        if(args[0].equals("true")){
            setPlayerMuteMode(uuid,true);
            player.sendMessage("已开启静音模式，其他玩家提到你的名字将不会有提示音");
        }
        else if(args[0].equals("false")){
            setPlayerMuteMode(uuid,true);
            player.sendMessage("已关闭静音模式，如果其他玩家提到你的名字你将收到提示音");
        }
        else{
            player.sendMessage("装逼让你飞起来！");
        }




        player.sendMessage(": " + soundParameter);
        return true;
    }

}
