package redstonedev.betternetherite.patches;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import redstonedev.betternetherite.BetterNetheriteFabric;
import redstonedev.betternetherite.mixin.RecipeManagerAccessor;

public class RecipeRemovePatch {
    public static <K, V> Map<K, V> createConcurrentMap(Map<K, V> map) {
		Map<K, V> map2 = new ConcurrentHashMap<>();

		for (Map.Entry<K, V> entry : map.entrySet()) {
			map2.put(entry.getKey(), entry.getValue());
		}

		return map2;
	}

	public static void onServerStarted(MinecraftServer server) {
		RecipeManagerAccessor recipeManager = (RecipeManagerAccessor) server.getRecipeManager();

		Map<RecipeType<?>, Map<Identifier, Recipe<?>>> recipes_ = createConcurrentMap(recipeManager.getRecipes());
		Map<Identifier, Recipe<?>> recipes = createConcurrentMap(recipeManager.getRecipesById());
		Iterator<Map.Entry<Identifier, Recipe<?>>> iter = recipes.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<Identifier, Recipe<?>> recipe = iter.next();

			if (!recipe.getKey().toString().contains("trim")) continue;

			String id = recipe.getKey().toString();

			// Remove the recipe for "minecraft:smithing_template" (netherite)
			if (id.equals("minecraft:rib_armor_trim_smithing_template")) {
				BetterNetheriteFabric.LOGGER.info("Removing recipe: " + id);

				Identifier iden = recipe.getKey();
				RecipeType<?> type = recipe.getValue().getType();

				Map<Identifier, Recipe<?>> __r = createConcurrentMap(recipes_.get(type));

				if (__r != null && __r.containsKey(iden)) {
					__r.remove(iden);
					recipes_.replace(type, __r);
				}

				recipes.remove(iden);
			}
		}

		recipeManager.setRecipes(recipes_);
		recipeManager.setRecipesById(recipes);
	}
}
