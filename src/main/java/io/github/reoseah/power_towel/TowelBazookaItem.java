package io.github.reoseah.power_towel;

import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class TowelBazookaItem extends Item {
	public static final Item.Settings SETTINGS = new Item.Settings() //
		.registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of("power_towel:towel_bazooka"))) //
		.useItemPrefixedTranslationKey() //
		.rarity(Rarity.EPIC) //
		.repairable(Items.NETHER_STAR) //
		.maxCount(1) //
		.maxDamage(TowelItem.DURABILITY);

	public static final Item INSTANCE = new TowelBazookaItem(SETTINGS);

	public TowelBazookaItem(Settings settings) {
		super(settings);
	}


}
