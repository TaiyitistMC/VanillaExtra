package com.taiyitistmc.vanillaextra.datagen;

import com.taiyitistmc.vanillaextra.common.init.ModBlocks;
import com.taiyitistmc.vanillaextra.common.init.ModItems;
import com.taiyitistmc.vanillaextra.common.util.Helpers;
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
        planksFromLogs(recipeOutput, ModBlocks.PEACH_PLANKS.get(), ModBlocks.PEACH_LOG.get(), 4);
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
        heatRecipe(ModItems.SQUID_RAW.get(), RecipeCategory.FOOD, ModItems.SQUID_COOKED.get(), 0.1F, 200, recipeOutput);
        heatRecipe(ModItems.BAT_WING.get(), RecipeCategory.FOOD, ModItems.COOKED_BAT_WING.get(), 0.1F, 200, recipeOutput);
        heatRecipe(ModItems.LLAMA_MEAT.get(), RecipeCategory.FOOD, ModItems.COOKED_LLAMA_MEAT.get(), 0.1F, 200, recipeOutput);
        heatRecipe(ModItems.HUMAN_MEAT.get(), RecipeCategory.FOOD, ModItems.COOKED_HUMAN_MEAT.get(), 0.1F, 200, recipeOutput);
        heatRecipe(ModItems.ENDERMAN_MEAT.get(), RecipeCategory.FOOD, ModItems.COOKED_ENDERMAN_MEAT.get(), 0.1F, 200, recipeOutput);
        swordFromMaterial(recipeOutput, ModItems.PEACH_WOOD_SWORD.get(), ModBlocks.PEACH_PLANKS.get());
        orePlantRecipe(recipeOutput, ModItems.GOLD_ORE_SEEDS, Items.GOLD_BLOCK);
        orePlantRecipe(recipeOutput, ModItems.IRON_ORE_SEEDS, Items.IRON_BLOCK);
        orePlantRecipe(recipeOutput, ModItems.COAL_ORE_SEEDS, Items.COAL_BLOCK);
        orePlantRecipe(recipeOutput, ModItems.DIAMOND_ORE_SEEDS, Items.DIAMOND_BLOCK);
        orePlantRecipe(recipeOutput, ModItems.LAPIS_ORE_SEEDS, Items.LAPIS_BLOCK);
        orePlantRecipe(recipeOutput, ModItems.REDSTONE_ORE_SEEDS, Items.REDSTONE_BLOCK);
        orePlantRecipe(recipeOutput, ModItems.EMERALD_ORE_SEEDS, Items.EMERALD_BLOCK);
        orePlantRecipe(recipeOutput, ModItems.COPPER_ORE_SEEDS, Items.COPPER_BLOCK);
        orePlantRecipe(recipeOutput, ModItems.ANCIENT_DEBRIS_SEEDS, Items.NETHERITE_BLOCK);
        orePlantRecipe(recipeOutput, ModItems.AMETHYST_SEEDS, Items.AMETHYST_BLOCK);
    }

    protected void orePlantRecipe(RecipeOutput recipeOutput, ItemLike seed, ItemLike gem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, seed)
                .define('S', gem)
                .define('B', Items.WHEAT_SEEDS)
                .pattern("SSS")
                .pattern("SBS")
                .pattern("SSS")
                .unlockedBy("has_" + getItemName(gem), has(gem))
                .save(recipeOutput,
                        Helpers.identifier(getItemName(seed)));
    }

    protected void heatRecipe(ItemLike materialItem, RecipeCategory category, ItemLike finalItem, float exp, int cookingTime, RecipeOutput recipeOutput) {
        heatRecipe(materialItem, category, finalItem, exp, cookingTime, 1, recipeOutput);
    }

    private void heatRecipe(ItemLike materialItem, RecipeCategory category, ItemLike finalItem, float exp, int cookingTime, int count, RecipeOutput recipeOutput) {
        ItemStack item = finalItem.asItem().getDefaultInstance();
        item.setCount(count);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(materialItem), category, item, exp, cookingTime).unlockedBy("has_" + Helpers.getItemName(materialItem), has(materialItem)).save(recipeOutput, Helpers.identifier(getSmeltingRecipeName(item.getItem())));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(materialItem), category, item, exp, cookingTime).unlockedBy("has_" + Helpers.getItemName(materialItem), has(materialItem)).save(recipeOutput, Helpers.identifier(getCampfireRecipeName(item.getItem())));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(materialItem), category, item, exp, cookingTime).unlockedBy("has_" + Helpers.getItemName(materialItem), has(materialItem)).save(recipeOutput, Helpers.identifier(getBlastingRecipeName(item.getItem())));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(materialItem), category, item, exp, cookingTime).unlockedBy("has_" + Helpers.getItemName(materialItem), has(materialItem)).save(recipeOutput, Helpers.identifier(getSmokingRecipeName(item.getItem())));
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

    protected static void swordFromMaterial(RecipeOutput recipeOutput, ItemLike sword, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, sword)
                .define('#', material)
                .define('A', Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("A  ")
                .unlockedBy("has_" + Helpers.getItemName(material), has(material))
                .save(recipeOutput,
                        Helpers.identifier(getItemName(sword)));
    }

}
