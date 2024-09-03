package hibikero.chatringtone;

import hibikero.chatringtone.BackEnd.Command.ModeSetCommandDef;
import hibikero.chatringtone.BackEnd.Command.ModeSetCommandMut;
import hibikero.chatringtone.BackEnd.Command.PurchaseCommand;
import hibikero.chatringtone.BackEnd.Command.SetforCommand;
import hibikero.chatringtone.BackEnd.Config.PriceConfigReader;
import hibikero.chatringtone.BackEnd.Config.PriceConfigWriter;
import hibikero.chatringtone.BackEnd.Config.SoundConfigReader;
import hibikero.chatringtone.BackEnd.Config.SoundConfigWriter;
import hibikero.chatringtone.BackEnd.ECO.ECOManager;
import hibikero.chatringtone.BackEnd.Listener.ChatListener;
import hibikero.chatringtone.BackEnd.Listener.PlayerJoinListener;
import hibikero.chatringtone.Date.Sql.SqlInitialize;
import hibikero.chatringtone.FrontEnd.Command_Panel.VCPanelCommand;
import hibikero.chatringtone.FrontEnd.Listener.MenuClickListener;
import hibikero.chatringtone.FrontEnd.Listener.SettingsClickListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ChatRingtone extends JavaPlugin {

    private static ChatRingtone instance;

    private PriceConfigReader priceConfigReader;
    private PriceConfigWriter priceConfigWriter;
    private SoundConfigWriter soundConfigWriter;
    private SoundConfigReader soundConfigReader;


    @Override
    public void onEnable() {

        priceConfigWriter = new PriceConfigWriter(this);
        priceConfigReader = new PriceConfigReader(this);

        soundConfigWriter = new SoundConfigWriter(this);
        soundConfigReader = new SoundConfigReader(this);



        priceConfigReader.loadConfig();
        soundConfigReader.loadConfig();

        instance = this;

        if (!ECOManager.setupEconomy()) {
            getLogger().severe("\u001B未找到经济插件");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        File dbFile = new File(getDataFolder(), "PlayerDate/Playerdate.db");
        if (!dbFile.exists()) {
            dbFile.getParentFile().mkdirs();
            SqlInitialize.initializeDatabase(dbFile);
        }

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new MenuClickListener(), this);
        getServer().getPluginManager().registerEvents(new SettingsClickListener(), this);

        PurchaseCommand purchaseCommand = new PurchaseCommand();
        getCommand("sdpurchase").setExecutor(purchaseCommand);
        getCommand("sdpurchase").setTabCompleter(null);

        SetforCommand setforCommand = new SetforCommand();
        getCommand("sdsetfor").setExecutor(setforCommand);
        getCommand("sdsetfor").setTabCompleter(null);

        ModeSetCommandDef modeSetCommandDef = new ModeSetCommandDef();
        getCommand("sdmodesetfordef").setExecutor(modeSetCommandDef);
        getCommand("sdmodesetfordef").setTabCompleter(null);

        ModeSetCommandMut modeSetCommandMut = new ModeSetCommandMut();
        getCommand("sdmodesetformut").setExecutor(modeSetCommandMut);
        getCommand("sdmodesetformut").setTabCompleter(null);

        VCPanelCommand vcpanelcommand = new VCPanelCommand();
        getCommand("vcpanel").setExecutor(vcpanelcommand);


    }

    public static ChatRingtone getInstance() {
        return instance;
    }


}