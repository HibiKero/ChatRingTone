package hibikero.chatringtone.BackEnd.Listener;

import hibikero.chatringtone.Date.Sql.SqlTool;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerCurrentVoice;
import static hibikero.chatringtone.Date.Sql.SqlWriter.setPlayerVoicePermission;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // 获取玩家的UUID
        String uuid = event.getPlayer().getUniqueId().toString();

        // 将玩家UUID添加到数据库
        SqlTool.addPlayerToDatabase(uuid);

        //获取默认声音权限
        setPlayerVoicePermission(uuid,"Defa");

        //将玩家当前声音设置成默认声音
        setPlayerCurrentVoice(uuid,"Defa");

    }
}
