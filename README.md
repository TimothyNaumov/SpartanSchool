# Spartan School

This is a minecraft plugin for a minigame called Spartan School

<h2> About </h2>
Fight off waves of enemies with your friends! Purchase upgrades from the shop and level up your gear. Work together to fend off enemies as the waves get more difficult as you progress.

<h2> Contributing </h2>
<h3> Adding new items</h3>
<ol>
<li>
<p>Create a new item object in the shop package.
New items will inherit from ShopItem and new entities that fight for you will inherit from ShopEntity. 
If the item or entity is simply an already existing item with just a material and a price, 
you can skip this step and map the item in the SpartanShop to a new ShopItem by filling out the constructor in line.</p>
</li>
<li>
<p>Create a new entry in the <b>SpartanShop</b> item map. This is the item that will be added to the players inventory when they try to purchase the item.</p>
</li>
<li>
<p>Create a new item in the <b>ShopCommand</b> where the inventory is created and shown to the player. Use the itemFactory to quickly give a Material icon to be shown, a name of the item, and lore.</p>
</li>
<li>
<p>Listen for the item to be clicked on in the <b>ShopListener</b> OnClick method. When the item is clicked on, pass a purchase command down to the GameLogic class to purchase th item.</p>
</li>
</ol>
<h2> Items </h2>

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