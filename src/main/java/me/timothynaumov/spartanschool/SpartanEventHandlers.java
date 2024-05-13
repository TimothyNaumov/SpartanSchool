package me.timothynaumov.spartanschool;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpartanEventHandlers implements Listener {
    private GameLogic gameLogic;

    public SpartanEventHandlers(GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }

    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent e){
        Entity damager = e.getDamager();
        LivingEntity entity = (LivingEntity) e.getEntity();
        if(e.getDamage() > entity.getHealth()){
            gameLogic.handlePlayerKillingEntity(damager);
        }
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e){
        Entity entity = e.getEntity();
        gameLogic.handleMobDeath(entity);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        gameLogic.handlePlayerDeath(player);
    }

    @EventHandler
    public void onButtonPress(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block block = e.getClickedBlock();
            gameLogic.handleButtonPress(block, e.getPlayer());
        }
    }
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent e) {
        //make every item in the players inventory unbreakable when they change items
        for (ItemStack item : e.getPlayer().getInventory().getContents())
            GameUtils.makeUnbreakable(item);
    }
}
