package cn.solarmoon.idyllic_food_diary.feature.generic_recipe.soup;

import cn.solarmoon.idyllic_food_diary.feature.spice.SpiceList;
import cn.solarmoon.idyllic_food_diary.feature.fluid_temp.Temp;
import cn.solarmoon.idyllic_food_diary.registry.common.IMRecipes;
import cn.solarmoon.solarmoon_core.api.data.SerializeHelper;
import cn.solarmoon.solarmoon_core.api.entry.common.RecipeEntry;
import cn.solarmoon.solarmoon_core.api.recipe.IConcreteRecipe;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record SoupRecipe (
        ResourceLocation id,
        List<Ingredient> ingredients,
        FluidStack inputFluid,
        Temp temp,
        SpiceList withSpices,
        int time,
        FluidStack outputFluid,
        int exp
) implements IConcreteRecipe {
    @Override
    public RecipeEntry<?> getRecipeEntry() {
        return IMRecipes.SOUP;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public static class Serializer implements RecipeSerializer<SoupRecipe> {

        @Override
        public @NotNull SoupRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            List<Ingredient> inputIngredients = SerializeHelper.readIngredients(json, "ingredients");
            FluidStack inputFluid = SerializeHelper.readFluidStack(json, "input_fluid");
            Temp tempScale = Temp.readFromJson(json);
            SpiceList withSpices = SpiceList.readSpices(json, "with_spices");
            int time = GsonHelper.getAsInt(json, "time");
            FluidStack outputFluid = SerializeHelper.readFluidStack(json, "output_fluid");
            int exp = GsonHelper.getAsInt(json, "exp", 0);
            return new SoupRecipe(recipeId, inputIngredients, inputFluid, tempScale, withSpices, time, outputFluid, exp);
        }

        @Nullable
        @Override
        public SoupRecipe fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer) {
            List<Ingredient> ingredients = SerializeHelper.readIngredients(buffer);
            FluidStack inputFluid = buffer.readFluidStack();
            Temp tempScale = buffer.readEnum(Temp.class);
            SpiceList withSpices = SpiceList.readSpices(buffer);
            int time = buffer.readInt();
            FluidStack outputFluid = buffer.readFluidStack();
            int exp = buffer.readInt();
            return new SoupRecipe(recipeId, ingredients, inputFluid, tempScale, withSpices, time, outputFluid, exp);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, SoupRecipe recipe) {
            SerializeHelper.writeIngredients(buffer, recipe.ingredients());
            buffer.writeFluidStack(recipe.inputFluid());
            buffer.writeEnum(recipe.temp);
            SpiceList.writeSpices(buffer, recipe.withSpices());
            buffer.writeInt(recipe.time());
            recipe.outputFluid().writeToPacket(buffer);
            buffer.writeInt(recipe.exp());
        }

    }
}
