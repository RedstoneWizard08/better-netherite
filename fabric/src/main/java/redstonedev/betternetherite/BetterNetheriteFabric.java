package redstonedev.betternetherite;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import redstonedev.betternetherite.patches.RecipeRemovePatch;

public class BetterNetheriteFabric implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Better Netherite");

	@Override
	public void onInitialize() {
		LOGGER.info("Registering events...");

		ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStarted);
	}

	public void onServerStarted(MinecraftServer server) {
		LOGGER.info("Removing recipes...");

		RecipeRemovePatch.onServerStarted(server);
	}
}
