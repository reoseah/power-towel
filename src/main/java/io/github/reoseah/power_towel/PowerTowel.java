package io.github.reoseah.power_towel;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerTowel implements ModInitializer {
	public static final String ID = "power_towel";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, "power_towel:towel", TowelItem.INSTANCE);
		Registry.register(Registries.ITEM, "power_towel:towel_whip", TowelWhipItem.INSTANCE);
		Registry.register(Registries.ITEM, "power_towel:towel_morning_star", TowelMorningStarItem.INSTANCE);
		Registry.register(Registries.ITEM, "power_towel:towel_bazooka", TowelBazookaItem.INSTANCE);
		Registry.register(Registries.ITEM, "power_towel:towel_railgun", TowelRailgunItem.INSTANCE);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(group -> {
			group.add(TowelItem.INSTANCE);
			group.add(TowelWhipItem.INSTANCE);
			group.add(TowelMorningStarItem.INSTANCE);
			group.add(TowelBazookaItem.INSTANCE);
			group.add(TowelRailgunItem.INSTANCE);
		});
	}
}
