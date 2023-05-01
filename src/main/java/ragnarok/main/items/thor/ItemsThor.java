package ragnarok.main.items.thor;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemsThor {

    public static ItemStack Hammer;
    public static ItemStack ThorChestplate;
    public static ItemStack ThorLeggings;
    public static ItemStack ThorBoots;


    public static  void init(){
        createHammer();
        createThorChestplate();
        createThorLeggings();
        createThorBoots();
    }




    private static void createThorChestplate(){
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);

        item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lThor's chestplate");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("");
        lore.add("");

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(lore);
        item.setItemMeta(meta);
        ThorChestplate = item;

    }


    private static void createThorLeggings(){
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);

        item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lThor's leggings");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("");
        lore.add("");

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(lore);
        item.setItemMeta(meta);
        ThorLeggings = item;

    }

    private static void createThorBoots(){
        ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);

        item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lThor's boots");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("");
        lore.add("");

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(lore);
        item.setItemMeta(meta);
        ThorBoots = item;

    }





    private static void createHammer(){
        ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);

        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lThor's hammer");
        List<String> lore = new ArrayList<>();
        lore.add("§7Did anyone have to");
        lore.add("§7start Ragnarok after all?");
        lore.add("");
        lore.add("");
        lore.add("");

        meta.setUnbreakable(true);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(lore);
        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 70.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier speed = new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", 0.05, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier health = new AttributeModifier(UUID.randomUUID(), "generic.max_health", 50.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier knocresist = new AttributeModifier(UUID.randomUUID(), "generic.attack_knockback", 50.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 10.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);



        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, speed);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, health);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
        item.setItemMeta(meta);
        Hammer = item;
    }
}
