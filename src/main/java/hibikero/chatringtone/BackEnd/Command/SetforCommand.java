package hibikero.chatringtone.BackEnd.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static hibikero.chatringtone.BackEnd.Config.SoundConfigReader.getSoundName;
import static hibikero.chatringtone.Date.Sql.SqlReader.hasPlayerVoicePermission;
import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerCurrentVoice;

public class SetforCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();


        if (args.length < 1) {
            return true;
        }


        String soundParameter = args[0];
        String name = getSoundName(soundParameter);

        if(hasPlayerVoicePermission(uuid,soundParameter)) {
            setPlayerCurrentVoice(uuid, soundParameter);
            player.sendMessage("您的铃声设置为: " + name);
            return true;
        }
        else{
            player.sendMessage(ChatColor.RED+"牛逼啊老弟，越权访问是吧？");
        }
        return true;

    }
}
