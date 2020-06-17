package io.github.haykam821.bonecheese.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class BoneCheeseItem extends Item {
	public BoneCheeseItem(Settings settings) {
		super(settings);
	}

	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		ItemStack finishStack = super.finishUsing(stack, world, user);

		if (user instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) user;
			if (player.abilities.creativeMode) {
				return finishStack;
			}
		}
		return new ItemStack(Items.BONE);
	}
}