package io.github.reoseah.power_towel.mixin;

import io.github.reoseah.power_towel.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin {
	/// Mixin early into recipe matching because Minecraft recipe handling
	/// doesn't allow us to detect where in the crafting grid an item is placed
	@Inject(at = @At("HEAD"), method = "updateResult", cancellable = true)
	private static void updateResult(ScreenHandler handler, ServerWorld world, PlayerEntity player, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory, @Nullable RecipeEntry<CraftingRecipe> recipe, CallbackInfo ci) {
		if (craftingInventory.getWidth() < 2 || craftingInventory.getHeight() < 2) {
			// can't use a grid smaller than 2x2
			return;
		}

		int nonEmptyCount = 0;
		var towel = ItemStack.EMPTY;
		int towelX = -1;
		int towelY = -1;

		for (int x = 0; x < craftingInventory.getWidth(); x++) {
			for (int y = 0; y < craftingInventory.getHeight(); y++) {
				int slot = y * craftingInventory.getWidth() + x;
				var stack = craftingInventory.getStack(slot);
				if (!stack.isEmpty()) {
					nonEmptyCount++;

					if (stack.isOf(TowelItem.INSTANCE) //
						|| stack.isOf(TowelWhipItem.INSTANCE) //
						|| stack.isOf(TowelMorningStarItem.INSTANCE) //
						|| stack.isOf(TowelRailgunItem.INSTANCE) //
						|| stack.isOf(TowelBazookaItem.INSTANCE)) { //
						towel = stack;
						towelX = x;
						towelY = y;
					} else {
						// something else in the grid
						return;
					}
				}
			}
		}

		if (nonEmptyCount != 1) {
			// more than one item - not our recipe
			return;
		}

		Item resultItem = null;
		if (towelX == 0 && towelY == 0) {
			resultItem = TowelBazookaItem.INSTANCE;
		} else if (towelX == craftingInventory.getWidth() - 1 && towelY == 0) {
			resultItem = TowelWhipItem.INSTANCE;
		} else if (towelX == 0 && towelY == craftingInventory.getHeight() - 1) {
			resultItem = TowelMorningStarItem.INSTANCE;
		} else if (towelX == craftingInventory.getWidth() - 1 && towelY == craftingInventory.getHeight() - 1) {
			resultItem = TowelRailgunItem.INSTANCE;
		}
		if (resultItem == null) {
			// not in a corner
			return;
		}

		var towelItem = towel.getItem();

		// if matches target - uncraft back to the default shape
		resultItem = resultItem == towelItem ? TowelItem.INSTANCE : resultItem;

		var result = towel.copyComponentsToNewStack(resultItem, 1);

		resultInventory.setStack(0, result);
		handler.setReceivedStack(0, result);
		((ServerPlayerEntity) player).networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 0, result));

		ci.cancel();
	}
}
