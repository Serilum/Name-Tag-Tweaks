package com.natamus.nametagtweaks;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.CollectiveEntityEvents;
import com.natamus.nametagtweaks.cmds.NametagCommand;
import com.natamus.nametagtweaks.config.ConfigHandler;
import com.natamus.nametagtweaks.events.NameTagEvent;
import com.natamus.nametagtweaks.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

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
			if (ConfigHandler.enableNameTagCommand) {
				NametagCommand.register(dispatcher);
			}
		});

		CollectiveEntityEvents.ON_ENTITY_IS_DROPPING_LOOT.register((Level world, Entity entity, DamageSource damageSource) -> {
			NameTagEvent.mobItemDrop(world, entity, damageSource);
		});
	}

	private static void setGlobalConstants() {

	}
}
