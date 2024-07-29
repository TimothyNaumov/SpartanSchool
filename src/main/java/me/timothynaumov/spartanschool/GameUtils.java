package me.timothynaumov.spartanschool;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

enum ArmorType{
    IRON,
    DIAMOND,
    ENCHANTED_DIAMOND,
    NETHERITE,
    ENCHANTED_NETHERITE
}

public class GameUtils {
    public static void resetPlayer(Player player){
        if(player.getGameMode() == GameMode.SPECTATOR){
            player.setGameMode(GameMode.ADVENTURE);
        }
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }

    public static void resetPlayersInBetweenRounds(){
        World world = Bukkit.getWorlds().get(0);
        for(Player player : world.getPlayers()){
            resetPlayer(player);
        }
    }

    public static void setupPlayer(Player player, Location playerSpawnLocation){
        player.teleport(playerSpawnLocation);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        ItemStack goldenShovel = new ItemStack(Material.IRON_SWORD);
        ItemMeta shovelMeta = goldenShovel.getItemMeta();
        shovelMeta.setUnbreakable(true);
        goldenShovel.setItemMeta(shovelMeta);
        player.getInventory().addItem(goldenShovel);
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }

    public static void getPlayersReady(Location playerSpawnLocation) {
        World world = Bukkit.getWorlds().get(0);
        for (Player player : world.getPlayers()) {
            setupPlayer(player, playerSpawnLocation);
        }
    }

    public static void removeAllMobs(){
        World world = Bukkit.getWorlds().get(0);
        for(Entity e : world.getEntities()){
            if(!(e instanceof Player)){
                e.remove();
            }
        }
    }

    public static boolean isGameOver(){
        World world = Bukkit.getWorlds().get(0);
        for(Player player : world.getPlayers()){
            if(player.getGameMode() != GameMode.SPECTATOR){
                return false;
            }
        }
        Bukkit.broadcastMessage(ChatColor.RED + "Game over");
        return true;
    }

    public static void addPlayersToDatabase(Database db){
        World world = Bukkit.getWorlds().get(0);
        for(Player player : world.getPlayers()){
            db.create(player, 1000000);
        }
    }

    public static void levelUp(int waveCount){
        if(waveCount % 5 != 0)
            return;
        //lvl 5: They get enchanted armor
        //lvl 10: Diamond armor
        //lvl 15: Enchanted Diamond armor with protection II
        //lvl 20: Netherite armor
        //lvl 25: Enchanted Netherite armor with protection II
        System.out.println("wave count is " + waveCount);
        World world = Bukkit.getWorlds().get(0);
        for(Player player : world.getPlayers()){
            if(waveCount > 15){
                equipArmorSet(player, ArmorType.NETHERITE);
            } else if(waveCount > 10){
                equipArmorSet(player, ArmorType.DIAMOND);
            } else {
                equipArmorSet(player, ArmorType.IRON);
            }
        }
    }

    public static void equipArmorSet(Player player, ArmorType type){
        switch(type){
            case IRON:
                ItemStack[] IronArmorSet = {new ItemStack(Material.IRON_HELMET), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.IRON_BOOTS)};
                player.getInventory().setArmorContents(IronArmorSet);
                break;
            case DIAMOND:
                ItemStack[] DiamondArmorSet = {new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_BOOTS)};
                player.getInventory().setArmorContents(DiamondArmorSet);
                break;
            case NETHERITE:
                ItemStack[] NetheriteArmorSet = {new ItemStack(Material.NETHERITE_HELMET), new ItemStack(Material.NETHERITE_CHESTPLATE), new ItemStack(Material.NETHERITE_LEGGINGS), new ItemStack(Material.NETHERITE_BOOTS)};
                player.getInventory().setArmorContents(NetheriteArmorSet);
                break;
        }
    }

    public static ItemStack makeUnbreakable(ItemStack item){
        if(item != null && item.getType() != Material.AIR){
            ItemMeta meta = item.getItemMeta();
            if(meta != null){
                meta.setUnbreakable(true);
                item.setItemMeta(meta);
            }
        }
        return item;
    }
}
