package me.timothynaumov.spartanschool.shop.entity;

import me.timothynaumov.spartanschool.shop.ShopEntity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class FightingWolf extends ShopEntity {

    public FightingWolf(){
        super(4, EntityType.WOLF);
    }

    @Override
    public void purchase(Player player, int quantity){
        Location location = player.getLocation();

        for(int i = 0; i < quantity; i++){
            Wolf wolf =  (Wolf) location.getWorld().spawnEntity(location, entity);
            wolf.setAdult();
            wolf.setTamed(true);
            wolf.setOwner(player);

            wolf.setBreed(false);

            wolf.setCustomName(ChatColor.YELLOW + player.getName() + "'s Wolf");
            wolf.setCustomNameVisible(true);

            wolf.setHealth(wolf.getMaxHealth());
            wolf.setCanPickupItems(false);
        }
    }
}
