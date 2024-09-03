package hibikero.chatringtone.Date.Voice;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlaySound {
    public static void playsound(String soundV, Player mentionedPlayer){
        if(soundV.equals("Defa")) {
            mentionedPlayer.playSound(mentionedPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        }
        if(soundV.equals("BeaconAct")){
            mentionedPlayer.playSound(mentionedPlayer.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.0f);
        }















    }

}
