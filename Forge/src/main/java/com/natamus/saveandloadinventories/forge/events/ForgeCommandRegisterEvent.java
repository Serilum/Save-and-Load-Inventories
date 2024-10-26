package com.natamus.saveandloadinventories.forge.events;

import com.mojang.brigadier.CommandDispatcher;
import com.natamus.saveandloadinventories.cmds.CommandListinventories;
import com.natamus.saveandloadinventories.cmds.CommandLoadinventory;
import com.natamus.saveandloadinventories.cmds.CommandSaveinventory;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeCommandRegisterEvent {
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
        CommandDispatcher<CommandSourceStack> dispatcher = e.getDispatcher();

        CommandListinventories.register(dispatcher);
        CommandLoadinventory.register(dispatcher);
        CommandSaveinventory.register(dispatcher);
    }
}
