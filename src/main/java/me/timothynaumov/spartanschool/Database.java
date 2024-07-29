package me.timothynaumov.spartanschool;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private static Database instance;
    private HashMap<String, PlayerData> database;

    public Database(){
        database = new HashMap<>();
    }

    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public void create(Player player, int points){
        PlayerData newPlayerData = new PlayerData(player, points);
        database.put(player.getDisplayName(), newPlayerData);
    }

    public PlayerData get(Player player){
        return database.get(player.getDisplayName());
    }

    //update
    public void update(Player player, PlayerData newData){
        database.replace(player.getDisplayName(), newData);
    }

    //delete
    public void delete(Player player){
        database.remove(player.getDisplayName());
    }

    public boolean changePoints(Player player, int points){
        PlayerData playerData = this.get(player);
        if(playerData != null && playerData.points + points >= 0){
            playerData.points += points;
            return true;
        }
        return false;
    }
}

/*
key-value pair database

schema:
database: String -> PlayerData
example: "0Timothy0" -> { player: Player Object, points: 100 }

{
    "0Timothy": {
        player: 0Timothy0's player object
        points: 100
    },
    "thebraines": {
        player: thebraines's player object
        points: 150
    },
}
 */
