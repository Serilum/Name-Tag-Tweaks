package com.natamus.nametagtweaks.events;

import com.natamus.nametagtweaks.config.ConfigHandler;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NameTagEvent {
	public static void mobItemDrop(Level level, Entity entity, DamageSource damageSource) {
		if (level.isClientSide) {
			return;
		}
		
		if (entity.hasCustomName()) {
			if (entity instanceof Slime) {
				Slime slime = (Slime)entity;
				int slimesize = slime.getSize();
				if (slimesize != 4) {
					return;
				}
			}
			
			ItemStack nametagstack = new ItemStack(Items.NAME_TAG, 1);
			if (ConfigHandler.droppedNameTagbyEntityKeepsNameValue) {
				Component name = entity.getName();
				nametagstack.set(DataComponents.CUSTOM_NAME, name);
			}
			nametagstack.set(DataComponents.REPAIR_COST, 0);
			
			Vec3 evec = entity.position();
			ItemEntity ie = new ItemEntity(level, evec.x(), evec.y()+1, evec.z(), nametagstack);
			level.addFreshEntity(ie);
		}
	}
}
