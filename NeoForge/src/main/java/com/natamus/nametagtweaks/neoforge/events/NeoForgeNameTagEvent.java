package com.natamus.nametagtweaks.neoforge.events;

import com.natamus.nametagtweaks.cmds.NametagCommand;
import com.natamus.nametagtweaks.config.ConfigHandler;
import com.natamus.nametagtweaks.events.NameTagEvent;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
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
