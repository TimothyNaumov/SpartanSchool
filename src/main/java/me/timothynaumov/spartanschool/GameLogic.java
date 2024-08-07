package me.timothynaumov.spartanschool;

import me.timothynaumov.spartanschool.lobby.LobbyManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class GameLogic {
    //public Database database;
    Database database = Database.getInstance();
    public Location playerSpawnLocation;
    public Location enemySpawnLocation;
    public int enemyCount;
    public int waveCount;
    Random r = new Random();
    HashSet<EntityType> hostileMobs = new HashSet<>(){{
        add(EntityType.ZOMBIE);
        add(EntityType.SKELETON);
        add(EntityType.BLAZE);
    }};

    public void handleMobDeath(Entity entity) {
        if(hostileMobs.contains(entity.getType())){
            enemyCount--;
            System.out.println("MOB-DEATH: " + entity.getType() + " was hostile and has died: " + enemyCount + " mobs left\n");
            if (enemyCount == 0) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Wave " + (waveCount - 1) + " has been completed! Press the button to start the next wave");
                GameUtils.resetPlayersInBetweenRounds();
                GameUtils.levelUp(waveCount);
            }
        } else {
            System.out.println("MOB-DEATH: " + entity.getType() + " WAS NOT a hostile mob: " + enemyCount + " mobs left\n");
        }
    }

    public void handlePlayerKillingEntity(Entity damager){
        if(damager.getType() != EntityType.PLAYER)
            return;

        database.changePoints((Player) damager, 1);
    }



    public void handleButtonPress(Block block, Player player){
        if(block.getType() == Material.BIRCH_BUTTON){
            if(enemyCount <= 0){
                spawnWave();
            } else {
                player.sendMessage("Please remove all enemies to spawn another wave");
            }
        }
    }

    public void startSpartanSchool(){
        Bukkit.broadcastMessage("Starting Spartan School");

        World world = Bukkit.getWorlds().get(0);

        enemyCount = 0;
        waveCount = 6;
        playerSpawnLocation = new Location(world, -691, 4, 136);
        enemySpawnLocation = new Location(world, -707, 6, 136);

        world.setTime(18000);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        GameUtils.getPlayersReady(playerSpawnLocation);
        GameUtils.addPlayersToDatabase(database);
        GameUtils.removeAllMobs();
        spawnWave();
    }

    public void spawnWave(){
        spawnMobs();
        for(int i = 2; i <= waveCount; i++){
            //TODO: delay each iteration by 1 second
            final int index = i;
            Bukkit.getScheduler().runTaskLater(SpartanSchool.getInstance(), new Runnable() {
                @Override
                public void run() {
                    spawnMobs();
                }
            }, 5L * i);
        }
        Bukkit.broadcastMessage(ChatColor.RED + "Wave " + waveCount + ": " + enemyCount + " enemies left");
        waveCount++;
    }

    private void spawnMobs(){
        World world = Bukkit.getWorlds().get(0);
        Entity zombie = world.spawnEntity(enemySpawnLocation, EntityType.ZOMBIE);
        enemyCount ++;
        System.out.println("Added a zombie, enemy count is now:" + enemyCount);
        Entity skeleton = world.spawnEntity(enemySpawnLocation, EntityType.SKELETON);
        enemyCount ++;
        System.out.println("Added a skeleton, enemy count is now:" + enemyCount);
        if(waveCount >= 5 && r.nextBoolean()){
            Entity blaze = world.spawnEntity(enemySpawnLocation, EntityType.BLAZE);
            enemyCount ++;
            System.out.println("Added a blaze, enemy count is now:" + enemyCount);
        }
    }

    public void handlePurchase(Player player, String[] args){
        if(args.length == 0){
            player.sendMessage(ChatColor.RED +  "Command failed: 1 argument are required");
            return;
        }

        String itemName = args[0];

        int quantity = 1;
        if(args.length == 2) {
            quantity = Integer.parseInt(args[1]);
        }
        StringBuilder error = new StringBuilder();
        if(SpartanShop.purchase(player, itemName, quantity, error)){
            player.sendMessage(ChatColor.GREEN + "Successfully purchased " + quantity + " " + itemName + ". " + error);
            System.out.println("Frienly mob has been added, " + enemyCount + " hostile mobs remain");
        } else {
            player.sendMessage(ChatColor.RED + error.toString());
        }
    }
    public void getBalance(Player p){
        p.sendMessage(p.getDisplayName() + " has " + database.get(p).points + " points");
    }
}
