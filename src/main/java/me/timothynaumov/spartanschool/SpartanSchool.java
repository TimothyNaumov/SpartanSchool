package me.timothynaumov.spartanschool;

import me.timothynaumov.spartanschool.lobby.LobbyManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpartanSchool extends JavaPlugin {
    private GameLogic gameLogic;
    private LobbyManager lobbyManager;
    private static SpartanSchool instance;

    @Override
    public void onEnable() {
        instance = this;
        gameLogic = new GameLogic();
        lobbyManager = new LobbyManager();
        getServer().getPluginManager().registerEvents(new SpartanEventHandlers(gameLogic, lobbyManager), this);
        getServer().getPluginManager().registerEvents(new ShopListener(gameLogic), this);
        getCommand("spartanschool").setExecutor(new SpartanCommandExecutor(gameLogic));
        getCommand("balance").setExecutor(new SpartanCommandExecutor(gameLogic));
        getCommand("purchase").setExecutor(new SpartanCommandExecutor(gameLogic));
        getCommand("purchase").setTabCompleter(new SpartanShopTabCompletion());
        getCommand("shop").setExecutor(new ShopCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }

    public static SpartanSchool getInstance(){
        return instance;
    }
}
