package com.serilum.nametagtweaks.forge.events;

import com.serilum.nametagtweaks.cmds.NametagCommand;
import com.serilum.nametagtweaks.config.ConfigHandler;
import com.serilum.nametagtweaks.events.NameTagEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeNameTagEvent {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent e) {
		if (ConfigHandler.enableNameTagCommand) {
			NametagCommand.register(e.getDispatcher());
		}
	}

	@SubscribeEvent
	public static void mobItemDrop(LivingDeathEvent e) {
		LivingEntity livingEntity = e.getEntity();
		NameTagEvent.mobItemDrop(livingEntity.level(), e.getEntity(), e.getSource());
	}
}
