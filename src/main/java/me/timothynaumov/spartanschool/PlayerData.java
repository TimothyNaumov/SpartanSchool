package me.timothynaumov.spartanschool;

import org.bukkit.entity.Player;

public class PlayerData {
    public Player player;
    public int points;

    public PlayerData(Player player, int points){
        this.player = player;
        this.points = points;
    }
}
