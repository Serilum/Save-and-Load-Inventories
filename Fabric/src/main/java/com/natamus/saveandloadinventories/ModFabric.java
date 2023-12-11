package com.natamus.saveandloadinventories;

import com.natamus.collective.check.RegisterMod;
import com.natamus.saveandloadinventories.cmds.CommandListinventories;
import com.natamus.saveandloadinventories.cmds.CommandLoadinventory;
import com.natamus.saveandloadinventories.cmds.CommandSaveinventory;
import com.natamus.saveandloadinventories.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			CommandListinventories.register(dispatcher);
			CommandLoadinventory.register(dispatcher);
			CommandSaveinventory.register(dispatcher);
		});
	}

	private static void setGlobalConstants() {

	}
}
