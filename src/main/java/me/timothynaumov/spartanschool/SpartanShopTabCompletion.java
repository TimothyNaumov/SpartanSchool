package me.timothynaumov.spartanschool;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpartanShopTabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>(SpartanShop.itemMap.keySet());
            return arguments;
        } else if (args.length > 1) {
            List<String> arguments = new ArrayList<>();
            return arguments;
        }

        return null;
}
}
