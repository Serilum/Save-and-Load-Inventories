package com.serilum.saveandloadinventories;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import com.serilum.saveandloadinventories.cmds.CommandListinventories;
import com.serilum.saveandloadinventories.cmds.CommandLoadinventory;
import com.serilum.saveandloadinventories.cmds.CommandSaveinventory;
import com.serilum.saveandloadinventories.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			CommandListinventories.register(dispatcher);
			CommandLoadinventory.register(dispatcher);
			CommandSaveinventory.register(dispatcher);
		});
	}

	private static void setGlobalConstants() {

	}
}
