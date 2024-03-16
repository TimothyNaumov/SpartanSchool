package me.timothynaumov.spartanschool.shop;

import org.bukkit.entity.Player;

public abstract class Purchasable {
    public int price;

    public Purchasable(int price){
        this.price = price;
    }

    public abstract void purchase(Player player, int quantity);
}
