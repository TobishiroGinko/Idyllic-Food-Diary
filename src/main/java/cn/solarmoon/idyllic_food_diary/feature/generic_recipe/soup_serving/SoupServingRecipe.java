package cn.solarmoon.idyllic_food_diary.feature.generic_recipe.soup_serving;

import cn.solarmoon.idyllic_food_diary.registry.common.IMRecipes;
import cn.solarmoon.solarmoon_core.api.data.SerializeHelper;
import cn.solarmoon.solarmoon_core.api.entry.common.RecipeEntry;
import cn.solarmoon.solarmoon_core.api.recipe.IConcreteRecipe;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public record SoupServingRecipe(
        ResourceLocation id,
        Ingredient container,
        FluidStack fluidToServe,
        ItemStack result
) implements IConcreteRecipe {

    public int getAmountToServe() {
        return fluidToServe.getAmount();
    }

    @Override
    public RecipeEntry<?> getRecipeEntry() {
        return IMRecipes.SOUP_SERVING;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public static class Serializer implements RecipeSerializer<SoupServingRecipe> {

        @Override
        public SoupServingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient container = SerializeHelper.readIngredient(json, "container");
            FluidStack fluidToServe = SerializeHelper.readFluidStack(json, "fluid");
            ItemStack result = CraftingHelper.getItemStack(json.getAsJsonObject("result"), true);
            return new SoupServingRecipe(recipeId, container, fluidToServe, result);
        }

        @Nullable
        @Override
        public SoupServingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient container = Ingredient.fromNetwork(buffer);
            FluidStack fluidToServe = buffer.readFluidStack();
            ItemStack result = buffer.readItem();
            return new SoupServingRecipe(recipeId, container, fluidToServe, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, SoupServingRecipe recipe) {
            recipe.container().toNetwork(buffer);
            buffer.writeFluidStack(recipe.fluidToServe());
            buffer.writeItemStack(recipe.result(), false);
        }

    }
}