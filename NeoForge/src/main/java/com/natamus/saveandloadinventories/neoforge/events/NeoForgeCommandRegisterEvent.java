package com.natamus.saveandloadinventories.neoforge.events;

import com.mojang.brigadier.CommandDispatcher;
import com.natamus.saveandloadinventories.cmds.CommandListinventories;
import com.natamus.saveandloadinventories.cmds.CommandLoadinventory;
import com.natamus.saveandloadinventories.cmds.CommandSaveinventory;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeCommandRegisterEvent {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent e) {
		CommandDispatcher<CommandSourceStack> dispatcher = e.getDispatcher();

		CommandListinventories.register(dispatcher);
		CommandLoadinventory.register(dispatcher);
		CommandSaveinventory.register(dispatcher);
	}
}
