package net.maltshakes.genetictesting.server;

import java.util.function.Supplier;
import net.maltshakes.genetictesting.item.GeneBookItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class SaveGeneBookPagesPacket {
    private final ListTag compiledPages;
    private final InteractionHand hand;

    public SaveGeneBookPagesPacket(ListTag compiledPages, InteractionHand hand) {
        this.compiledPages = compiledPages;
        this.hand = hand;
    }

    public SaveGeneBookPagesPacket(FriendlyByteBuf buf) {
        CompoundTag wrapper = buf.readNbt();
        this.compiledPages =
                wrapper != null ? wrapper.getList("pages", 8) : new ListTag(); // 8 = StringTag
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void toBytes(FriendlyByteBuf buf) {
        CompoundTag wrapper = new CompoundTag();
        wrapper.put("pages", this.compiledPages);
        buf.writeNbt(wrapper);
        buf.writeEnum(this.hand);
    }

    // Server-side execution handler
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(
                () -> {
                    ServerPlayer player = context.getSender();
                    if (player == null) return;

                    ItemStack stack = player.getItemInHand(this.hand);
                    if (stack.getItem() instanceof GeneBookItem) {
                        CompoundTag tag = stack.getOrCreateTag();

                        // Write client-generated text pages directly into the server stack NBT
                        tag.put("pages", this.compiledPages);
                        tag.remove("FullGeneticsData"); // Clear out processing data on server

                        // Alert client slots to re-sync item values
                        player.containerMenu.broadcastChanges();
                    }
                });
        return true;
    }
}
