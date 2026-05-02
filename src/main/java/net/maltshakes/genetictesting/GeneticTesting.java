package net.maltshakes.genetictesting;

import com.mojang.logging.LogUtils;
import java.util.List;
import net.maltshakes.genetictesting.genes.datamodel.BookEntry;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;
import net.maltshakes.genetictesting.genes.handler.AnimalResolver;
import net.maltshakes.genetictesting.item.*;
import net.maltshakes.genetictesting.utils.GeneHelpers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// TODO
// Add recessive/phenotype filter with config options
// Reference genetic bettas github so bettas can also be in test world
// Put genes in logical order
// Github wiki documentation
// Backport to 1.18.2

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GeneticTesting.MOD_ID)
public class GeneticTesting {
  // Define mod id
  public static final String MOD_ID = "genetictesting";
  // Directly reference a slf4j logger
  private static final Logger LOGGER = LogUtils.getLogger();

  public GeneticTesting(FMLJavaModLoadingContext context) {
    IEventBus modEventBus = context.getModEventBus();
    GeneTestingItems.register(modEventBus);
    // Register the commonSetup method for modloading
    modEventBus.addListener(this::commonSetup);
    // Register for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
    // Register the item to a creative tab
    modEventBus.addListener(this::addCreative);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    LOGGER.info("STARTING SETUP");
  }

  // Add items to the creative tab
  private void addCreative(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
      event.accept(GeneTestingItems.LIVESTOCK_TAPE_MEASURE);
    }
  }

  // EventBusSubscriber for MOD Bus
  @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {
    @SubscribeEvent
    public static void onItemColourHandler(RegisterColorHandlersEvent.Item event) {
      event.register(
          (stack, tintIndex) -> {
            // tintIndex 0 is the base layer being coloured
            if (tintIndex == 0) {
              if (stack.hasTag() && stack.getTag().contains("BookColour")) {
                return stack.getTag().getInt("BookColour");
              }
              return 0x8B4513;
            }
            return -1;
          },
          GeneTestingItems.GENE_BOOK.get());
    }
  }

  // EventBusSubscriber automatically registers all static methods in the class annotated with
  // @SubscribeEvent
  @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
  /**
   * Handler for the GeneBook. This is the main handler where all functionality relating to the
   * GeneBook is being handled. GeneBooks are created by using a vanilla Minecraft book and
   * interacting with a LivingEntity that has the 'Genetics' NBT tag.
   */
  public static class GeneBookHandler {
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
      ItemStack stack = event.getItemStack();
      Player player = event.getEntity();
      Entity target = event.getTarget();
      // Check if item is a vanilla book and player interacts with LivingEntity
      if (stack.is(Items.BOOK) && target instanceof LivingEntity livingTarget) {
        if (!event.getLevel().isClientSide()) {
          CompoundTag entityNBT = livingTarget.serializeNBT();
          if (entityNBT.contains("Genetics")) {
            // Initalize new GeneBook item called geneBook
            ItemStack geneBook = new ItemStack(GeneTestingItems.GENE_BOOK.get());
            if (geneBook.getItem() instanceof GeneBookItem geneItem) {
              // Gets all Genetics as a String through the 'Genetics' key
              String fullGeneTag = entityNBT.get("Genetics").toString();
              int[] agenes = GeneHelpers.extractIntFromArray(fullGeneTag, "AGenes");
              int[] sgenes = GeneHelpers.extractIntFromArray(fullGeneTag, "SGenes");
              boolean isFemale = entityNBT.getBoolean("IsFemale");
              if (agenes != null) {
                // Gets the entityType (what kind of entity is being handled)
                String entityType = EntityType.getKey(livingTarget.getType()).toString();
                List<BookEntry> bookEntries =
                    AnimalResolver.resolveAllEntries(entityType, agenes, sgenes, isFemale);
                GeneFormatting geneFormat = AnimalResolver.getFormat(entityType);
                if (geneFormat != null) {
                  // Gets the book colour set to the animal
                  int colour = geneFormat.getBookColour();
                  CompoundTag tag = geneBook.getOrCreateTag();
                  tag.putInt("BookColour", colour);
                }
                // Saves the entries to the book
                geneItem.savePagesToBook(geneBook, bookEntries, player);
                // Sets the name of the book to be the animal name
                if (livingTarget.hasCustomName()) {
                  geneBook.setHoverName(livingTarget.getCustomName());
                } else {
                  geneBook.resetHoverName();
                }
                stack.shrink(1);
                // If that was the last book in the stack, set the geneBook as item
                if (stack.isEmpty()) {
                  player.setItemInHand(event.getHand(), geneBook);
                  // Else add new geneBook to the inventory. If inventory full, drop geneBook on
                  // ground
                } else {
                  if (!player.getInventory().add(geneBook)) {
                    player.drop(geneBook, false);
                  }
                }
              }
            }
          }
        }
        event.setCancellationResult(
            InteractionResult.sidedSuccess(event.getLevel().isClientSide()));
        event.setCanceled(true);
      }
    }
  }
}
