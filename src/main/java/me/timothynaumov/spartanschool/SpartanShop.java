package me.timothynaumov.spartanschool;

import me.timothynaumov.spartanschool.shop.Purchasable;
import me.timothynaumov.spartanschool.shop.entity.IronGolem;
import me.timothynaumov.spartanschool.shop.entity.Snowman;
import me.timothynaumov.spartanschool.shop.entity.FightingWolf;
import me.timothynaumov.spartanschool.shop.item.Shield;
import me.timothynaumov.spartanschool.shop.item.Snowball;
import org.bukkit.entity.Player;


import java.util.HashMap;

public class SpartanShop {
    public static HashMap<String, Purchasable> itemMap;

    static{
        itemMap = new HashMap<>();

        itemMap.put("shield", new Shield());
        itemMap.put("snowball", new Snowball());
        itemMap.put("irongolem", new IronGolem());
        itemMap.put("snowman", new Snowman());
        itemMap.put("wolf", new FightingWolf());
    }

    public static boolean purchase(Player player, String itemName, int quantity, StringBuilder error){
        if(quantity <= 0){
            error.append("Quantity must be greater than 0");
            return false;
        }

        Purchasable item = itemMap.get(itemName.toLowerCase());
        if(item == null){
            error.append("that item does not exist");
            return false;
        }
        int playerPoints = Database.getInstance().get(player).points;

        if(playerPoints <= item.price * quantity){
            error.append("not enough money");
            return false;
        }
        /*
        TODO
        let's say the player has 1 spot in their inventory left
        we try to add 100 cakes
        we will try to add 100 cakes, and charge them for 100 cakes
        we will successfully add 1 cakes, and get a hashmap containing the material cake with the quantity of cakes not added (99 cakes)?
         */
        item.purchase(player, quantity);
        Database.getInstance().changePoints(player, item.price * quantity * -1);
        return true;
    }
}