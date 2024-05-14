package me.timothynaumov.spartanschool;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener {

    GameLogic gameLogic;

    public ShopListener(GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }
    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(player.hasMetadata("OpenedSpartanShop")) {
            event.setCancelled(true);

            // get the clicked item and perform actions
            switch(event.getSlot()){
                case 0 + (9*0):
                    this.gameLogic.handlePurchase(player, new String[]{"snowball"});
                    break;
                case 1 + (9*0):
                    this.gameLogic.handlePurchase(player, new String[]{"shield"});
                    break;
                case 0 + (9*2):
                    this.gameLogic.handlePurchase(player, new String[]{"snowman"});
                    break;
                case 1 + (9*2):
                    this.gameLogic.handlePurchase(player, new String[]{"wolf"});
                    break;
                case 2 + (9*2):
                    this.gameLogic.handlePurchase(player, new String[]{"irongolem"});
                    break;
                case 8:
                    player.closeInventory();
                    break;
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if(player.hasMetadata("OpenedSpartanShop"))
            player.removeMetadata("OpenedSpartanShop", SpartanSchool.getInstance());
    }
}
