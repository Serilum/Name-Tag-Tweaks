package com.serilum.nametagtweaks.neoforge.events;

import com.serilum.nametagtweaks.cmds.NametagCommand;
import com.serilum.nametagtweaks.config.ConfigHandler;
import com.serilum.nametagtweaks.events.NameTagEvent;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class NeoForgeNameTagEvent {
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
