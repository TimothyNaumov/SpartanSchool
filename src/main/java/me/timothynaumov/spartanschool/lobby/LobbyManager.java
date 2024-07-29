package me.timothynaumov.spartanschool.lobby;

import me.timothynaumov.spartanschool.GameUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LobbyManager {
    Player partyLeader;
    boolean gameInProgress;
    Location gameSpawnLocation;


    public LobbyManager(){
        World world = Bukkit.getWorlds().get(0);
        gameSpawnLocation = new Location(world, -691, 4, 136);
        gameInProgress = false;
    }

    public void handlePlayerJoin(Player p){
        p.sendMessage("Welcome to Spartan School!");
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        if(gameInProgress){
            p.setGameMode(GameMode.SPECTATOR);
        }
        if(Bukkit.getOnlinePlayers().size() <= 1){
            //This player is the first to join
            //Make them the party leader
            partyLeader = p;
            p.sendMessage("You are now the party leader");
            p.getInventory().addItem(new ItemStack(Material.COMPASS));
        }
        p.teleport(gameSpawnLocation);
    }

    public void handlePlayerDeath(Player player){
        player.setGameMode(GameMode.SPECTATOR);
        if(GameUtils.isGameOver()){
            resetLobby();
        }
    }

    public void resetLobby(){
        World world = Bukkit.getWorlds().get(0);
        for(Player player : world.getPlayers()){
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            player.teleport(gameSpawnLocation);
        }
        partyLeader.getInventory().addItem(new ItemStack(Material.COMPASS));
    }
}
