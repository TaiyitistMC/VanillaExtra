package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.init.ModBlocks;
import com.taiyitistmc.vanillaextra.init.ModItems;
import com.taiyitistmc.vanillaextra.util.Helpers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.ItemStack;
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
        heatRecipe(ModBlocks.LAND_KELP.get(), RecipeCategory.FOOD, ModItems.DRIED_LAND_KELP.get(), 0.1F, 200, recipeOutput);
        planksFromLogs(recipeOutput, ModBlocks.SAGO_PALM_PLANKS.get(), ModBlocks.SAGO_PALM_LOG.get(), 4);
        heatRecipe(ModBlocks.STRIPPED_SAGO_PALM_LOG.get(), RecipeCategory.FOOD, ModItems.SAGO.get(), 0.1F, 200, 4, recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.BREAD).define('#', ModItems.SAGO.get())
                .pattern("###")
                .unlockedBy("has_sago", has(ModItems.SAGO.get()))
                .save(recipeOutput,
                        Helpers.identifier(getItemName(Items.BREAD) +
                                "_from_" + getItemName(ModItems.SAGO.get())));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BACON.get(), 2)
                .requires(Items.PORKCHOP)
                .unlockedBy("has_bacon", has(ModItems.SAGO.get()))
                .save(recipeOutput,
                        Helpers.identifier(getItemName(ModItems.BACON.get())));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BACON_AND_EGG.get())
                .requires(Items.EGG)
                .requires(ModItems.BACON.get())
                .unlockedBy("has_bacon", has(ModItems.SAGO.get()))
                .save(recipeOutput,
                        Helpers.identifier(getItemName(ModItems.BACON_AND_EGG.get())));
        heatRecipe(ModItems.BACON.get(), RecipeCategory.FOOD, ModItems.COOKED_BACON.get(), 0.1F, 200, recipeOutput);
        heatRecipe(ModItems.WOLF_MEAT.get(), RecipeCategory.FOOD, ModItems.COOKED_WOLF_MEAT.get(), 0.1F, 200, recipeOutput);
        heatRecipe(ModItems.HORSE_MEAT.get(), RecipeCategory.FOOD, ModItems.COOKED_HORSE_MEAT.get(), 0.1F, 200, recipeOutput);
    }

    protected void heatRecipe(ItemLike materialItem, RecipeCategory category, ItemLike finalItem, float exp, int cookingTime, RecipeOutput recipeOutput) {
        heatRecipe(materialItem, category, finalItem, exp, cookingTime, 1, recipeOutput);
    }

    private void heatRecipe(ItemLike materialItem, RecipeCategory category, ItemLike finalItem, float exp, int cookingTime, int count, RecipeOutput recipeOutput) {
        ItemStack item = finalItem.asItem().getDefaultInstance();
        item.setCount(count);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(materialItem), category, item, exp, cookingTime).unlockedBy("has_" + Helpers.unwrapName(materialItem.asItem().getDefaultInstance().toString()), has(materialItem)).save(recipeOutput, Helpers.identifier(getSmeltingRecipeName(item.getItem())));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(materialItem), category, item, exp, cookingTime).unlockedBy("has_" + Helpers.unwrapName(materialItem.asItem().getDefaultInstance().toString()), has(materialItem)).save(recipeOutput, Helpers.identifier(getCampfireRecipeName(item.getItem())));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(materialItem), category, item, exp, cookingTime).unlockedBy("has_" + Helpers.unwrapName(materialItem.asItem().getDefaultInstance().toString()), has(materialItem)).save(recipeOutput, Helpers.identifier(getBlastingRecipeName(item.getItem())));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(materialItem), category, item, exp, cookingTime).unlockedBy("has_" + Helpers.unwrapName(materialItem.asItem().getDefaultInstance().toString()), has(materialItem)).save(recipeOutput, Helpers.identifier(getSmokingRecipeName(item.getItem())));
    }

    protected static String getSmokingRecipeName(ItemLike itemLike) {
        return getItemName(itemLike) + "_from_smoking";
    }

    protected static String getCampfireRecipeName(ItemLike itemLike) {
        return getItemName(itemLike) + "_from_campfire";
    }

    protected static void planksFromLogs(RecipeOutput recipeOutput, ItemLike planks, ItemLike log, int result) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, result).requires(log).group("planks").unlockedBy("has_logs", has(log)).save(recipeOutput);
    }

}
