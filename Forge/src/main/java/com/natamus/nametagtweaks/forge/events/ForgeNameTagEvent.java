package com.natamus.nametagtweaks.forge.events;

import com.natamus.nametagtweaks.cmds.NametagCommand;
import com.natamus.nametagtweaks.config.ConfigHandler;
import com.natamus.nametagtweaks.events.NameTagEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeNameTagEvent {
	public static void registerEventsInBus() {
		BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeNameTagEvent.class);
	}

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
