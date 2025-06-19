package com.natamus.saveandloadinventories.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.natamus.collective.functions.MessageFunctions;
import com.natamus.collective.functions.PlayerFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.saveandloadinventories.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

public class CommandSaveinventory {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("saveinventory").requires((iCommandSender) -> iCommandSender.hasPermission(2))
			.then(Commands.argument("inventory-name", StringArgumentType.word())
			.executes((command) -> {
				return saveinventory(command);
			}))
		);
		dispatcher.register(Commands.literal("si").requires((iCommandSender) -> iCommandSender.hasPermission(2))
			.then(Commands.argument("inventory-name", StringArgumentType.word())
			.executes((command) -> {
				return saveinventory(command);
			}))
		);
	}
	
	private static int saveinventory(CommandContext<CommandSourceStack> command) {
		CommandSourceStack source = command.getSource();
		Player player;
		try {
			player = source.getPlayerOrException();
		}
		catch (CommandSyntaxException ex) {
			MessageFunctions.sendMessage(source, "This command can only be executed as a player in-game.", ChatFormatting.RED);
			return 1;
		}
		
		String inventoryname = StringArgumentType.getString(command, "inventory-name").toLowerCase();
		if (inventoryname.trim() == "") {
			MessageFunctions.sendMessage(source, "The inventory name '" + inventoryname + "' is invalid.", ChatFormatting.RED);
			return 0;
		}
		
		String gearstring = PlayerFunctions.getPlayerGearString(player);
		if (StringFunctions.sequenceCount(gearstring, "\n") < 40) {
			MessageFunctions.sendMessage(source, "Something went wrong while generating the save file content for your inventory.", ChatFormatting.RED);
			return 0;					
		}
		
		if (!Util.writeGearStringToFile(inventoryname, gearstring)) {
			MessageFunctions.sendMessage(source, "Something went wrong while saving the content of your inventory as '" + inventoryname + "'.", ChatFormatting.RED);
			return 0;							
		}
		
		MessageFunctions.sendMessage(source, "Successfully saved your inventory as '" + inventoryname + "'.", ChatFormatting.DARK_GREEN);
		MessageFunctions.sendMessage(source, "You can load it with the command '/loadinventory " + inventoryname + "'.", ChatFormatting.DARK_GREEN);
		return 1;
	}
}
