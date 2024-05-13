package me.timothynaumov.spartanschool;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(player, 9 * 6, ChatColor.DARK_BLUE + "Spartan Shop");

        player.openInventory(inventory);
        player.setMetadata("OpenedSpartanShop", new FixedMetadataValue(SpartanSchool.getInstance(), "Spartan Shop"));

        ItemStack closeButton = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = closeButton.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "Close Spartan Shop");
        closeButton.setItemMeta(closeMeta);

        ItemStack shieldButton = new ItemStack(Material.SHIELD);
        ItemMeta shieldMeta = shieldButton.getItemMeta();
        shieldMeta.setDisplayName(ChatColor.RED + "Shield");

        ArrayList<String> testLore = new ArrayList<>();
        testLore.add("5 dabloons");
        testLore.add("5 more dabloons");
        shieldMeta.setLore(testLore);

        shieldButton.setItemMeta(shieldMeta);

        inventory.setItem(0, shieldButton);

        inventory.setItem(8, closeButton);
        return true;
    }
}
