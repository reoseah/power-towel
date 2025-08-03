package io.github.reoseah.power_towel;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class TowelMorningStarItem extends Item {

	public static final Identifier BASE_ENTITY_KNOCKBACK_MODIFIER_ID = Identifier.of("power_towel:base_attack_speed");

	public static final Item.Settings SETTINGS = new Item.Settings() //
		.registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of("power_towel:towel_morning_star"))) //
		.useItemPrefixedTranslationKey() //
		.maxCount(1) //
		.rarity(Rarity.EPIC) //
		.attributeModifiers(createAttributeModifiers());

	public static final Item INSTANCE = new TowelMorningStarItem(SETTINGS);

	public TowelMorningStarItem(Settings settings) {
		super(settings);
	}

	public static AttributeModifiersComponent createAttributeModifiers() {
		return AttributeModifiersComponent.builder() //
			.add( //
				EntityAttributes.ATTACK_DAMAGE, //
				new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, 9.0, EntityAttributeModifier.Operation.ADD_VALUE), //
				AttributeModifierSlot.MAINHAND //
			) //
			.add( //
				EntityAttributes.ATTACK_SPEED, //
				new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, -2.4, EntityAttributeModifier.Operation.ADD_VALUE), //
				AttributeModifierSlot.MAINHAND //
			) //
			.add( //
				EntityAttributes.ATTACK_KNOCKBACK, //
				new EntityAttributeModifier(BASE_ENTITY_KNOCKBACK_MODIFIER_ID, 3, EntityAttributeModifier.Operation.ADD_VALUE), //
				AttributeModifierSlot.MAINHAND //
			) //
			.build();
	}
}
