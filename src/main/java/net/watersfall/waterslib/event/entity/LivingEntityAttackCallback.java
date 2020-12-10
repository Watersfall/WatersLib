package net.watersfall.waterslib.event.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;

public interface LivingEntityAttackCallback
{
	Event<LivingEntityAttackCallback> EVENT = EventFactory.createArrayBacked(LivingEntityAttackCallback.class,
			(listeners) -> ((attacker, target) -> {
				for(LivingEntityAttackCallback event : listeners)
				{
					ActionResult result = event.interact(attacker, target);
					if(result != ActionResult.PASS)
					{
						return result;
					}
				}
				return ActionResult.PASS;
			})
	);

	ActionResult interact(LivingEntity attacker, Entity target);
}
