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

        //Close Button
        ItemStack closeButton = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = closeButton.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "Close Spartan Shop");
        closeButton.setItemMeta(closeMeta);

        inventory.setItem(0 + (9*0), itemFactory(Material.SNOWBALL, "Snowball", "1 dabloon"));
        inventory.setItem(1 + (9*0), itemFactory(Material.SHIELD, "Shield", "5 dabloons"));


        inventory.setItem(0 + (9*2), itemFactory(Material.CARVED_PUMPKIN, "Snowman", "2 dabloons"));
        inventory.setItem(1 + (9*2), itemFactory(Material.BONE, "Wolf", "4 dabloons"));
        inventory.setItem(2 + (9*2), itemFactory(Material.IRON_INGOT, "Iron Golem", "10 dabloons"));

        inventory.setItem(8, closeButton);
        return true;
    }

    private ItemStack itemFactory(Material material, String displayName, String lore){
        ItemStack itemButton = new ItemStack(material);
        ItemMeta itemMeta = itemButton.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + displayName);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(lore);
        itemMeta.setLore(itemLore);
        itemButton.setItemMeta(itemMeta);

        return itemButton;
    }
}
