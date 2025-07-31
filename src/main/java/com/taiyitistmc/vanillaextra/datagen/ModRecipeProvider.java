package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.util.Helpers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        heatRecipe(ModBlocks.LAND_KELP.get(), RecipeCategory.FOOD, Items.DRIED_KELP, 0.1F, 200, recipeOutput);
    }

    private void heatRecipe(ItemLike materialItem, RecipeCategory category, ItemLike finalItem, float exp, int cookingTime, RecipeOutput recipeOutput) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(materialItem), category, finalItem, exp, cookingTime).unlockedBy("has_" + materialItem.asItem().getDescriptionId().substring(VanillaExtra.MODID.length() + 7), has(materialItem)).save(recipeOutput, Helpers.identifier(getSmeltingRecipeName(finalItem)));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(materialItem), category, finalItem, exp, cookingTime).unlockedBy("has_" + materialItem.asItem().getDescriptionId().substring(VanillaExtra.MODID.length() + 7), has(materialItem)).save(recipeOutput, Helpers.identifier(getCampfireRecipeName(finalItem)));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(materialItem), category, finalItem, exp, cookingTime).unlockedBy("has_" + materialItem.asItem().getDescriptionId().substring(VanillaExtra.MODID.length() + 7), has(materialItem)).save(recipeOutput, Helpers.identifier(getBlastingRecipeName(finalItem)));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(materialItem), category, finalItem, exp, cookingTime).unlockedBy("has_" + materialItem.asItem().getDescriptionId().substring(VanillaExtra.MODID.length() + 7), has(materialItem)).save(recipeOutput, Helpers.identifier(getSmokingRecipeName(finalItem)));
    }

    protected static String getSmokingRecipeName(ItemLike itemLike) {
        return getItemName(itemLike) + "_from_smoking";
    }

    protected static String getCampfireRecipeName(ItemLike itemLike) {
        return getItemName(itemLike) + "_from_campfire";
    }

}
