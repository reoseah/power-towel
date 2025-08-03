package io.github.reoseah.power_towel;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.component.type.WeaponComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

public class TowelWhipItem extends Item {
	public static final Identifier BASE_ENTITY_INTERACTION_RANGE_MODIFIER_ID = Identifier.of("power_towel:base_attack_speed");

	public static final Item.Settings SETTINGS = new Item.Settings() //
		.registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of("power_towel:towel_whip"))) //
		.useItemPrefixedTranslationKey() //
		.rarity(Rarity.EPIC) //
		.repairable(Items.NETHER_STAR) //
		.maxCount(1) //
		.maxDamage(TowelItem.DURABILITY) //
		.component(DataComponentTypes.WEAPON, new WeaponComponent(1)) //
		.component(DataComponentTypes.TOOL, new ToolComponent(List.of(), 1.0F, 2, false)) //
		.attributeModifiers(createAttributeModifiers());

	public static final Item INSTANCE = new TowelWhipItem(SETTINGS);

	public TowelWhipItem(Settings settings) {
		super(settings);
	}

	public static AttributeModifiersComponent createAttributeModifiers() {
		return AttributeModifiersComponent.builder()
			.add(
				EntityAttributes.ATTACK_DAMAGE,
				new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, 5.0, EntityAttributeModifier.Operation.ADD_VALUE),
				AttributeModifierSlot.MAINHAND
			)
			.add(
				EntityAttributes.ATTACK_SPEED,
				new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, -1, EntityAttributeModifier.Operation.ADD_VALUE),
				AttributeModifierSlot.MAINHAND
			)
			.add(
				EntityAttributes.ENTITY_INTERACTION_RANGE,
				new EntityAttributeModifier(BASE_ENTITY_INTERACTION_RANGE_MODIFIER_ID, 1, EntityAttributeModifier.Operation.ADD_VALUE),
				AttributeModifierSlot.MAINHAND
			)
			.build();
	}
}
