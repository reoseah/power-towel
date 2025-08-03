package io.github.reoseah.power_towel;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class TowelMorningStarItem extends Item {
	public static final Item.Settings SETTINGS = new Item.Settings() //
		.registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of("power_towel:towel_morning_star"))) //
		.useItemPrefixedTranslationKey() //
		.maxCount(1) //
		.rarity(Rarity.EPIC);

	public static final Item INSTANCE = new TowelMorningStarItem(SETTINGS);

	public TowelMorningStarItem(Settings settings) {
		super(settings);
	}
}
