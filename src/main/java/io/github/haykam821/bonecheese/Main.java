package io.github.haykam821.bonecheese;


import io.github.haykam821.bonecheese.item.BoneCheeseItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	private static final String MOD_ID = "bonecheese";

	private static final Identifier BONE_CHEESE_ID = new Identifier(MOD_ID, "bone_cheese");
	private static final FoodComponent BONE_CHEESE_FOOD = new FoodComponent.Builder()
		.hunger(2)
		.saturationModifier(0.4f)
		.snack()
		.build();
	public static final Item BONE_CHEESE = new BoneCheeseItem(new Item.Settings().maxCount(1).group(ItemGroup.FOOD).food(BONE_CHEESE_FOOD));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, BONE_CHEESE_ID, BONE_CHEESE);
	}
}