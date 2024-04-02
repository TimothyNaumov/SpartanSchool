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

public class GuiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(player, 9 * 6, ChatColor.DARK_BLUE + "Spartan Shop");

        ItemStack getDiamondButton = new ItemStack(Material.DIAMOND);
        ItemMeta diamondMeta = getDiamondButton.getItemMeta();
        diamondMeta.setDisplayName(ChatColor.AQUA + "Get Diamond");
        getDiamondButton.setItemMeta(diamondMeta);

        ItemStack clearInventoryButton = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta clearInventoryMeta = clearInventoryButton.getItemMeta();
        clearInventoryMeta.setDisplayName(ChatColor.RED + "Clear Inventory");
        clearInventoryButton.setItemMeta(clearInventoryMeta);

        ItemStack clearWeatherButton = new ItemStack(Material.SUNFLOWER);
        ItemMeta clearWeatherMeta = clearWeatherButton.getItemMeta();
        clearWeatherMeta.setDisplayName(ChatColor.YELLOW + "Clear Weather");
        clearWeatherButton.setItemMeta(clearWeatherMeta);

        inventory.setItem(11, getDiamondButton);
        inventory.setItem(13, clearInventoryButton);
        inventory.setItem(15, clearWeatherButton);

        player.openInventory(inventory);
        player.setMetadata("OpenedMenu", new FixedMetadataValue(SpartanSchool.getInstance(), "Spartan Shop"));

        return true;
    }
}
