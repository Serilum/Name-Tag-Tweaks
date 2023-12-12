package com.natamus.nametagtweaks.forge.events;

import com.natamus.nametagtweaks.cmds.NametagCommand;
import com.natamus.nametagtweaks.config.ConfigHandler;
import com.natamus.nametagtweaks.events.NameTagEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeNameTagEvent {
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
    	if (ConfigHandler.enableNameTagCommand) {
    		NametagCommand.register(e.getDispatcher());
    	}
    }

	@SubscribeEvent
	public void mobItemDrop(LivingDeathEvent e) {
		LivingEntity livingEntity = e.getEntityLiving();
		NameTagEvent.mobItemDrop(livingEntity.level, e.getEntity(), e.getSource());
	}
}
