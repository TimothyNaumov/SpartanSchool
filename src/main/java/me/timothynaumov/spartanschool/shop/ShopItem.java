package me.timothynaumov.spartanschool.shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopItem extends Purchasable {
    public Material material;

    public ShopItem(int price, Material material){
        super(price);
        this.material = material;
    }

    public void purchase(Player player, int quantity){
        player.getInventory().addItem(new ItemStack(this.material, quantity));
    }
}
