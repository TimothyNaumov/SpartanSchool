package me.timothynaumov.spartanschool.shop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopEntity extends Purchasable {
    public EntityType entity;

    public ShopEntity(int price, EntityType entity){
        super(price);
        this.entity = entity;
    }

    public void purchase(Player player, int quantity){
        Location location = player.getLocation();

        for(int i = 0; i < quantity; i++){
            location.getWorld().spawnEntity(location, entity);
        }
    }
}
