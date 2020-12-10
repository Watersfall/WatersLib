package net.watersfall.waterslib.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.watersfall.waterslib.event.entity.LivingEntityAttackCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity
{
	public LivingEntityMixin(EntityType<?> type, World world)
	{
		super(type, world);
	}

	@Inject(method = "onAttacking", at = @At("HEAD"), cancellable = true)
	public void onAttack(Entity entity, CallbackInfo info)
	{
		ActionResult result = LivingEntityAttackCallback.EVENT.invoker().interact((LivingEntity)(Object) this, entity);
		if(result != ActionResult.PASS)
		{
			info.cancel();
		}
	}
}
