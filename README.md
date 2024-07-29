# Spartan School

This is a minecraft plugin for a minigame called Spartan School

## About 
Fight off waves of enemies with your friends! Purchase upgrades from the shop and level up your gear. Work together to fend off enemies as the waves get more difficult as you progress.

## Contributing

### Adding new items

1. Create a new item class in the shop package.
New items will inherit from ShopItem and new entities that fight for you will inherit from ShopEntity. 
If the item or entity is simply an already existing item with just a material and a price, 
you can skip this step and map the item in the SpartanShop to a new ShopItem by filling out the constructor in line.

2. Create a new entry in the <b>SpartanShop</b> item map. This is the item that will be added to the players inventory when they try to purchase the item.

3. Create a new item in the <b>ShopCommand</b> where the inventory is created and shown to the player. Use the itemFactory to quickly give a Material icon to be shown, a name of the item, and lore.

4. Listen for the item to be clicked on in the <b>ShopListener</b> OnClick method. When the item is clicked on, pass a purchase command down to the GameLogic class to purchase the item.


## Items

<table>
    <tr>
        <th>Item</th>
        <th>Price</th>
    </tr>
    <tr>
        <td>Shield</td>
        <td>5</td>
    </tr>
    <tr>
        <td>Snowball</td>
        <td>1</td>
    </tr>
    <tr>
        <td>Snowman</td>
        <td>2</td>
    </tr>
    <tr>
        <td>Wolf</td>
        <td>4</td>
    </tr>
    <tr>
        <td>Iron Golem</td>
        <td>10</td>
    </tr>
</table>

## Backlog
- [x] Implement item shop GUI
- [ ] Fix the mob death counter 