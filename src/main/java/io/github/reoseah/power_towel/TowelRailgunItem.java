package io.github.reoseah.power_towel;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class TowelRailgunItem extends Item {
	public static final Item.Settings SETTINGS = new Item.Settings() //
		.registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of("power_towel:towel_railgun"))) //
		.useItemPrefixedTranslationKey() //
		.maxCount(1) //
		.rarity(Rarity.EPIC);

	public static final Item INSTANCE = new TowelRailgunItem(SETTINGS);

	public TowelRailgunItem(Settings settings) {
		super(settings);
	}
}
