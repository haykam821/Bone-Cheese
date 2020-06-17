package io.github.haykam821.bonecheese.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.haykam821.bonecheese.Main;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

@Mixin(CowEntity.class)
public class CowEntityMixin {
	@Unique
	private void addCheeseToBone(PlayerEntity player, Hand hand) {
		player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0f, 1.0f);

		ItemStack cheesedStack = ItemUsage.method_30012(player.getStackInHand(hand), player, new ItemStack(Main.BONE_CHEESE));
		player.setStackInHand(hand, cheesedStack);
	}

	@Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
	private void addBoneMilkingBehavior(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> ci) {
		ItemStack handStack = player.getStackInHand(hand);
		if (handStack.getItem() != Items.BONE) return;

		CowEntity cow = (CowEntity) (Object) this;
		if (cow.isBaby()) return;

		this.addCheeseToBone(player, hand);
		ci.setReturnValue(ActionResult.success(cow.world.isClient));
	}
}