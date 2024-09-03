package hibikero.chatringtone.BackEnd.Listener;

import hibikero.chatringtone.Date.Sql.SqlReader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

import static hibikero.chatringtone.Date.Sql.SqlReader.getPlayerCurrentVoice;
import static hibikero.chatringtone.Date.Voice.PlaySound.playsound;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String uuid = player.getUniqueId().toString();

        for (Player mentionedPlayer : Bukkit.getServer().getOnlinePlayers()) {
            String playerName = mentionedPlayer.getName();
            if (message.contains(playerName)) {
                UUID mentionedPlayerUUID = mentionedPlayer.getUniqueId();


                boolean muteMode = SqlReader.getPlayerMuteMode(mentionedPlayerUUID);
                boolean defaultMode = SqlReader.getPlayerDefaultMode(mentionedPlayerUUID);
                String currentVoice = getPlayerCurrentVoice(player.getUniqueId());


                if (muteMode) {
                    continue; // 如果MuteMode为True，则不播放声音，直接跳过
                }

                // 检查被提及玩家的DefaultMode
                else if (defaultMode) {
                    playsound("Defa",mentionedPlayer);
                    continue; // 如果DefaultMode为True，则播放默认声音并跳过
                }

                // 获取提及者的CurrentVoice作为声音参数
                else if(currentVoice != null && !currentVoice.isEmpty()) {
                    // 使用提及者的CurrentVoice作为声音参数
                    playsound(currentVoice,mentionedPlayer);


                    }
                }

            }
        }
    }


