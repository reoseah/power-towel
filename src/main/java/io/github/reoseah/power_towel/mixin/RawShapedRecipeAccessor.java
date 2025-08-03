package io.github.reoseah.power_towel.mixin;

import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Optional;

@Mixin(RawShapedRecipe.class)
public interface RawShapedRecipeAccessor {
    @Invoker("create")
    static RawShapedRecipe invokeCreate(Integer width, Integer height, List<Optional<Ingredient>> ingredients) {
        throw new AssertionError();
    }
}
