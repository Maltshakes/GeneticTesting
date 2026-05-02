package net.maltshakes.genetictesting.item;

import net.maltshakes.genetictesting.GeneticTesting;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GeneTestingItems {
  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, GeneticTesting.MOD_ID);

  public static final RegistryObject<Item> GENE_BOOK =
      ITEMS.register("gene_book", () -> new GeneBookItem(new Item.Properties()));

  public static final RegistryObject<Item> LIVESTOCK_TAPE_MEASURE =
      ITEMS.register(
          "livestock_tape_measure", () -> new LivestockTapeMeasureItem(new Item.Properties()));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
