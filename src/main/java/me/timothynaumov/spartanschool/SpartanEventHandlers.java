package me.timothynaumov.spartanschool;

import me.timothynaumov.spartanschool.lobby.LobbyManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class SpartanEventHandlers implements Listener {
    private GameLogic gameLogic;
    private LobbyManager lobbyManager;

    public SpartanEventHandlers(GameLogic gameLogic, LobbyManager lobbyManager){
        this.gameLogic = gameLogic;
        this.lobbyManager = lobbyManager;
        lobbyManager = new LobbyManager();
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
        System.out.println("Event handler has detected that " + entity.getType() + " has died");
        gameLogic.handleMobDeath(entity);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        lobbyManager.handlePlayerDeath(player);
    }

    @EventHandler
    public void onButtonPress(PlayerInteractEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS){
            gameLogic.startSpartanSchool();
            return;
        }
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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        this.lobbyManager.handlePlayerJoin(player);
    }
}
