package net.kaupenjoe.mccourse;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModItemGroups;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.world.biome.ModBiomes;
import net.kaupenjoe.mccourse.world.biome.ModMaterialRules;
import net.kaupenjoe.mccourse.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class MCCourseMod implements ModInitializer, TerraBlenderApi {
	public static final String MOD_ID = "mccourse";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModWorldGeneration.generateModWorldGeneration();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);
		registerStrippables();
		registerFlammables();
	}

	private static void registerStrippables() {
		StrippableBlockRegistry.register(ModBlocks.BLACKWOOD_LOG, ModBlocks.STRIPPED_BLACKWOOD_LOG);
		StrippableBlockRegistry.register(ModBlocks.BLACKWOOD_WOOD, ModBlocks.STRIPPED_BLACKWOOD_WOOD);
	}

	private static void registerFlammables() {
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLACKWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLACKWOOD_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_LEAVES, 30, 60);
	}

	@Override
	public void onTerraBlenderInitialized() {
		ModBiomes.registerBiomes();

		// Register our surface rules
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModMaterialRules.makeKaupenValleyRules());
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, MOD_ID, ModMaterialRules.makeGlowstonePlainsRules());
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.END, MOD_ID, ModMaterialRules.makeEndRotRules());
	}
}