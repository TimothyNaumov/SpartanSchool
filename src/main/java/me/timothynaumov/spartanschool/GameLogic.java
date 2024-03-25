package me.timothynaumov.spartanschool;

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

            if (enemyCount == 0) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Wave " + (waveCount - 1) + " has been completed! Press the button to start the next wave");
                GameUtils.resetPlayersInBetweenRounds();
                GameUtils.levelUp(waveCount);
            }
        }
    }

    public void handlePlayerKillingEntity(Entity damager){
        if(damager.getType() != EntityType.PLAYER)
            return;

        database.changePoints((Player) damager, 1);
    }

    public void handlePlayerDeath(Player player){
        player.setGameMode(GameMode.SPECTATOR);
        GameUtils.checkGameOver();
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
        waveCount = 100;
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
        for(int i = 1; i <= waveCount; i++){
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
        Entity skeleton = world.spawnEntity(enemySpawnLocation, EntityType.SKELETON);
        enemyCount ++;
        if(waveCount >= 5 && r.nextBoolean()){
            Entity blaze = world.spawnEntity(enemySpawnLocation, EntityType.BLAZE);
            enemyCount ++;
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
        } else {
            player.sendMessage(ChatColor.RED + error.toString());
        }
    }
    public void getBalance(Player p){
        p.sendMessage(p.getDisplayName() + " has " + database.get(p).points + " points");
    }
}
