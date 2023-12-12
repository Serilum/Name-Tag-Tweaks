package com.natamus.nametagtweaks.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.nametagtweaks.config.ConfigHandler;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;

public class NametagCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("nametag")
			.requires((iCommandSender) -> iCommandSender.getEntity() instanceof ServerPlayer)
			.executes((command) -> {
				StringFunctions.sendMessage(command.getSource(), "Usage: '/nametag <name>' while holding a name tag.", ChatFormatting.DARK_GREEN);
				return 1;
			})
			.then(Commands.argument("name", StringArgumentType.word())
			.executes((command) -> {
				Player player = command.getSource().getPlayerOrException();
				
				ItemStack nametagstack;
				if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem().equals(Items.NAME_TAG)) {
					nametagstack = player.getItemInHand(InteractionHand.MAIN_HAND);
				}
				else if (player.getItemInHand(InteractionHand.OFF_HAND).getItem().equals(Items.NAME_TAG)) {
					nametagstack = player.getItemInHand(InteractionHand.OFF_HAND);
				}
				else {
					StringFunctions.sendMessage(player, "Usage: '/nametag <name>' while holding a name tag.", ChatFormatting.RED);
					return 1;
				}
				
				String name = StringArgumentType.getString(command, "name");
				if (ConfigHandler.nameTagCommandReplaceUnderscoresWithSpaces) {
					name = name.replace("_", " ");
				}
				
				nametagstack.setHoverName(new TextComponent(name));
				nametagstack.setRepairCost(0);
				StringFunctions.sendMessage(player, "Set name value to '" + name + "'.", ChatFormatting.DARK_GREEN);
				return 1;
			}))
		);
	}
}
