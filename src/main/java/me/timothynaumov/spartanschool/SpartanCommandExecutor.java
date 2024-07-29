package me.timothynaumov.spartanschool;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpartanCommandExecutor implements CommandExecutor {
    private GameLogic gameLogic;

    public SpartanCommandExecutor(GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (command.getName().equalsIgnoreCase("spartanschool")) {
//            if(sender instanceof Player p){
//                gameLogic.startSpartanSchool();
//            }
//        }
        if(command.getName().equalsIgnoreCase("balance")){
            if(sender instanceof Player p){
                gameLogic.getBalance(p);
            }
        }
        if(command.getName().equalsIgnoreCase("purchase")) {
            if (sender instanceof Player p) {
                gameLogic.handlePurchase(p, args);
            }
        }
        return true;
    }
}
